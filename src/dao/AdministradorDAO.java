package dao;

import java.util.List;

import model.Administrador;

public interface AdministradorDAO extends InterfaceDAO {
	public boolean inserir(Administrador administrador);
	public List<Administrador> listarPorNome(String nome);
	public List<Administrador> listarPorMatricula(String matricula);
	public List<Administrador> listarPorEmail(String email);
	public boolean atualizar(Administrador administrador);
}