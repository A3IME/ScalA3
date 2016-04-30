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
		Estado estado = null;
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				if (this.resultSet.getString("idestado") == null) {
					estado = null;
				}
				else {
					Calendar dataInicio = Calendar.getInstance();
					dataInicio.setTime(this.resultSet.getDate("datainicio"));
					Calendar dataTermino = Calendar.getInstance();
					dataTermino.setTime(this.resultSet.getDate("datatermino"));
					
					estado = new Estado(this.resultSet.getInt("idestado"), dataInicio, dataTermino, this.resultSet.getString("estadodescricao"));
				}
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						estado));
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
		Estado estado = null;
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE nomecompleto = '" + nome + "' AND "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				if ((this.resultSet.getString("idestado") == null) || (this.resultSet.getString("idestado") == "")) {
					estado = null;
				}
				else {
					Calendar dataInicio = Calendar.getInstance();
					dataInicio.setTime(this.resultSet.getDate("datainicio"));
					Calendar dataTermino = Calendar.getInstance();
					dataTermino.setTime(this.resultSet.getDate("datatermino"));
					
					estado = new Estado(this.resultSet.getInt("idestado"), dataInicio, dataTermino, this.resultSet.getString("estadodescricao"));
				}
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						estado));
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
		Estado estado = null;
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE matricula = " + matricula + " AND "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				if ((this.resultSet.getString("idestado") == null) || (this.resultSet.getString("idestado") == "")) {
					estado = null;
				}
				else {
					Calendar dataInicio = Calendar.getInstance();
					dataInicio.setTime(this.resultSet.getDate("datainicio"));
					Calendar dataTermino = Calendar.getInstance();
					dataTermino.setTime(this.resultSet.getDate("datatermino"));
					
					estado = new Estado(this.resultSet.getInt("idestado"), dataInicio, dataTermino, this.resultSet.getString("estadodescricao"));
				}
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						estado));
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
		Estado estado = null;
		try {
			funcionarios = new ArrayList<Funcionario>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT * "
					+ "FROM funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
					+ "WHERE email = '" + email + "' AND "
					+ "eadmin = false;");
			
			while (this.resultSet.next()) {
				if ((this.resultSet.getString("idestado") == null) || (this.resultSet.getString("idestado") == "")) {
					estado = null;
				}
				else {
					Calendar dataInicio = Calendar.getInstance();
					dataInicio.setTime(this.resultSet.getDate("datainicio"));
					Calendar dataTermino = Calendar.getInstance();
					dataTermino.setTime(this.resultSet.getDate("datatermino"));
					
					estado = new Estado(this.resultSet.getInt("idestado"), dataInicio, dataTermino, this.resultSet.getString("estadodescricao"));
				}
				
				funcionarios.add(new Funcionario(
						this.resultSet.getInt("idfunc"),
						this.resultSet.getString("nomecompleto"),
						this.resultSet.getInt("matricula"),
						this.resultSet.getString("email"),
						this.resultSet.getString("telefone"),
						this.resultSet.getInt("habilitacao"),
						estado));
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
	public boolean tornarAdministrador(Funcionario funcionario) {
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			return statement.execute("UPDATE funcionario "
					+ "SET eadmin = true "
					+ "WHERE idfunc = " + funcionario.getId() + ";");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Não foi possível transformar o funcionário em administrador. Verifique se este funcionário realmente existe.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível tornar funcionário administrador");
		}
		return false;
	}
}
