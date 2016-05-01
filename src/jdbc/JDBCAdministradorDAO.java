package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Tools.Tools;
import dao.AdministradorDAO;
import model.Administrador;
import model.Estado;
import model.Funcionario;


public class JDBCAdministradorDAO extends JDBCDAO implements AdministradorDAO {	
	@Override
	public Administrador inserir(Administrador administrador) {
		String comandoSQL;
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			if (administrador.getEstado() == null) {
				comandoSQL = "INSERT INTO funcionario "
						+ "(nomecompleto, eadmin, matricula, email, telefone, habilitacao, senha) "
						+ "VALUES ("
						+ "'" + administrador.getNomeCompleto() + "', "
						+ "true, "
						+ administrador.getMatricula() + ", "
						+ "'" + administrador.getEmail() + "', "
						+ "'" + administrador.getTelefone() + "', "
						+ administrador.getHabilitacao() + ", "
						+ "'" + Tools.gerarSenha() + "') "
						+ "RETURNING *;";
				
				this.resultSet =  this.statement.executeQuery(comandoSQL);
				
				if (this.resultSet.next()) {
					return Administrador.getAdministradorFromDatabase(this.resultSet);
				}
				else {
					return null;
				}
			}
			else if (administrador.getEstado().getId() != -1) {
				comandoSQL = "INSERT INTO funcionario "
						+ "(nomecompleto, eadmin, idestado, matricula, email, telefone, habilitacao, senha) "
						+ "VALUES ("
						+ "'" + administrador.getNomeCompleto() + "', "
						+ "true, "
						+ administrador.getEstado().getId() + ", "
						+ administrador.getMatricula() + ", "
						+ "'" + administrador.getEmail() + "', "
						+ "'" + administrador.getTelefone() + "', "
						+ administrador.getHabilitacao() + ", "
						+ "'" + Tools.gerarSenha() + "') "
						+ "RETURNING *;";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				this.resultSet.next();
				
				comandoSQL = "SELECT * FROM "
						+ "funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
						+ "WHERE idfunc = " + this.resultSet.getInt("idfunc") + ";";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				
				if (this.resultSet.next()) {
					return Administrador.getAdministradorFromDatabase(this.resultSet);
				}
				else {
					return null;
				}
			}
			else {			//if funcionario.getEstado().getId() != 1
				Estado estado = DataBaseManager.getEstadoManager().inserir(administrador.getEstado());
				administrador.setEstado(estado);
				
				comandoSQL = "INSERT INTO funcionario "
						+ "(nomecompleto, eadmin, idestado, matricula, email, telefone, habilitacao, senha) "
						+ "VALUES ("
						+ "'" + administrador.getNomeCompleto() + "', "
						+ "true, "
						+ administrador.getEstado().getId() + ", "
						+ administrador.getMatricula() + ", "
						+ "'" + administrador.getEmail() + "', "
						+ "'" + administrador.getTelefone() + "', "
						+ administrador.getHabilitacao() + ", "
						+ "'" + Tools.gerarSenha() + "') "
						+ "RETURNING *;";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				this.resultSet.next();
				
				comandoSQL = "SELECT * FROM "
						+ "funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado "
						+ "WHERE idfunc = " + this.resultSet.getInt("idfunc") + ";";
				
				this.resultSet = this.statement.executeQuery(comandoSQL);
				
				if (this.resultSet.next()) {
					return Administrador.getAdministradorFromDatabase(this.resultSet);
				}
				else {
					return null;
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Não foi possível inserir este administrador. Verifique se o administrador já foi inserido.");
			return null;
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível inserir administrador");
			return null;
		}
	}

	@Override
	public List<Administrador> listarPorNome(String nome) {
		List<Administrador> administradores = null;
		try {
			administradores = new ArrayList<Administrador>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			this.resultSet = statement.executeQuery("SELECT * FROM funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado WHERE " +
							"nomeCompleto = '" + nome + "' AND "
							+ "eadmin = true;");
			
			while (this.resultSet.next()) {
				administradores.add(Administrador.getAdministradorFromDatabase(this.resultSet));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o adminstrador. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar administradores.");
		}
		
		return administradores;
	}

	@Override
	public List<Administrador> listarPorMatricula(int matricula) {
		List<Administrador> administradores = null;
		try {
			administradores = new ArrayList<Administrador>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			this.resultSet = statement.executeQuery("SELECT * FROM funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado WHERE " +
							"matricula = '" + matricula + "' AND "
							+ "eadmin = true;");
			
			while (this.resultSet.next()) {
				administradores.add(Administrador.getAdministradorFromDatabase(this.resultSet));
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Não foi possível buscar o adminstrador. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar administradores.");
		}
		
		return administradores;
	}

	@Override
	public List<Administrador> listarPorEmail(String email) {
		List<Administrador> administradores = null;
		try {
			administradores = new ArrayList<Administrador>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			this.resultSet = statement.executeQuery("SELECT * FROM funcionario LEFT JOIN estado ON funcionario.idestado = estado.idestado WHERE " +
							"email = '" + email + "' AND "
							+ "eadmin = true;");
			
			while (this.resultSet.next()) {
				administradores.add(Administrador.getAdministradorFromDatabase(this.resultSet));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o adminstrador. Verifique sua conexão com o banco de dados.");
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar administradores.");
		}
		
		return administradores;
	}

	@Override
	public boolean atualizar(Administrador administrador) {
		try{
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			String attributes = "nomecompleto = '" + administrador.getNomeCompleto() + "', "
					+ (administrador.getEstado() == null ? ("") : ("idestado = " + administrador.getEstado().getId() + ", "))
					+ "matricula = " + administrador.getMatricula() + ", "
				    + "email = '" + administrador.getEmail() + "', "
				    + "telefone = '" + administrador.getTelefone() + "', "
				    + "habilitacao = " + administrador.getHabilitacao(); 
				
			return statement.execute("UPDATE funcionario SET "
					+ attributes
					+ " WHERE idfunc = " + administrador.getId() + ";");
					
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Não foi possível atualizar este administrador. Verifique se este administrador realmente existe.");
			return false;
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível atualizar administrador");
			return false;
		}
	}

	@Override
	public boolean destituir(Administrador administrador) {
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			return statement.execute("UPDATE funcionario "
					+ "SET eadmin = false "
					+ "WHERE idfunc = " + administrador.getId() + ";");
		}
		catch (SQLException e) {
			System.out.println("Não foi possível destituir este administrador. Verifique se este administrador realmente existe.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível destituir administrador");
		}
		return false;
	}
}