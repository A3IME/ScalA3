package model;

public class Administrador extends Funcionario {
	public Administrador(int id, String nomeCompleto, int matricula, String email, String telefone, int habilitacao, Estado estado) {
		super(id, nomeCompleto, matricula, email, telefone, habilitacao, estado);
	}
}