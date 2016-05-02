package control.dao;

public interface InterfaceDAO {
	public void open(String databaseName, String user, String password);
	public void close();
}