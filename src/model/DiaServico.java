package model;

import java.util.Date;

public class DiaServico {
	private int id;
	private Date data;
	private TipoServico tipoServico;
	private int qtde;
	
	public DiaServico (int id, Date data, TipoServico tipoServico, int qtde) {
		this.setId(id);
		this.setData(data);
		this.setTipoServico(tipoServico);
		this.setQtde(qtde);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
}