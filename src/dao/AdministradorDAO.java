package dao;

import java.util.List;

import model.Administrador;

public interface AdministradorDAO {
	public void inserir(Administrador administrador);
	public List<Administrador> listarPorNome(String nome);
	public List<Administrador> listarPorMatricula(String matricula);
	public List<Administrador> listarPorEmail(String email);
	public void atualizar(Administrador administrador);
}