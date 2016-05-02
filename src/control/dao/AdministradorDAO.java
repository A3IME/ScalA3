package control.dao;

import java.util.List;

import model.Administrador;

public interface AdministradorDAO extends InterfaceDAO {
	public Administrador inserir(Administrador administrador);
	public List<Administrador> listarPorNome(String nome);
	public List<Administrador> listarPorMatricula(int matricula);
	public List<Administrador> listarPorEmail(String email);
	public boolean atualizar(Administrador administrador);
	public boolean destituir(Administrador administrador);
}