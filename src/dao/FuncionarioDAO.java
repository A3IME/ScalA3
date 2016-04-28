package dao;

import java.util.List;

import model.Funcionario;

public interface FuncionarioDAO extends InterfaceDAO {
	public List<Funcionario> listar();
}