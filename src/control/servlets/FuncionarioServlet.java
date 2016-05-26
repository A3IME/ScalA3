package control.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DiaServico;

/**
 * Servlet implementation class FuncionarioServlet
 */
@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SimpleDateFormat parser  = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FuncionarioServlet() {
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
		
		if(servOp.equals("Buscar")) {
			String dataInicial, dataFinal;
			dataInicial = (String)request.getParameter("dataInicial");
			dataFinal = (String)request.getParameter("dataFinal");
			List<DiaServico> dias = null;
			dias = new ArrayList<DiaServico>();
			
			SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			String dataInicialSQL = formater.format(parser.parse(dataInicial));
			String dataFinalSQL = formater.format(parser.parse(dataFinal));
			
			try {
				(new DiaServico()).open(databaseName, dbUser, dbPassword);
				dias = (new DiaServico()).listarEscala(dataInicial, dataFinal);
				
				(new DiaServico()).close();
				
				//TERMINAR \/
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
	}

}
