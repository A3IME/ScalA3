package model;

public class Funcionario {
	protected int id;
	protected String nomeCompleto;
	protected int matricula;
	protected String email;
	protected String telefone;
	protected int habilitacao;
	protected Estado estado;
	
	public Funcionario (int id, String nomeCompleto, int matricula, String email, String telefone, int habilitacao, Estado estado) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
		this.habilitacao = habilitacao;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(int habilitacao) {
		this.habilitacao = habilitacao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}