package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Tools.Tools;
import dao.AdministradorDAO;
import model.Administrador;


public class JDBCAdministradorDAO extends JDBCDAO implements AdministradorDAO {	
	@Override
	public boolean inserir(Administrador administrador) {
		try{
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			String values = "'" + administrador.getNomeCompleto() + "', "
					+ "'true'" + ", "
					+ "'" + Integer.toString(administrador.getMatricula()) + "', "
					+ "'" + administrador.getEmail() + "', "
					+ "'" + administrador.getTelefone() + "', "
					+ "'" + Integer.toString(administrador.getHabilitacao()) + "',"
					+ "'" + Tools.gerarSenha() + "'";
			
			return statement.execute("INSERT INTO " +
					"funcionario " + 
					"(nomeCompleto, eadmin, matricula, email, telefone, habilitacao, senha) " + 
					"VALUES " + 
					"(" + values + ");");
		}
		catch (SQLException e) {
			System.out.println("Não foi possível inserir este administrador. Verifique se o administrador já foi inserido.");
			return false;
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível inserir administrador");
			return false;
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
			this.resultSet = statement.executeQuery("SELECT * FROM funcionario WHERE " +
					"nomeCompleto = '" + nome + "';");
			
			while (resultSet.next()) {
				administradores.add(new Administrador(
						resultSet.getInt("idfunc"),
						resultSet.getString("nomecompleto"),
						resultSet.getInt("matricula"),
						resultSet.getString("email"),
						resultSet.getString("telefone"),
						resultSet.getInt("habilitacao"),
						null));
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
	public List<Administrador> listarPorMatricula(String matricula) {
		return null;
	}

	@Override
	public List<Administrador> listarPorEmail(String email) {
		return null;
	}

	@Override
	public void atualizar(Administrador administrador) {
	}
}