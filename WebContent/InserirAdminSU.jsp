<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserir administrador</title>
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
	<a href="#">Inserir</a>
	<a href="BuscarAdminSU.jsp">Buscar</a>
	<form action="/ScalA3/LoginServlet" method="post">
		<!-- Se mudar o value abaixo deve mudar no servlet -->
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="ControleSU">
		<p>Nome completo</p>
		<input type="text" name="nome"/>
		<p>Matricula</p>
		<input type="text" name="matricula"/>
		<p>Email</p>
		<input type="text" name="email"/>
		<p>Telefone</p>
		<input type="text" name="telefone"/>
		<p>Senha</p>
		<input type="password" name="senha"/><br/>
		<input type="hidden" name="origem" value="inserir">
		<input type="submit" value="Cadastrar"/>
	</form>
	

</body>
</html>