package control.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.jdbc.JDBCAdministradorDAO;
import control.jdbc.JDBCEstadoDAO;
import control.jdbc.JDBCFuncionarioDAO;
import model.Administrador;
import model.Estado;
import model.Funcionario;
/**
 * Servlet implementation class SuperUsuarioServlet
 */
@WebServlet("/SuperUsuarioServlet")
public class S_usuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_usuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			//senha = (String)request.getParameter("senha");
			
			try {
				JDBCAdministradorDAO administradorManager = new JDBCAdministradorDAO();
				administradorManager.open(databaseName, dbUser, dbPassword);
				
				Administrador administrador = new Administrador(nome, Integer.parseInt(matricula), email, telefone, 255, null);
				administradorManager.inserir(administrador);
				administradorManager.close();
				System.out.println("Inserido!");
				response.sendRedirect("InserirAdminSU.jsp");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("InserirAdminSU.jsp");
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
					funcionarios = funcionarioManager.listaAproximadaPorId(valor, true);  
				}
				else if(campo.equals("nome")){
					funcionarios = funcionarioManager.listaAproximadaPorNome(valor, true);
				}
				else if(campo.equals("matricula")){
					funcionarios = funcionarioManager.listaAproximadaPorMatricula(valor, true);
				}
				funcionarioManager.close();
				request.setAttribute("funcionarios", funcionarios);
				ServletContext app = this.getServletContext();
		        RequestDispatcher rd = app.getRequestDispatcher("/ResultadoBuscaSU.jsp");
		        System.out.println("Buscar");
		        rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("BuscarAdminSU.jsp");
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
			        RequestDispatcher rd = app.getRequestDispatcher("/EditarFuncionarioSU.jsp");
			        System.out.println("Editar inicio");
			        rd.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e.getMessage());
					response.sendRedirect("BuscarAdminSU.jsp");
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
				System.out.println(request.getParameter("eadmin"));
				if(request.getParameter("eadmin") != null) {
					eadmin = true;
				}
				else {
					eadmin = false;
				}
				
				Funcionario funcionario = new Funcionario(id, nome, matricula, email, telefone, habilitacao, null, eadmin);
				
				try {
					JDBCFuncionarioDAO funcionarioManager = new JDBCFuncionarioDAO();
					funcionarioManager.open(databaseName, dbUser, dbPassword);
				
					funcionarioManager.editar(funcionario);
					
					funcionarioManager.close();
			        System.out.println("Editar concluido");
					response.sendRedirect("BuscarAdminSU.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e.getMessage());
					response.sendRedirect("BuscarAdminSU.jsp");
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
				response.sendRedirect("BuscarAdminSU.jsp");
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("InserirAdminSU.jsp");
			}
		}
		else if(servOp.equals("Editar estado")) {
			System.out.println("Editar estado");
			response.sendRedirect("EditarEstadoSU.jsp");;
		}
	}

}
