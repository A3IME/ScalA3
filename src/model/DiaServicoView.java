package model;

import java.util.List;

public class DiaServicoView {
	String data;
	String cor;
	List<String> nomeServico;
	List<String> pessoasServico;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public List<String> getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(List<String> nomeServico) {
		this.nomeServico = nomeServico;
	}
	public List<String> getPessoasServico() {
		return pessoasServico;
	}
	public void setPessoasServico(List<String> pessoasServico) {
		this.pessoasServico = pessoasServico;
	}
	
	
}
