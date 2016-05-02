package control.dao;

import java.util.List;

import model.Estado;

public interface EstadoDAO extends InterfaceDAO {
	public Estado inserir(Estado estado);
	public List<Estado> listar();
}
