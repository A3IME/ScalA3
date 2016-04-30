import java.sql.*;
import java.util.List;
import java.util.Scanner;

import jdbc.JDBCAdministradorDAO;
import jdbc.JDBCFuncionarioDAO;
import model.Administrador;
import model.Funcionario;

public class Teste {

	public static void main(String[] args) throws SQLException {
		JDBCFuncionarioDAO dao = new JDBCFuncionarioDAO();
		String nomeBancoDeDados, nomeUsuario, senha;
		System.out.println("Digite o nome do banco de dados, o nome do usuário e a senha: ");
		Scanner scanner = (new Scanner(System.in));
		nomeBancoDeDados = scanner.next();
		nomeUsuario = scanner.next();
		senha = scanner.next();
		scanner.close();
		
		dao.open(nomeBancoDeDados, nomeUsuario, senha);
		
		List<Funcionario> funcionarios = dao.listarPorMatricula(2);
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.toString());
		}
				
		dao.close();
	}
}