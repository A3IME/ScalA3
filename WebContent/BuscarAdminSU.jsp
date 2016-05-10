<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscar administrador</title>
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
%>
	Ola <%=nome %>! 
	<a href="InserirAdminSU.jsp">Inserir</a>
	<a href="#">Buscar</a>
	<form action="/ScalA3/LoginServlet" method="post">
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="/ScalA3/SuperUsuarioServlet" target="display">
		<p>Valor</p><input type="text" name="valor"/><br/>
		<p>Campo</p>
		<input type="radio" name="campo" value="id" checked>Id<br/>
		<input type="radio" name="campo" value="nome">Nome<br/>
		<input type="radio" name="campo" value="matricula">Matricula<br/>
		<input type="submit" name="opt" value="Buscar">
	</form>
	<iframe name="display" width="100%" height="100%" src="ResultadoBuscaSU.jsp"></iframe>

</body>
</html>