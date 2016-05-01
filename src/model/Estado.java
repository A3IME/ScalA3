package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Estado {
	private int id;
	private Calendar dataInicio;
	private Calendar dataTermino;
	private String descricao;
	
	public Estado (Calendar dataInicio, Calendar dataTermino, String descricao) {
		this.id = -1;
		this.setDataInicio(dataInicio);
		this.setDataTermino(dataTermino);
		this.setDescricao(descricao);
	}
	
	public static Estado getEstadoFromDataBase (ResultSet resultSet) throws SQLException {
		Estado estado = null;
		if (resultSet.getString("idestado") != null) {
			Calendar dataInicio = Calendar.getInstance();
			dataInicio.setTime(resultSet.getDate("datainicio"));
			Calendar dataTermino = Calendar.getInstance();
			dataTermino.setTime(resultSet.getDate("datatermino"));
			
			estado = new Estado(dataInicio, dataTermino, resultSet.getString("estadodescricao"));
			estado.setId(resultSet.getInt("idestado"));
		}
		
		return estado;
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
	
	private void setId(int id) {
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