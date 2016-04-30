package dao;

import java.util.List;

import model.Funcionario;

public interface FuncionarioDAO extends InterfaceDAO {
	public List<Funcionario> listar();
	public List<Funcionario> listarPorNome(String nome);
	public List<Funcionario> listarPorMatricula(int matricula);
	public List<Funcionario> listarPorEmail(String email);
	public boolean tornarAdministrador(Funcionario funcionario);
}