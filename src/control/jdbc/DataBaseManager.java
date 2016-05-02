package control.jdbc;

import java.sql.SQLException;

public class DataBaseManager {
	private static JDBCFuncionarioDAO funcionarioManager;
	private static JDBCAdministradorDAO administradorManager;
	private static JDBCEstadoDAO estadoManager;
	
	public static void open(String databaseName, String user, String password) throws SQLException {
		funcionarioManager = new JDBCFuncionarioDAO();
		funcionarioManager.open(databaseName, user, password);
		
		administradorManager = new JDBCAdministradorDAO();
		administradorManager.open(databaseName, user, password);
		
		estadoManager = new JDBCEstadoDAO();
		estadoManager.open(databaseName, user, password);
	}
	
	public static void close() {
		funcionarioManager.close();
		administradorManager.close();
		estadoManager.close();
	}

	public static JDBCFuncionarioDAO getFuncionarioManager() {
		return funcionarioManager;
	}

	public static void setFuncionarioManager(JDBCFuncionarioDAO funcionarioManager) {
		DataBaseManager.funcionarioManager = funcionarioManager;
	}

	public static JDBCAdministradorDAO getAdministradorManager() {
		return administradorManager;
	}

	public static void setAdministradorManager(JDBCAdministradorDAO administradorManager) {
		DataBaseManager.administradorManager = administradorManager;
	}

	public static JDBCEstadoDAO getEstadoManager() {
		return estadoManager;
	}

	public static void setEstadoManager(JDBCEstadoDAO estadoManager) {
		DataBaseManager.estadoManager = estadoManager;
	}
}
