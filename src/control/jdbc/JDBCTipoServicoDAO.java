package control.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCTipoServicoDAO extends JDBCDAO {
	
	public void open() throws SQLException {
		// TODO Auto-generated method stub
		super.open("scala3", "postgres", "postgres");
	}
	
	public List<String> listarFuncoes(String cor){
		List<String> funcoes = null;
		try {
			funcoes = new ArrayList<String>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT funcao FROM tiposervico "
					+ "WHERE cor='" + cor + "';");
			
			while (this.resultSet.next()) {
				funcoes.add(this.resultSet.getString("funcao"));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o funcionário. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar funcionário.");
		}
		return funcoes;
	}
	
	public HashMap<String, List<String>> mapearTipos(){
		HashMap<String, List<String>> mapa = new HashMap<String, List<String>>();
		List<String> cores = null;
		try {
			cores = new ArrayList<String>();
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			this.resultSet = this.statement.executeQuery("SELECT DISTINCT cor FROM tiposervico;");
			
			while (this.resultSet.next()) {
				cores.add(this.resultSet.getString("cor"));
			}
						
			for(String cor : cores){
				mapa.put(cor, listarFuncoes(cor));
			}
		}
		catch (SQLException e) {
			System.out.println("Não foi possível buscar o funcionário. Verifique sua conexão com o banco de dados.");
		}
		catch (NullPointerException e) {
			System.out.println("Não conectado ao banco de dados. Não foi possível buscar funcionário.");
		}
		return mapa;
	}

}
