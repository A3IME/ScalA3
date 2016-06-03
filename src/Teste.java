import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import Tools.FuncionarioComparator;
import control.jdbc.DataBaseManager;
import control.jdbc.JDBCAdministradorDAO;
import control.jdbc.JDBCFuncionarioDAO;
import control.jdbc.JDBCFuncionarioHabilitadoDAO;
import model.Administrador;
import model.Estado;
import model.Funcionario;
import model.FuncionarioHabilitado;
import model.TipoServico;

public class Teste {

	public static void main(String[] args) throws SQLException {
		JDBCFuncionarioHabilitadoDAO funcionarioHabilitadoManager = new JDBCFuncionarioHabilitadoDAO();
		funcionarioHabilitadoManager.open("scala3", "postgres", "postgres");
		TipoServico tipoServico = new TipoServico(0, "SgtDia", "preta");
		List<FuncionarioHabilitado> funcionariosHabilitados = funcionarioHabilitadoManager.listarFuncionariosHabilitados(tipoServico);
		funcionariosHabilitados.sort(FuncionarioComparator.getInstance());
		for (FuncionarioHabilitado funcionarioHabilitado : funcionariosHabilitados) {
			System.out.println(funcionarioHabilitado);
		}
		funcionarioHabilitadoManager.close();
	}
}