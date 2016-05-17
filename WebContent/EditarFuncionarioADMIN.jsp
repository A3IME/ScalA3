<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Funcionario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar administrador</title>
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
	<a href="InserirFuncionarioADMIN.jsp">Inserir</a>
	<a href="BuscarFuncionarioADMIN.jsp">Buscar</a>
	<form action="/ScalA3/LoginServlet" method="post">
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="/ScalA3/AdminServlet">
		<p>Nome completo</p>
		<input type="text" name="nome" value="<%out.print(funcionario.getNomeCompleto());%>"  />
		<p>Matricula</p>
		<input type="text" name="matricula" value="<%out.print(funcionario.getMatricula());%>"/>
		<p>Email</p>
		<input type="text" name="email" value="<%out.print(funcionario.getEmail());%>"/>
		<p>Telefone</p>
		<input type="text" name="telefone" value="<%out.print(funcionario.getTelefone());%>"/>
		<p>Habilitação</p>
		<input type="text" name="habilitacao" value="<%out.print(funcionario.getHabilitacao());%>"/>
		<input type="hidden" name="id" value="<%out.print(funcionario.getId());%>">
		<input type="hidden" name="sOpt" value="concluir">
		<br>
		<input type="submit" name="opt" value="Editar dados">
	</form>
	

</body>
</html>