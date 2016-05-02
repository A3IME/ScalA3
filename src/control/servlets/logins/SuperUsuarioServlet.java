package control.servlets.logins;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.jdbc.DataBaseManager;

/**
 * Servlet implementation class SudoSu
 */
@WebServlet("/SuperUsuarioServlet")
public class SuperUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String databaseName = "scala3";
		String user, password;
		
		try {
			user = request.getParameter("login");
			password = request.getParameter("senha");
			
			DataBaseManager.open(databaseName, user, password);
			
	        ServletContext app = this.getServletContext(); 
	        RequestDispatcher rd = app.getRequestDispatcher("/CadastroFuncionário.html");
	        rd.forward(request, response);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			request.setAttribute("falha_autenticacao", "Falha na autenticação.");
			ServletContext app = this.getServletContext();
	        RequestDispatcher rd = app.getRequestDispatcher("/TelaLogin.jsp");
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
