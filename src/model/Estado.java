package model;

import java.util.Calendar;

public class Estado {
	private int id;
	private Calendar dataInicio;
	private Calendar dataTermino;
	private String descricao;
	
	public Estado (int id, Calendar dataInicio, Calendar dataTermino, String descricao) {
		this.setId(id);
		this.setDataInicio(dataInicio);
		this.setDataTermino(dataTermino);
		this.setDescricao(descricao);
	}
	
	public String toString() {
		return (this.id + "\t"
				+ this.dataInicio.get(Calendar.YEAR) + "/" + (this.dataInicio.get(Calendar.MONTH) + 1) + "/" + this.dataInicio.get(Calendar.DATE) + "\t"
				+ this.dataTermino.get(Calendar.YEAR) + "/" + (this.dataTermino.get(Calendar.MONTH) + 1) + "/" + this.dataTermino.get(Calendar.DATE) + "\t"
				+ this.descricao);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}