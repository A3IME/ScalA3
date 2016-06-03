package control.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
import model.DiaServicoView;

/**
 * Servlet implementation class FuncionarioServlet
 */
@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat parser  = null;
       
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
		
		if(servOp.equals("Consultar")) {
			String dataInicial, dataFinal;
			dataInicial = (String)request.getParameter("dataInicial");
			dataFinal = (String)request.getParameter("dataFinal");
			List<DiaServico> dias = new ArrayList<DiaServico>();
			List<DiaServicoView> views = new ArrayList<DiaServicoView>();
			
			System.out.println(dataInicial);
			System.out.println(dataFinal);
			
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
				response.sendRedirect("ConsultarFUNC.jsp");
			}
		}
	}

}
