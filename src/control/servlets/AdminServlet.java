package control.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tools.FuncionarioComparator;
import Tools.TipoServicoComparator;
import control.jdbc.JDBCAdministradorDAO;
import control.jdbc.JDBCFuncionarioDAO;
import control.jdbc.JDBCFuncionarioHabilitadoDAO;
import model.Administrador;
import model.DiaServico;
import model.DiaServicoView;
import model.Funcionario;
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
		
		servOp = (String)request.getParameter("opt");
		
		if(servOp.equals("Inserir")) {
			String nome, matricula, email, telefone, senha;
			nome = (String)request.getParameter("nome");
			matricula = (String)request.getParameter("matricula");
			email = (String)request.getParameter("email");
			telefone = (String)request.getParameter("telefone");
			
			try {
				JDBCFuncionarioDAO funcionarioManager = new JDBCFuncionarioDAO();
				funcionarioManager.open(databaseName, dbUser, dbPassword);
				
				Funcionario funcionario = new Funcionario(nome, Integer.parseInt(matricula), email, telefone, 255, null);
				funcionarioManager.inserir(funcionario);
				funcionarioManager.close();
				System.out.println("Inserido!");
				request.setAttribute("alert", "Inserção Realizada com sucesso!");
				ServletContext app = this.getServletContext();
		        RequestDispatcher rd = app.getRequestDispatcher("/InserirFuncionarioADMIN.jsp");
		        rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("InserirFuncionarioADMIN.jsp");
			}
		} 
		else if(servOp.equals("Buscar")) {
			String valor, campo;
			valor = (String)request.getParameter("valor");
			campo = (String)request.getParameter("campo");
			List<Funcionario> funcionarios = null;
			funcionarios = new ArrayList<Funcionario>();
			
			try {
				JDBCFuncionarioDAO funcionarioManager = new JDBCFuncionarioDAO();
				funcionarioManager.open(databaseName, dbUser, dbPassword);
				
				if(campo.equals("id")){
					funcionarios = funcionarioManager.listaAproximadaPorId(valor, false);  
				}
				else if(campo.equals("nome")){
					funcionarios = funcionarioManager.listaAproximadaPorNome(valor, false);
				}
				else if(campo.equals("matricula")){
					funcionarios = funcionarioManager.listaAproximadaPorMatricula(valor, false);
				}
				funcionarioManager.close();
				request.setAttribute("funcionarios", funcionarios);
				ServletContext app = this.getServletContext();
		        RequestDispatcher rd = app.getRequestDispatcher("/ResultadoBuscaADMIN.jsp");
		        System.out.println("Buscar");
		        rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("BuscarFuncionaioADMIN.jsp");
			}
		}
		else if(servOp.equals("Editar dados")) {
			Integer id;
			String sOpt;
			sOpt = request.getParameter("sOpt");
			id = Integer.parseInt(request.getParameter("id"));
			
			System.out.println("Editar dados");
			if(sOpt.equals("iniciar")) {
				try {
					JDBCFuncionarioDAO funcionarioManager = new JDBCFuncionarioDAO();
					funcionarioManager.open(databaseName, dbUser, dbPassword);
					Funcionario funcionario;
					
					funcionario = funcionarioManager.listarPorId(id);  
					
					funcionarioManager.close();
					request.setAttribute("funcionario", funcionario);
					ServletContext app = this.getServletContext();
			        RequestDispatcher rd = app.getRequestDispatcher("/EditarFuncionarioADMIN.jsp");
			        System.out.println("Editar inicio");
			        rd.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e.getMessage());
					response.sendRedirect("BuscarFcunionarioADMIN.jsp");
				}	
			}
			else if (sOpt.equals("concluir")) {
				String nome, email, telefone;
				Integer matricula, habilitacao;
				boolean eadmin;
				nome = request.getParameter("nome");
				matricula = Integer.parseInt(request.getParameter("matricula"));
				email = request.getParameter("email");
				telefone = request.getParameter("telefone");
				habilitacao = Integer.parseInt(request.getParameter("habilitacao"));
				/*System.out.println(request.getParameter("eadmin"));
				if(request.getParameter("eadmin") != null) {
					eadmin = true;
				}
				else {
					eadmin = false;
				}*/
				
				Funcionario funcionario = new Funcionario(id, nome, matricula, email, telefone, habilitacao, null, false);
				
				try {
					JDBCFuncionarioDAO funcionarioManager = new JDBCFuncionarioDAO();
					funcionarioManager.open(databaseName, dbUser, dbPassword);
				
					funcionarioManager.editar(funcionario);
					
					funcionarioManager.close();
			        System.out.println("Editar concluido");
					request.setAttribute("alert", "Funcionário editado com sucesso!");
					ServletContext app = this.getServletContext();
			        RequestDispatcher rd = app.getRequestDispatcher("/BuscarFuncionarioADMIN.jsp");
			        rd.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e.getMessage());
					response.sendRedirect("BuscarFuncionarioADMIN.jsp");
				}
			}
		}
		else if(servOp.equals("Excluir")) {
			Integer id;
			id = Integer.parseInt(request.getParameter("id"));
			
			try {
				JDBCFuncionarioDAO funcionarioManager = new JDBCFuncionarioDAO();
				funcionarioManager.open(databaseName, dbUser, dbPassword);
				
				funcionarioManager.remover(id);
			
				funcionarioManager.close();
				System.out.println("Excluir");
				request.setAttribute("alert", "Funcionário excluido com sucesso!");
				ServletContext app = this.getServletContext();
		        RequestDispatcher rd = app.getRequestDispatcher("/BuscarFuncionarioADMIN.jsp");
		        rd.forward(request, response);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("InserirFuncionarioADMIN.jsp");
			}
		}
		else if(servOp.equals("Editar estado")) {
			System.out.println("Editar estado");
			response.sendRedirect("EditarEstadoADMIN.jsp");;
		}
		else if(servOp.equals("Gerar")) {
			try {
				DiaServico diaManager = new DiaServico();
				diaManager.open(databaseName, dbUser, dbPassword);
				
				List<DiaServico> dias = new ArrayList<DiaServico>();
				
				for (int i = 0; i < 7; i++) {
					Calendar date = Calendar.getInstance();
					DiaServico dia = new DiaServico();
					dia.setCor(request.getParameter("d" + i + "cor"));
					dia.setData(date);
					dias.add(dia);
					
					date.add(Calendar.DATE, i+7);
					dia.printCorData();
				}
				
				HashMap<String, String[]> parametersMap = (HashMap<String, String[]>) request.getParameterMap();
				
				for (String entry : parametersMap.keySet()) {
					if ((!entry.equals("opt")) && (!entry.substring(2).equals("cor"))) {
						int i = Integer.parseInt(entry.substring(1, 2));
						dias.get(i).qtdeServicoPut(entry.substring(2), Integer.parseInt(parametersMap.get(entry)[0]));
					}
				}
				
				List<List<Integer>> escalasDiasAnteriores = diaManager.getEscalasDiasAnteriores();
				
				TreeSet<TipoServico> tiposServico = new TreeSet<TipoServico>(new TipoServicoComparator());
				for (int i = 0; i < 7; i++) {
					for (String funcao : dias.get(i).getTipoServico()) {
						TipoServico tipoServico = new TipoServico(0, funcao, dias.get(i).getCor());
						tiposServico.add(tipoServico);
					}
				}
				
				JDBCFuncionarioHabilitadoDAO funcionariosHabilitados = new JDBCFuncionarioHabilitadoDAO();
				funcionariosHabilitados.open(databaseName, dbUser, dbPassword);
				HashMap<String, List<FuncionarioHabilitado>> filasFunionarios = new HashMap<String, List<FuncionarioHabilitado>>();
				
				for(TipoServico tipoServico : tiposServico){
					filasFunionarios.put(tipoServico.getFuncao(), funcionariosHabilitados.listarFuncionariosHabilitados(tipoServico));
					filasFunionarios.get(tipoServico.getFuncao()).sort(FuncionarioComparator.getInstance());
				}
				
				for (int i = 0; i < 7; i++) {
					List<Integer> funcionariosHoje;
					funcionariosHoje = dias.get(i).gerarEscalaDia(filasFunionarios, escalasDiasAnteriores);
					escalasDiasAnteriores.set(1, escalasDiasAnteriores.get(0));
					escalasDiasAnteriores.set(0, funcionariosHoje);
					
					dias.get(i).printEscalaDia();
				}
				
				for(DiaServico dia : dias) {
					dia.open(databaseName, dbUser, dbPassword);
					dia.gravarBD();	
				}
				
				request.setAttribute("alert", "Escala gerada com sucesso!");
				ServletContext app = this.getServletContext();
		        RequestDispatcher rd = app.getRequestDispatcher("/ConsultarEscalaADMIN.jsp");
		        rd.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else if(servOp.equals("Consultar")) {
			String dataInicial, dataFinal;
			dataInicial = (String)request.getParameter("dataInicial");
			dataFinal = (String)request.getParameter("dataFinal");
			List<DiaServico> dias = new ArrayList<DiaServico>();
			List<DiaServicoView> views = new ArrayList<DiaServicoView>();
			
			try {
				DiaServico diaManager = new DiaServico();
				diaManager.open(databaseName, dbUser, dbPassword);
				dias = diaManager.listarEscala(dataInicial, dataFinal);
				
				for(DiaServico dia : dias) {
					dia.open(databaseName, dbUser, dbPassword);
					views.add(dia.getView());
				}
				
				(new DiaServico()).close();
				
				request.setAttribute("views", views);
				ServletContext app = this.getServletContext();
		        RequestDispatcher rd = app.getRequestDispatcher("/ResultadoEscala.jsp");
		        System.out.println("Buscar");
		        rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("InserirFuncionarioADMIN.jsp");
			}
		}
	}

}
