package model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DiaServico {
	private int id;
	private Calendar data;
	private String cor;
	private List<String> tipoServico;
	private HashMap<String, Integer> qtdServico;
	private HashMap<String, List<Integer>> pessoasServico;
	private int qtde;
	
	public DiaServico (int id, Calendar data, TipoServico tipoServico, int qtde) {
		this.setId(id);
		this.setData(data);
		//this.setTipoServico(tipoServico);
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

	/*public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}*/

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	
	public void gerarEscalaDia(HashMap<String, List<Integer>> filaFunionarios) {
		for(String tipo : tipoServico){
			int i;
			for(i = 0; i < qtdServico.get(tipo); i++) {
				pessoasServico.get(tipo).add(filaFunionarios.get(tipo).get(0));
				filaFunionarios.get(tipo).add(filaFunionarios.get(tipo).remove(0));
			}
			
		}
		
	}
}