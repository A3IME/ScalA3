import java.sql.*;
import java.util.Scanner;

import jdbc.JDBCAdministradorDAO;
import model.Administrador;

public class Teste {

	public static void main(String[] args) throws SQLException {
		JDBCAdministradorDAO dao = new JDBCAdministradorDAO();
		String nomeBancoDeDados, nomeUsuario, senha;
		System.out.println("Digite o nome do banco de dados, o nome do usuário e a senha: ");
		Scanner scanner = (new Scanner(System.in));
		nomeBancoDeDados = scanner.next();
		nomeUsuario = scanner.next();
		senha = scanner.next();
		scanner.close();
		
		dao.open(nomeBancoDeDados, nomeUsuario, senha);
		
		Administrador admin = new Administrador(0, "a3ime", 10, "a3ime@a3ime.com", "66666666666", 11, null);
		dao.inserir(admin);
		
		dao.close();
	}
}