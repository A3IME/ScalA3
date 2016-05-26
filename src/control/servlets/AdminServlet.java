package control.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tools.FuncionarioComparator;
import control.jdbc.JDBCAdministradorDAO;
import control.jdbc.JDBCFuncionarioDAO;
import control.jdbc.JDBCFuncionarioHabilitadoDAO;
import model.Administrador;
import model.DiaServico;
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
				
				Funcionario funcionario = new Funcionario(nome, Integer.parseInt(matricula), email, telefone, 0, null);
				funcionarioManager.inserir(funcionario);
				funcionarioManager.close();
				System.out.println("Inserido!");
				response.sendRedirect("InserirFuncionarioADMIN.jsp");
				
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
					response.sendRedirect("BuscarFuncionarioADMIN.jsp");
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
				response.sendRedirect("BuscarFuncionarioADMIN.jsp");
					
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
				JDBCFuncionarioHabilitadoDAO funcionariosHabilitados = new JDBCFuncionarioHabilitadoDAO();
				funcionariosHabilitados.open(databaseName, dbUser, dbPassword);
				HashMap<String, List<FuncionarioHabilitado>> filasFunionarios = new HashMap<String, List<FuncionarioHabilitado>>();
				
				int i = 0;
				for(String servico : servicos){
					tipoServico.setFuncao(servico);
					tipoServico.setId(ids.get(i));
					//^ FAZER METODO Q PEGA ID DO BD E SALVA DIRETO
					filasFunionarios.put(servico, funcionariosHabilitados.listarFuncionariosHabilitados(tipoServico));
					filasFunionarios.get(servico).sort(FuncionarioComparator.getInstance());
					i++;
				}
		
				Calendar data = Calendar.getInstance();
				HashMap<String, Integer> qtds = new HashMap<String, Integer>();
				qtds.put(servicos.get(0), 1);
				qtds.put(servicos.get(1), 1);
				DiaServico diaServico = new DiaServico(0, cor, data, servicos, qtds);
				
				diaServico.open(databaseName, dbUser, dbPassword);
				
				List<List<Integer>> pessoalServicoAnteriores = new ArrayList<List<Integer>>();
				Calendar dia = Calendar.getInstance();
				
				
				for(i = 0; i < 2; i++){
					dia.add(Calendar.DATE, -1);
					pessoalServicoAnteriores.add(diaServico.getFuncionariosServicoDia(dia));
				}
				
				diaServico.gerarEscalaDia(filasFunionarios, pessoalServicoAnteriores);
				diaServico.printEscalaDia();
				
						
			} catch (SQLException e) {
	
			}
			
		}
	}

}
