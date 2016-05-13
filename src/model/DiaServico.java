package model;

import java.util.ArrayList;
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
	
	public DiaServico (int id, String cor, Calendar data, List<String> tipo, HashMap<String, Integer> qtd) {
		this.setId(id);
		this.setCor(cor);
		this.setData(data);
		this.setTipoServico(tipo);
		this.setQtdeServico(qtd);
		this.pessoasServico = new HashMap<String, List<Integer>>();
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
	}*/

	public void setTipoServico(List<String> tipoServico) {
		this.tipoServico = tipoServico;
	}

	/*public int getQtde() {
		return qtde;
	}*/

	public void setQtdeServico(HashMap<String, Integer> qtd) {
		this.qtdServico = qtd;
	}
	
	public void gerarEscalaDia(HashMap<String, List<FuncionarioHabilitado>> filasFunionarios) {
		System.out.println("#" + filasFunionarios);
		for(String tipo : tipoServico){
			System.out.println("##" + tipo);
			pessoasServico.put(tipo, new ArrayList<Integer>());
			int i;
			for(i = 0; i < qtdServico.get(tipo); i++) {
				System.out.println(pessoasServico.get(tipo));
				pessoasServico.get(tipo).add(filasFunionarios.get(tipo).get(0).getId());
				filasFunionarios.get(tipo).add(filasFunionarios.get(tipo).remove(0));
			}
			
		}
		
	}
	
	public void printEscalaDia() {
		System.out.println("------");
		System.out.println(data);
		System.out.println(cor);
		
		for(String tipo : tipoServico){
			System.out.println(tipo);
			
			for(Integer pessoa : pessoasServico.get(tipo)) {
				System.out.println(pessoa);
			}
			
		}
		
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
}