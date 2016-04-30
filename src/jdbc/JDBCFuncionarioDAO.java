package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.FuncionarioDAO;
import model.Estado;
import model.Funcionario;

public class JDBCFuncionarioDAO extends JDBCDAO implements FuncionarioDAO {
	@Override
	public List<Funcionario> listar() {
		List<Funcionario> funcionarios = null;
		Calendar dataInicio = null;
		Calendar dataTermino = null;
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario INNER JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				dataInicio = Calendar.getInstance();
				dataInicio.setTime(this.resultSet.getDate("datainicio"));
				
				dataTermino = Calendar.getInstance();
				dataTermino.setTime(this.resultSet.getDate("datatermino"));
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						new Estado(this.resultSet.getInt("idestado"),
								dataInicio,
								dataTermino,
								this.resultSet.getString("estadodescricao"))));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o funcionário. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar funcionário.");
		}
		return funcionarios;
	}

	@Override
	public List<Funcionario> listarPorNome(String nome) {
		List<Funcionario> funcionarios = null;
		Calendar dataInicio = null;
		Calendar dataTermino = null;
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario INNER JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE nomecompleto = '" + nome + "' AND "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				dataInicio = Calendar.getInstance();
				dataInicio.setTime(this.resultSet.getDate("datainicio"));
				
				dataTermino = Calendar.getInstance();
				dataTermino.setTime(this.resultSet.getDate("datatermino"));
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						new Estado(this.resultSet.getInt("idestado"),
								dataInicio,
								dataTermino,
								this.resultSet.getString("estadodescricao"))));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o funcionário. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar funcionário.");
		}
		return funcionarios;
	}

	@Override
	public List<Funcionario> listarPorMatricula(int matricula) {
		List<Funcionario> funcionarios = null;
		Calendar dataInicio = null;
		Calendar dataTermino = null;
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario INNER JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE matricula = " + matricula + " AND "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				dataInicio = Calendar.getInstance();
				dataInicio.setTime(this.resultSet.getDate("datainicio"));
				
				dataTermino = Calendar.getInstance();
				dataTermino.setTime(this.resultSet.getDate("datatermino"));
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						new Estado(this.resultSet.getInt("idestado"),
								dataInicio,
								dataTermino,
								this.resultSet.getString("estadodescricao"))));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o funcionário. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar funcionário.");
		}
		return funcionarios;
	}

	@Override
	public List<Funcionario> listarPorEmail(String email) {
		List<Funcionario> funcionarios = null;
		Calendar dataInicio = null;
		Calendar dataTermino = null;
		
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario INNER JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE email = '" + email + "' AND "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				dataInicio = Calendar.getInstance();
				dataInicio.setTime(this.resultSet.getDate("datainicio"));
				
				dataTermino = Calendar.getInstance();
				dataTermino.setTime(this.resultSet.getDate("datatermino"));
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						new Estado(this.resultSet.getInt("idestado"),
								dataInicio,
								dataTermino,
								this.resultSet.getString("estadodescricao"))));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o funcionário. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar funcionário.");
		}
		return funcionarios;
	}
}
