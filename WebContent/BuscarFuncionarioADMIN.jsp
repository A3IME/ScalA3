<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Cadastro</title>

	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/style2.css" rel="stylesheet">

		<script src="js/jquery.js"></script>		
		<script src="js/bootstrap.min.js"></script>
		<script src="js/npm.js"></script>
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
	<!-- Barra de Navegação-->
		
		<nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container-fluid">
		  	<div class="navbar-header">
				<img class="navbar-brand" src="images/scala3.png" height="100px" width="100px" />
			</div>
		       
		   <div class="collapse navbar-collapse" >
		   	<ul class="nav navbar-nav">
				<li><a href="InserirFuncionarioADMIN.jsp">Inserir</a></li>
				<li><a href="#">Buscar</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="TelaLogin.jsp"><span class="glyphicon glyphicon-log-in"> Sair</span></a></li>
			
			</ul>
		   </div>
		   
		</nav>
				
		<!-- Buscar Admin -->
		
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<form action="/ScalA3/AdminServlet" method="post" class="form-group form-horizontal" style="padding-top:80px" target="display">
					
					<h4 class="center text-center" style="color:green">Buscar Funcionário</h4>
					<center>
					
						<div class="input-group" style="padding-top:30px;">
					      <input type="text"  class="form-control"  placeholder="Buscar por..." name="valor">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="submit" name="opt" value="Buscar">Buscar</button>
					      </span>
					    </div>
			        	<div class="form-group">
							<label class="radio-inline"><input type="radio" name="campo" value="id" checked>Id</label>
							<label class="radio-inline"><input type="radio" name="campo" value="nome">Nome</label>
							<label class="radio-inline"><input type="radio" name="campo" value="matricula">Matrícula</label>
						</div>
					</center>
				</form>
			</div>
			<div class="col-lg-4"></div>
		</div>
		<iframe name="display" width="100%" height="500px" src="ResultadoBuscaSU.jsp" style="background-color: white"></iframe>
<!-- 
	<a href="InserirFuncionarioADMIN.jsp">Inserir</a>
	<a href="#">Buscar</a>
	<form action="/ScalA3/LoginServlet" method="post">
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="/ScalA3/AdminServlet" target="display">
		<p>Valor</p><input type="text" name="valor"/><br/>
		<p>Campo</p>
		<input type="radio" name="campo" value="id" checked>Id<br/>
		<input type="radio" name="campo" value="nome">Nome<br/>
		<input type="radio" name="campo" value="matricula">Matricula<br/>
		<input type="submit" name="opt" value="Buscar">
	</form>
	<iframe name="display" width="100%" height="100%" src="ResultadoBuscaSU.jsp"></iframe>
-->
</body>
</html>