package model;

public class FuncionarioHabilitado {
	private int id;
	private String nome;
	private int classificacao;
	private int qtdeServicosTirados;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	public int getQtdeServicosTirados() {
		return qtdeServicosTirados;
	}
	public void setQtdeServicosTirados(int qtdeServicosTirados) {
		this.qtdeServicosTirados = qtdeServicosTirados;
	}
}