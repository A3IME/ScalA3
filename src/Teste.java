import java.sql.*;
import java.util.List;
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
		
		List<Administrador> administradores = dao.listarPorEmail("a3ime@a3ime.com");
		for (Administrador administrador : administradores) {
			System.out.println(administrador.getId() + " "
					+ administrador.getNomeCompleto() + " "
					+ administrador.getMatricula() + " "
					+ administrador.getEmail() + " "
					+ administrador.getTelefone() + " ");
		}
		
		administradores.get(1).setNomeCompleto("Mopa2");
		dao.atualizar(administradores.get(1));
		
		dao.close();
	}
}