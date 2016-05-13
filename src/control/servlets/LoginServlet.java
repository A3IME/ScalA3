package control.servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import control.jdbc.DataBaseManager;
import control.jdbc.JDBCFuncionarioDAO;
import model.Funcionario;

/**
 * Servlet implementation class SudoSu
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String databaseName = "scala3";
		String dbUser = "postgres", dbPassword = "postgres";
		String user, password, logOp;
		
		logOp = request.getParameter("logOp");
		
		if(logOp.equals("Entrar")) {			
			try {
				user = request.getParameter("login");
				password = request.getParameter("senha");
	
				JDBCFuncionarioDAO funcionarioManager = new JDBCFuncionarioDAO();	
				funcionarioManager.open(databaseName, dbUser, dbPassword);
				
				//DataBaseManager.open(databaseName, user, password);
				List<Funcionario> funcionarios = null;
				funcionarios = funcionarioManager.listarLogin(user, password);
				funcionarioManager.close();
				
				if(funcionarios.size() != 1)
				{
					System.out.println(funcionarios.size());
					request.setAttribute("falha_autenticacao", "Falha na autenticação.");
					ServletContext app = this.getServletContext();
			        RequestDispatcher rd = app.getRequestDispatcher("/TelaLogin.jsp");
			        rd.forward(request, response);
			        //response.sendRedirect("TelaLogin.jsp");
				}
				else
				{
					Funcionario funcionario = funcionarios.get(0);
					HttpSession session = request.getSession();
					session.setAttribute("id", funcionario.getId());
					session.setAttribute("adm", funcionario.getEadmin());
					session.setAttribute("nome", funcionario.getNomeCompleto());
					//FAZER O HASH DO EMAIL ABAIXO
					session.setAttribute("logId", funcionario.getEmail());
					//HASH DO EMAIL
					Cookie logId = new Cookie("logId", funcionario.getEmail());
					logId.setMaxAge(5*60);
					response.addCookie(logId);
					
					if(funcionario.getId() == 0)
					{
						//FAZER SUPER USUARIO
						//Máximo de 5 min de inatividade
						session.setMaxInactiveInterval(5*60);
						response.sendRedirect("InserirAdminSU.jsp");
					}
					else if(funcionario.getEadmin())
					{
						//Máximo de 30 min de inatividade
						session.setMaxInactiveInterval(30*60);
						//response.sendRedirect(PAGINA ADMINISTRADOR);
						//FAZER ADMIN
					}
					else
					{
						//Máximo de 30 min de inatividade
						session.setMaxInactiveInterval(30*60);
						//response.sendRedirect(PAGINA FUNCIONARIO);
						//FAZER FUNCIONARIO COMUM
					}
					
				}
				
		        /*ServletContext app = this.getServletContext(); 
		        RequestDispatcher rd = app.getRequestDispatcher("/CadastroFuncionário.html");
		        rd.forward(request, response);*/
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				request.setAttribute("falha_autenticacao", "Impossível se conectar ao banco de dados.");
				ServletContext app = this.getServletContext();
		        RequestDispatcher rd = app.getRequestDispatcher("/TelaLogin.jsp");
		        rd.forward(request, response);
			}
		}
		else {
			HttpSession session = request.getSession(false);
			session.invalidate();
			response.sendRedirect("TelaLogin.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
