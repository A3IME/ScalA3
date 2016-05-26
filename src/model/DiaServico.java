package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import control.jdbc.JDBCDAO;

public class DiaServico extends JDBCDAO{
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
	
	public DiaServico(){
		
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
	
	public HashMap<String, List<Integer>> getPessoasServico(){
		return pessoasServico;
	}
	
	public List<Integer> gerarEscalaDia(HashMap<String, List<FuncionarioHabilitado>> filasFunionarios, List<List<Integer>> escalasDiasAnteriores) {
		TreeSet<Integer> funcionariosNaoDisponiveis = new TreeSet<Integer>();
		List<Integer> funcionariosServicoHoje = new ArrayList<Integer>();
		for(List<Integer> tempList : escalasDiasAnteriores) {
			funcionariosNaoDisponiveis.addAll(tempList);
		}
		
		System.out.println("#" + filasFunionarios);
		for(String tipo : tipoServico){
			System.out.println("##" + tipo);
			pessoasServico.put(tipo, new ArrayList<Integer>());
			int i;
			for(i = 0; i < qtdServico.get(tipo); i++) {
				if(funcionariosNaoDisponiveis.contains(filasFunionarios.get(tipo).get(0).getId())){
					i--;
				} 
				else{
					//System.out.println(pessoasServico.get(tipo));
					pessoasServico.get(tipo).add(filasFunionarios.get(tipo).get(0).getId());
					filasFunionarios.get(tipo).get(0).incrementaQtdServicos();
					funcionariosNaoDisponiveis.add(filasFunionarios.get(tipo).get(0).getId());
					funcionariosServicoHoje.add(filasFunionarios.get(tipo).get(0).getId());
				}
				filasFunionarios.get(tipo).add(filasFunionarios.get(tipo).remove(0));
			}
			
		}
		
		System.out.println("###" + filasFunionarios);
		return funcionariosServicoHoje;
		
	}
	
	public List<Integer> getFuncionariosServicoDia(Calendar dia) {
		  //List<FuncionarioHabilitado> funcionariosServicoDia = new
		  //ArrayList<FuncionarioHabilitado>();
		try{
			List<Integer> funcionariosServicoDia = new ArrayList<Integer>();
			String diaString = new SimpleDateFormat("yyyy-MM-dd").format(dia.getTime());
			this.resultSet = this.statement.executeQuery("SELECT idfunc "
			+ "FROM servico_funcionario "
			+ "NATURAL JOIN (SELECT idfunc FROM servico WHERE data='" + diaString + "') AS servico");
			
			while (this.resultSet.next()) {
				funcionariosServicoDia.add(this.resultSet.getInt("idfunc"));
			}
			
			return funcionariosServicoDia;
	
		}catch(Exception e){
			return new ArrayList<Integer>();
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

	public List<DiaServico> listarEscala(String dataInicial, String dataFinal) {
		List<DiaServico> dias = new ArrayList<DiaServico>();
		
		try{
			this.resultSet = this.statement.executeQuery("SELECT idservico, data, cor "
					+ "FROM servico NATURAL JOIN tiposervico "
					+ "WHERE data > '" + dataInicial + "' AND data < '" + dataFinal + "';");
			
		while (this.resultSet.next()) {
			DiaServico tempDia = new DiaServico();
			Calendar tempCalendar = Calendar.getInstance();
			tempDia.setCor(this.resultSet.getString("cor"));
			tempCalendar.setTime(this.resultSet.getDate("data"));
			tempDia.setData(tempCalendar);
			tempDia.setId(this.resultSet.getInt("idservico"));
			dias.add(tempDia);
		}
		
		for(DiaServico dia : dias){
			this.resultSet = this.statement.executeQuery("SELECT funcao FROM servico NATURAL JOIN tiposervico "
					+ "WHERE idservico = " + dia.getId() + ";");
			
			List<String> tempFuncao = new ArrayList<String>();
			
			while(this.resultSet.next()){
				tempFuncao.add(this.resultSet.getString("funcao"));
			}
			
			for(String funcao : tempFuncao){
				List<Integer> tempFuncionario = new ArrayList<Integer>();
				this.resultSet = this.statement.executeQuery("SELECT idfunc FROM tiposervico NATURAL JOIN "
						+ "(SELECT * FROM servico_funcionario NATURAL JOIN servico) "
						+ "AS temp WHERE idservico = " + dia.getId() + " "
						+ "AND funcao = '" + funcao + "';");
				while(this.resultSet.next()){
					tempFuncionario.add(this.resultSet.getInt("idfunc"));
				}
				dia.getPessoasServico().put(funcao, tempFuncionario);
				
			}
			dia.setTipoServico(tempFuncao);
		}
		
		
		
		}catch(Exception e){
			return null;
		}
		return dias;
	}
	
}