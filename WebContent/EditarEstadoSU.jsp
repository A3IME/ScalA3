<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Funcionario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//Controle de acessos	
String nome = null;
String logId = null;
Cookie[] cookies = request.getCookies();
if(cookies != null){
for(Cookie cookie : cookies){
		System.out.println("#" + cookie.getName());
	    if(cookie.getName().equals("logId")){
	    	logId = cookie.getValue();
	    	//print para controle apenas. apagar do projeto final
	    	System.out.println(logId);
	    }
	}
}
if((session.getAttribute("logId") == null) || ((Integer)session.getAttribute("id") != 0)){
//if((!session.getAttribute("logId").equals(logId)) || ((Integer)session.getAttribute("id") != 0)){
	response.sendRedirect("TelaLogin.jsp");
}
else {
	nome = (String)session.getAttribute("nome");
}

Funcionario funcionario = null;
//funcionario = new Funcionario();
if(request.getAttribute("funcionario") != null)
{
	funcionario = (Funcionario)request.getAttribute("funcionario");
}
    
%>
	Ola <%=nome %>! 
	<a href="InserirAdminSU.jsp">Inserir</a>
	<a href="BuscarAdminSU.jsp">Buscar</a>
	<form action="/ScalA3/LoginServlet" method="post">
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="/ScalA3/SuperUsuarioServlet">
		<p>Estado</p>
		<p>Descrição</p>
		<textarea rows="5" cols="50" name="descEstado"><%if(funcionario.getEstado() != null){out.print(funcionario.getEstado().getDescricao());} %></textarea>
		<p>Início</p>
		<input type="text" name="inicioEstado" value="<%if(funcionario.getEstado() != null){out.print(funcionario.getEstado().getDataInicio());}%>"/>
		<p>Término</p>
		<input type="text" name="inicioEstado" value="<%if(funcionario.getEstado() != null){out.print(funcionario.getEstado().getDataTermino());}%>"/>
		<input type="hidden" name="id" value="<%out.print(funcionario.getId());%>">
		<input type="hidden" name="sOpt" value="concluir">
		<br>
		<input type="submit" name="opt" value="Editar estado">
	</form>

</body>
</html>