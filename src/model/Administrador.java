package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrador extends Funcionario {
	public Administrador(String nomeCompleto, int matricula, String email, String telefone, int habilitacao, Estado estado) {
		super(nomeCompleto, matricula, email, telefone, habilitacao, estado);
	}

	public static Administrador getAdministradorFromDatabase(ResultSet resultSet) throws SQLException {
		Administrador administrador = new Administrador (resultSet.getString("nomecompleto"),
				resultSet.getInt("matricula"),
				resultSet.getString("email"),
				resultSet.getString("telefone"),
				resultSet.getInt("habilitacao"),
				Estado.getEstadoFromDataBase(resultSet));
		
		administrador.setId(resultSet.getInt("idfunc"));
		return administrador;
	}
}