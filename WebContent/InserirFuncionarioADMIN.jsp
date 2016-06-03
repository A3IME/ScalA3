<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Inserir</title>

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
//if((!session.getAttribute("logId").equals(logId)) || ((Integer)session.getAttribute("id") != 0)){
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
				<li><a href="#">Inserir</a></li>
				<li><a href="BuscarFuncionarioADMIN.jsp">Buscar</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="ScalA3/TelaLogin.jsp"><span class="glyphicon glyphicon-log-in"> Login</span></a></li>
			
			</ul>
		   </div>
		   
		</nav>
		
		<!-- Inserir Funcionário -->
		
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<form method="post" action="/ScalA3/AdminServlet" class="form-group form-horizontal" style="padding-top:80px">
					<h4 style="color:green;">Cadastrar Funcionário</h4>
					<div class="form-group" style="padding-top:20px">
						<label class="control-label col-sm-2" for="matricula">Matrícula:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="matricula" name="matricula" placeholder="Informe a matrícula"><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="nome">Nome:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="nome" name="nome" placeholder="Insira o nome"><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email" name="email" placeholder="Insira o email"><br>
						</div>
					</div>					
					<div class="form-group">
						<label class="control-label col-sm-2" for="telefone">Telefone:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="telefone" name="telefone" placeholder="Insira o telefone"><br>
						</div>
					</div>
					<div class="form-group">
						<div class="form-actions">
							<center>
							<button type="submit" class="btn btn-success" name="opt" value="Inserir">Enviar</button>
							<button type="submit" class="btn btn-danger">Cancelar</button>
							</center>
						</div>
					</div>
				</form>
			</div>
			<div class="col-lg-4"></div>
		</div>
<!-- 
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
-->

</body>
</html>