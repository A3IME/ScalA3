package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Funcionario {
	protected int id;
	protected String nomeCompleto;
	protected boolean eadmin;
	protected int matricula;
	protected String email;
	protected String telefone;
	protected int habilitacao;
	protected Estado estado;
	
	public Funcionario (String nomeCompleto, int matricula, String email, String telefone, int habilitacao, Estado estado) {
		this.id = -1;
		this.nomeCompleto = nomeCompleto;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
		this.habilitacao = habilitacao;
		this.estado = estado;
	}

	public static Funcionario getFuncionarioFromDatabase(ResultSet resultSet) throws SQLException {
		Funcionario funcionario = new Funcionario (resultSet.getString("nomecompleto"),
				resultSet.getInt("matricula"),
				resultSet.getString("email"),
				resultSet.getString("telefone"),
				resultSet.getInt("habilitacao"),
				Estado.getEstadoFromDataBase(resultSet));
		
		funcionario.setId(resultSet.getInt("idfunc"));
		funcionario.setEadmin(resultSet.getBoolean("eadmin"));
		return funcionario;
	}
	
	

	public String toString() {
		return (this.id + "\t"
				+ this.nomeCompleto + "\t"
				+ this.matricula + "\t"
				+ this.email + "\t"
				+ this.telefone + "\t"
				+ this.habilitacao + "\t"
				+ (this.estado == null ? null : this.estado.toString()));
	}
	
	public boolean getEadmin() {
		return eadmin;
		
	}
	
	public void setEadmin(boolean eadmin) {
		this.eadmin = eadmin;
		
	}
	
	public int getId() {
		return id;
	}

	protected void setId(int id) {
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