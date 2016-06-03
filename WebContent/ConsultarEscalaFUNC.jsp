<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta HTTP-EQUIV=Expires CONTENT="0">   
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">  
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Escala</title>

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
if((session.getAttribute("logId") == null) || ((Integer)session.getAttribute("id") == 0) || ((Boolean)session.getAttribute("adm") == true) ){
//if((!session.getAttribute("logId").equals(logId)) || ((Integer)session.getAttribute("id") != 0)){
	response.sendRedirect("TelaLogin.jsp");
}
else {
	nome = (String)session.getAttribute("nome");
}
%>
	Ola <%=nome %>!
	<body>
	
		<!-- Barra de Navegação-->
		
		<nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container-fluid">
		  	<div class="navbar-header">
			    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			    </button>
				<img class="navbar-brand" src="images/scala3.png" height="100px" width="100px" />
			</div>
		       
		   <div class="collapse navbar-collapse" >
		   	<ul class="nav navbar-nav">
				<li class="active"><a href="#">Consultar Escala</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="TelaLogin.jsp"><span class="glyphicon glyphicon-log-in">Sair</span></a></li>
			
			</ul>
		   </div>
 
		</nav>
		
		<!-- Formulário de Consulta de Escala-->
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4" style="padding-top:100px">
				 <form action="/ScalA3/FuncionarioServlet" method="post" class="form-horizontal" role="form" target="display">
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="dtInicial">Data Inicial:</label>
				    <div class="col-sm-10">
				      <input type="date" class="form-control" id="dtInicial" placeholder="dd/mm/yyyy" name="dataInicial">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="dtFInal">Data Final:</label>
				    <div class="col-sm-10">
						<input type="date" class="form-control" id="dtFinal" placeholder="dd/mm/yyyy" name="dataFinal">
				  	</div>
				  	<div class="form-group">
						<div class="form-actions">
							<center>
							<button type="submit" class="btn btn-success" name="opt" value="Consultar">Enviar</button>
							<button type="submit" class="btn btn-danger">Cancelar</button>
							</center>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
		<iframe name="display" width="100%" height="500px" src="ResultadoEscala.jsp"></iframe>
 	<!-- 
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
	-->
</body>
</html>