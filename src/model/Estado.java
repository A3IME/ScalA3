package model;

import java.util.Date;

public class Estado {
	private int id;
	private Date dataInicio;
	private Date dataTermino;
	private String descricao;
	
	public Estado (int id, Date dataInicio, Date dataTermino, String descricao) {
		this.setId(id);
		this.setDataInicio(dataInicio);
		this.setDataTermino(dataTermino);
		this.setDescricao(descricao);
	}
	
	public String toString() {
		return (this.id + "\t"
				+ (this.dataInicio.getYear() + 1900) + "/" + (this.dataInicio.getMonth() + 1) + "/" + this.dataInicio.getDate() + "\t"
				+ (this.dataTermino.getYear() + 1900) + "/" + (this.dataTermino.getMonth() + 1) + "/" + this.dataTermino.getDate() + "\t"
				+ this.descricao);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}