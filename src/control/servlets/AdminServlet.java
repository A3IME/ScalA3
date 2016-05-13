package control.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tools.FuncionarioComparator;
import control.jdbc.JDBCFuncionarioHabilitadoDAO;
import model.DiaServico;
import model.FuncionarioHabilitado;
import model.TipoServico;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servOp;
		String databaseName = "scala3";
		String dbUser = "postgres", dbPassword = "postgres";
		
		servOp = "Gerar"; //APAGAR DEPOIS DOS TESTES!!!
		//servOp = (String)request.getParameter("opt");
		
		if(servOp.equals("Gerar")) {
			List<String> servicos = new ArrayList<String>();
			List<Integer> ids = new ArrayList<Integer>();
			TipoServico tipoServico = new TipoServico(0,"a","a");
			
			//CRIAR JDBC TIPOSERVICO 
			//DADO COR E SERVICO PEGAR ID
			servicos.add("cb dia");
			servicos.add("sgt dia");
			String cor = "vermelha";
			//^ RECEBIDOS DO ADMIN
			ids.add(0);
			ids.add(2);
			//^ RECUPERADO POR METODO
			tipoServico.setCor(cor);
			
			try{
				JDBCFuncionarioHabilitadoDAO funcHab = new JDBCFuncionarioHabilitadoDAO();
				funcHab.open(databaseName, dbUser, dbPassword);
				HashMap<String, List<FuncionarioHabilitado>> filasFunionarios = new HashMap<String, List<FuncionarioHabilitado>>();
				
				int i = 0;
				for(String servico : servicos){
					tipoServico.setFuncao(servico);
					tipoServico.setId(ids.get(i));
					//^ FAZER METODO Q PEGA ID DO BD E SALVA DIRETO
					filasFunionarios.put(servico, funcHab.listarFuncionariosHabilitados(tipoServico));
					filasFunionarios.get(servico).sort(FuncionarioComparator.getInstance());
					i++;
				}
		
				Calendar data = Calendar.getInstance();
				HashMap<String, Integer> qtds = new HashMap<String, Integer>();
				qtds.put(servicos.get(0), 1);
				qtds.put(servicos.get(1), 1);
				DiaServico diaServico = new DiaServico(0, cor, data, servicos, qtds);
				
				diaServico.gerarEscalaDia(filasFunionarios);
				diaServico.printEscalaDia();
				
						
			} catch (SQLException e) {
	
			}
			
		}
	}

}
