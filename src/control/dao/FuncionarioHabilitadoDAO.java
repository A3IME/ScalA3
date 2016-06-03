package control.dao;

import java.util.List;

import model.FuncionarioHabilitado;
import model.TipoServico;

public interface FuncionarioHabilitadoDAO extends InterfaceDAO {
	List<FuncionarioHabilitado> listarFuncionariosHabilitados(TipoServico tipoServico);
}