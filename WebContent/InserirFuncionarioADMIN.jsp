<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	    	//print para controle apenas. apagar do projeto final
	    	System.out.println(logId);
	    }
	}
}
if((session.getAttribute("logId") == null) || ((Integer)session.getAttribute("id") == 0) || ((Boolean)session.getAttribute("adm") == false) ){
//if((!session.getAttribute("logId").equals(logId)) || ((Integer)session.getAttribute("id") != 0)){
	response.sendRedirect("TelaLogin.jsp");
}
else {
	nome = (String)session.getAttribute("nome");
}
%>
	Ola <%=nome %>! 
	<a href="#">Inserir</a>
	<a href="BuscarFuncionarioADMIN.jsp">Buscar</a>
	<form action="/ScalA3/LoginServlet" method="post">
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="/ScalA3/AdminServlet">
		<p>Nome completo</p>
		<input type="text" name="nome"/>
		<p>Matricula</p>
		<input type="text" name="matricula"/>
		<p>Email</p>
		<input type="text" name="email"/>
		<p>Telefone</p>
		<input type="text" name="telefone"/>
		<input type="submit" name="opt" value="Inserir"/>
	</form>
	

</body>
</html>