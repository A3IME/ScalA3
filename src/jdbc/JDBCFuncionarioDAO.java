package jdbc;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Tools.Tools;
import dao.EstadoDAO;
import dao.FuncionarioDAO;
import model.Estado;
import model.Funcionario;

public class JDBCFuncionarioDAO extends JDBCDAO implements FuncionarioDAO {
	@Override
	public Funcionario inserir(Funcionario funcionario) {
		String comandoSQL;
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			if (funcionario.getEstado() == null) {
				comandoSQL = "INSERT INTO funcionario "
						+ "(nomecompleto, eadmin, matricula, email, telefone, habilitacao, senha) "
						+ "VALUES ("
						+ "'" + funcionario.getNomeCompleto() + "', "
						+ "false, "
						+ funcionario.getMatricula() + ", "
						+ "'" + funcionario.getEmail() + "', "
						+ "'" + funcionario.getTelefone() + "', "
						+ funcionario.getHabilitacao() + ", "
						+ "'" + Tools.gerarSenha() + "') "
						+ "RETURNING *;";
				
				this.resultSet =  this.statement.executeQuery(comandoSQL);
				
				if (this.resultSet.next()) {
					return Funcionario.getFuncionarioFromDatabase(this.resultSet);
				}
				else {
					return null;
				}
			}
			else if (funcionario.getEstado().getId() != -1) {
				comandoSQL = "INSERT INTO funcionario "
						+ "(nomecompleto, eadmin, idestado, matricula, email, telefone, habilitacao, senha) "
						+ "VALUES ("
						+ "'" + funcionario.getNomeCompleto() + "', "
						+ "false, "
						+ funcionario.getEstado().getId() + ", "
						+ funcionario.getMatricula() + ", "
						+ "'" + funcionario.getEmail() + "', "
						+ "'" + funcionario.getTelefone() + "', "
						+ funcionario.getHabilitacao() + ", "
						+ "'" + Tools.gerarSenha() + "') "
						+ "RETURNING *;";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				this.resultSet.next();
				
				comandoSQL = "SELECT * FROM "
						+ "funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
						+ "WHERE idfunc = " + this.resultSet.getInt("idfunc") + ";";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				
				if (this.resultSet.next()) {
					return Funcionario.getFuncionarioFromDatabase(this.resultSet);
				}
				else {
					return null;
				}
			}
			else {			//if funcionario.getEstado().getId() != 1
				Estado estado = DataBaseManager.getEstadoManager().inserir(funcionario.getEstado());
				funcionario.setEstado(estado);
				
				comandoSQL = "INSERT INTO funcionario "
						+ "(nomecompleto, eadmin, idestado, matricula, email, telefone, habilitacao, senha) "
						+ "VALUES ("
						+ "'" + funcionario.getNomeCompleto() + "', "
						+ "false, "
						+ funcionario.getEstado().getId() + ", "
						+ funcionario.getMatricula() + ", "
						+ "'" + funcionario.getEmail() + "', "
						+ "'" + funcionario.getTelefone() + "', "
						+ funcionario.getHabilitacao() + ", "
						+ "'" + Tools.gerarSenha() + "') "
						+ "RETURNING *;";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				this.resultSet.next();
				
				comandoSQL = "SELECT * FROM "
						+ "funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
						+ "WHERE idfunc = " + this.resultSet.getInt("idfunc") + ";";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				
				if (this.resultSet.next()) {
					return Funcionario.getFuncionarioFromDatabase(this.resultSet);
				}
				else {
					return null;
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	@Override
	public List<Funcionario> listar() {
		List<Funcionario> funcionarios = null;
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
				funcionarios.add(Funcionario.getFuncionarioFromDatabase(this.resultSet));
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
				funcionarios.add(Funcionario.getFuncionarioFromDatabase(this.resultSet));
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
				funcionarios.add(Funcionario.getFuncionarioFromDatabase(this.resultSet));
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
				funcionarios.add(Funcionario.getFuncionarioFromDatabase(this.resultSet));
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
			System.out.println("Não foi possível transformar o funcionário em administrador. Verifique se este funcionário realmente existe.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível tornar funcionário administrador");
		}
		return false;
	}

	@Override
	public boolean remover(Funcionario funcionario) {
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			return statement.execute("DELETE FROM funcionario WHERE idfunc = " + funcionario.getId() + ";");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Não foi possível remover funcionário. Verifique se este funcionário realmente existe.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível tornar funcionário administrador");
		}
		return false;
	}
}
