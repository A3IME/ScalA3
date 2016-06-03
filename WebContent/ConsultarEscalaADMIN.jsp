<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Visualizar Escala</title>
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
%>
	Ola <%=nome %>! 
	
	<form action="/ScalA3/LoginServlet" method="post">
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="/ScalA3/AdminServlet" target="display">
		<p>Data inicial</p><input type="date" name="dataInicial"/><br/>
		<p>Data final</p><input type="date" name="dataFinal"/><br/>
		<input type="submit" name="opt" value="Consultar">
	</form>
	<iframe name="display" width="100%" height="100%" src="ResultadoEscala.jsp"></iframe>

</body>
</html>