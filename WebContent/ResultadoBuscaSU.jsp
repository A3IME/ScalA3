<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Funcionario" import="java.util.ArrayList"
	import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Matrícula</td>
			<td>Email</td>
			<td>Telefone</td>
			<td>Admin</td>
			<td>Excluir</td>
			<td>Editar dados</td>	
			<td>Editar estado</td>	
		</tr>
		<%
			List<Funcionario> funcionarios = null;
			//funcionarios = new ArrayList<Funcionario>();
			if(request.getAttribute("funcionarios") != null)
			{
				funcionarios = (ArrayList<Funcionario>)request.getAttribute("funcionarios");	
			    
				for(Funcionario funcionario : funcionarios)
				{
					out.println("<tr>");
					out.println("<td>" + funcionario.getId() + "</td>");
					out.println("<td>" + funcionario.getNomeCompleto() + "</td>");
					out.println("<td>" + funcionario.getMatricula() + "</td>");
					out.println("<td>" + funcionario.getEmail() + "</td>");
					out.println("<td>" + funcionario.getTelefone() + "</td>");
					if (funcionario.getEadmin()){
						out.println("<td>Sim</td>");	
					}
					else {
						out.println("<td>Não</td>");
					}
					out.print("<td><form method=\"post\" action=\"/ScalA3/SuperUsuarioServlet\" target=\"_parent\"><input type=\"hidden\" name=\"id\" value=\"" + funcionario.getId() + "\">");
					out.println("<input type=\"submit\" name=\"opt\" value=\"Excluir\"></form></td>");
					out.print("<td><form method=\"post\" action=\"/ScalA3/SuperUsuarioServlet\" target=\"_parent\"><input type=\"hidden\" name=\"id\" value=\"" + funcionario.getId() + "\">");
					out.print("<input type=\"hidden\" name=\"sOpt\" value=\"iniciar\">");
					out.println("<input type=\"submit\" name=\"opt\" value=\"Editar dados\"></form></td>");
					out.print("<td><form method=\"post\" action=\"/ScalA3/SuperUsuarioServlet\" target=\"_parent\"><input type=\"hidden\" name=\"id\" value=\"" + funcionario.getId() + "\">");
					out.print("<input type=\"hidden\" name=\"sOpt\" value=\"iniciar\">");
					out.println("<input type=\"submit\" name=\"opt\" value=\"Editar estado\"></form></td>");
					//out.println("<td>" + "EDITAR" + "</td>");
					out.println("</tr>");
				}
			}
		%>
	</table>

</body>
</html>