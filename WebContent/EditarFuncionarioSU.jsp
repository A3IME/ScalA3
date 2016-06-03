<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Funcionario"%>
<!DOCTYPE html>
<html>
	<head>
		<meta HTTP-EQUIV=Expires CONTENT="0">   
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">  
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Editar</title>

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
<!-- Barra de Navegação-->
		
		<nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container-fluid">
		  	<div class="navbar-header">
				<img class="navbar-brand" src="images/scala3.png" height="100px" width="100px" />
			</div>
		       
		   <div class="collapse navbar-collapse" >
		   	<ul class="nav navbar-nav">
				<li><a href="InserirAdminSU.jsp">Inserir</a></li>
				<li><a href="BuscarAdminSU.jsp">Buscar</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="TelaLogin.jsp"><span class="glyphicon glyphicon-log-in"> Login</span></a></li>
			
			</ul>
		   </div>
		   
		</nav>
		
		<!-- Editar Funcionário -->
		
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<form method="post" action="/ScalA3/SuperUsuarioServlet" class="form-group form-horizontal" style="padding-top:80px">
					
				<h4 class="center text-center">Cadastro de Funcionário</h4>
					<div class="form-group" style="padding-top:20px">
						<label class="control-label col-sm-2" for="nome">Nome Completo:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="nome" name="nome" value="<%out.print(funcionario.getNomeCompleto());%>" placeholder="Insira o nome"><br>
						</div>
					</div>					
					<div class="form-group" >
						<label class="control-label col-sm-2" for="matricula">Matrícula:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="matricula" name="matricula" value="<%out.print(funcionario.getMatricula());%>" placeholder="Informe a matrícula"><br>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email" name="email" value="<%out.print(funcionario.getEmail());%>" placeholder="Insira o email"><br>
						</div>
					</div>					
					<div class="form-group">
						<label class="control-label col-sm-2" for="telefone">Telefone:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="telefone" name="telefone" value="<%out.print(funcionario.getTelefone());%>" placeholder="Insira o telefone"><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="telefone">Habilitação:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="telefone" name="habilitacao" value="<%out.print(funcionario.getHabilitacao());%>" placeholder="Insira habilitação"><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="admin">Admin:</label>
						<div class="col-sm-10">
							<input type="checkbox" class="form-control" id="admin" name="eadmin" value="true"<%if(funcionario.getEadmin()){out.print("checked");}%>>
						</div>
					</div>
					<input type="hidden" name="id" value="<%out.print(funcionario.getId());%>">
					<input type="hidden" name="sOpt" value="concluir">
					<br>
					<div class="form-group">
						<div class="form-actions">
							<center>
							<button type="submit" name="opt" value="Editar dados" class="btn btn-success">Enviar</button>
							<button type="submit" class="btn btn-danger">Cancelar</button>
							</center>
						</div>
					</div>
				</form>
			</div>
			<div class="col-lg-4"></div>
		</div>

<!-- Código Anterior Comentado
 
	<a href="InserirAdminSU.jsp">Inserir</a>
	<a href="BuscarAdminSU.jsp">Buscar</a>
	<form action="/ScalA3/LoginServlet" method="post">
		<input type="submit" name="logOp" value="Sair">
	</form>
	<hr/>
	<br/>
	<form method="post" action="/ScalA3/SuperUsuarioServlet">
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
		<p>Admin</p>
		<input type="checkbox" name="eadmin" value="true"<%if(funcionario.getEadmin()){out.print("checked");}%>>
		<input type="hidden" name="id" value="<%out.print(funcionario.getId());%>">
		<input type="hidden" name="sOpt" value="concluir">
		<br>
		<input type="submit" name="opt" value="Editar dados">
	</form>
-->	

</body>
</html>