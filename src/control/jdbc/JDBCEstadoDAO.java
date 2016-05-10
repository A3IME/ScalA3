package control.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import control.dao.EstadoDAO;
import model.Estado;

public class JDBCEstadoDAO extends JDBCDAO implements EstadoDAO {
	@Override
	public Estado inserir(Estado estado) {
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("INSERT INTO estado "
					+ "(datainicio, datatermino, estadodescricao) "
					+ "VALUES ("
					+ "'" + estado.getDataInicio().get(Calendar.YEAR) + "/" + (estado.getDataInicio().get(Calendar.MONTH) + 1) + "/" + estado.getDataInicio().get(Calendar.DATE) + "', "
					+ "'" + estado.getDataTermino().get(Calendar.YEAR) + "/" + (estado.getDataTermino().get(Calendar.MONTH) + 1) + "/" + estado.getDataTermino().get(Calendar.DATE) + "', "
					+ "'" + estado.getDescricao() + "') "
					+ "RETURNING *;");
			
			if (this.resultSet.next()) {
				return Estado.getEstadoFromDataBase(this.resultSet);
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível inserir estado. Verifique se o mesmo já está inserido.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível inserir estado.");
		}
		return null;
	}

	@Override
	public List<Estado> listar() {
		List<Estado> estados = null;
		
		try {
			estados = new ArrayList<Estado>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * FROM estado;");
			
			while (this.resultSet.next()) {
				estados.add(Estado.getEstadoFromDataBase(this.resultSet));
			}
			
			return estados;
		}
		catch (SQLException e) {
			System.out.println("Não foi possível listar os estados. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar os estados.");
		}
		
		return null;
	}
	
	public Estado listarPorId(int id) {
		Estado estado = null;
		
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * FROM estado "
					+ "WHERE idestado = " + id + ";");
			
			estado = Estado.getEstadoFromDataBase(this.resultSet);
		
			return estado;
		}
		catch (SQLException e) {
			System.out.println("Não foi possível listar os estados. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar os estados.");
		}
		
		return null;
	}
}
