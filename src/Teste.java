import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import control.jdbc.DataBaseManager;
import control.jdbc.JDBCAdministradorDAO;
import control.jdbc.JDBCFuncionarioDAO;
import model.Administrador;
import model.Estado;
import model.Funcionario;

public class Teste {

	public static void main(String[] args) throws SQLException {
		String databaseName, user, password;
		System.out.println("Digite o nome do banco de dados, o nome do usuário e a senha: ");
		Scanner scanner = (new Scanner(System.in));
		databaseName = scanner.next();
		user = scanner.next();
		password = scanner.next();
		scanner.close();	
		
		DataBaseManager.open(databaseName, user, password);
		
		List<Estado> estados = DataBaseManager.getEstadoManager().listar();
		for (Estado estado : estados) {
			System.out.println(estado.toString());
		}
				
		DataBaseManager.close();
	}
}