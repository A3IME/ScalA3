package model;

public class TipoServico {
	private int id;
	private String funcao;
	private String cor;
	
	public TipoServico (int id, String funcao, String cor) {
		this.id = id;
		this.funcao = funcao;
		this.cor = cor;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
}