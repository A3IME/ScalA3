package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.InterfaceDAO;

public abstract class JDBCDAO implements InterfaceDAO {
	protected String url;
	protected Connection database;
	protected Statement statement;
	protected ResultSet resultSet;
	
	@Override
	public void open(String databaseName, String user, String password) {
		this.url = "jdbc:postgresql://localhost:5432/" + databaseName;
		
		try {
			 Class.forName("org.postgresql.Driver");
		}
		catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		
		try {
			this.database = DriverManager.getConnection(url,user, password);
			System.out.println("Conexão aberta");
		} catch (SQLException e) {
			System.out.println("Falha na autenticação ou no nome do banco de dados.");
		}
	}

	@Override
	public void close() {
		try {
			this.database.close();
		}
		catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
		}
		catch (NullPointerException e) {
		}
		
		try {
			this.statement.close();
			System.out.println("Conexão Fechada");
		}
		catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
		}
		catch (NullPointerException e) {
		}
		
		try {
			this.resultSet.close();
			System.out.println("Conexão Fechada");
		}
		catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
		}
		catch (NullPointerException e) {
		}
	}
}