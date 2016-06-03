package model;

import java.sql.SQLException;
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
		this.qtdServico = new HashMap<String, Integer>();
		this.tipoServico = new ArrayList<String>();
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
	
	public void qtdeServicoPut(String funcao, int qtde) {
		this.tipoServico.add(funcao);
		this.qtdServico.put(funcao, qtde);
	}

	public void setQtdeServico(HashMap<String, Integer> qtd) {
		this.qtdServico = qtd;
	}
	
	public HashMap<String, List<Integer>> getPessoasServico(){
		return pessoasServico;
	}
	
	public List<List<Integer>> getEscalasDiasAnteriores() throws SQLException {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		
		Calendar date = Calendar.getInstance();
		for (int i = 0; i < 2; i++) {
			List<Integer> escalaDia = new ArrayList<Integer>();
			date.add(Calendar.DATE, -1);
			
			String dataString = (new SimpleDateFormat("dd/MM/yyyy")).format(date.getTime());
			System.out.println(dataString);

			this.statement = this.database.createStatement();
			this.resultSet = this.statement.executeQuery("SELECT idfunc "
					+ "FROM servico_funcionario NATURAL JOIN servico "
					+ "WHERE data='" + dataString + "'");
			
			while(this.resultSet.next()) {
				escalaDia.add(this.resultSet.getInt("idfunc"));		
			}
			
			result.add(escalaDia);
		}
		
		return result;
	}
	
	public List<String> getTipoServico() {
		return this.tipoServico;
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
	
	public void printPreEscalaDia() {
		System.out.println("+-+-+-+-");
		System.out.println(data);
		System.out.println(cor);
		
		for(String tipo : tipoServico){
			System.out.println(tipo);
			System.out.println(qtdServico.get(tipo));	
		}
	}
	
	public void printCorData() {
		System.out.println("------");
		System.out.println(data);
		System.out.println(cor);
		
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public void gravarBD() throws SQLException {
		this.statement = this.database.createStatement();
		int idtiposervico, idservico;
		String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(this.data.getTime());
		for(String funcao : tipoServico){
			this.resultSet = this.statement.executeQuery("SELECT idtiposervico FROM tiposervico "
					+ "WHERE cor = '" + this.cor + "' "
					+ "AND funcao = '" + funcao + "';");
			
			this.resultSet.next();
			idtiposervico = this.resultSet.getInt("idtiposervico");
			
			this.resultSet = this.statement.executeQuery("INSERT INTO servico (data, idtiposervico, qtde) "
					+ "VALUES ('" + dataFormatada + "', " + idtiposervico + ", " 
					+ qtdServico.get(funcao) + ") RETURNING idservico;");
			
			this.resultSet.next();
			idservico = this.resultSet.getInt("idservico");
			
			for(int idfunc : pessoasServico.get(funcao)) {
				this.statement.execute("INSERT INTO servico_funcionario (idservico, idfunc) "
						+ "VALUES (" + idservico + ", " + idfunc + ");");
					
			}
		}
		//System.out.println("SALVO BD");
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
	
	public DiaServicoView getView() throws SQLException {
		this.statement = this.database.createStatement();
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(this.data.getTime());
		
		List<String> funcoes = new ArrayList<String>();
		List<String> pessoas = new ArrayList<String>();
		
		DiaServicoView view = new DiaServicoView();
		
		view.setCor(this.cor);
		view.setData(dataFormatada);
		
		for(String funcao : tipoServico) {
			for(int id : pessoasServico.get(funcao)){
				this.resultSet = this.statement.executeQuery("SELECT nomecompleto FROM funcionario "
						+ "WHERE idfunc=" + id + ";");
				this.resultSet.next();
				funcoes.add(funcao);
				pessoas.add(this.resultSet.getString("nomecompleto"));
			}
		}
		
		view.setPessoasServico(pessoas);
		view.setNomeServico(funcoes);
		
		return view;
		
		
	}
	
}