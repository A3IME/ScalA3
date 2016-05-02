package control.dao;

import java.sql.SQLException;

public interface InterfaceDAO {
	public void open(String databaseName, String user, String password) throws SQLException;
	public void close();
}