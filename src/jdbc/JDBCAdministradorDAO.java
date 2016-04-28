package jdbc;

import java.sql.SQLException;
import java.util.List;

import Tools.Tools;
import dao.AdministradorDAO;
import model.Administrador;


public class JDBCAdministradorDAO extends JDBCDAO implements AdministradorDAO {	
	@Override
	public boolean inserir(Administrador administrador) {
		try{
			this.statement = this.database.createStatement();
			String values = "'" + administrador.getNomeCompleto() + "', "
					+ "'true'" + ", "
					+ "'" + Integer.toString(administrador.getMatricula()) + "', "
					+ "'" + administrador.getEmail() + "', "
					+ "'" + administrador.getTelefone() + "', "
					+ "'" + Integer.toString(administrador.getHabilitacao()) + "',"
					+ "'" + Tools.gerarSenha() + "'";
			
			this.resultSet = statement.executeQuery("INSERT INTO " +
					"funcionario " + 
					"(nomeCompleto, eadmin, matricula, email, telefone, habilitacao, senha) " + 
					"VALUES " + 
					"(" + values + ");");
			
			return true;
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
		return null;
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