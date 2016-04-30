package model;

import java.util.Calendar;

public class DiaServico {
	private int id;
	private Calendar data;
	private TipoServico tipoServico;
	private int qtde;
	
	public DiaServico (int id, Calendar data, TipoServico tipoServico, int qtde) {
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
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