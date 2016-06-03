<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Login</title>
		
		
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/style2.css" rel="stylesheet">

	     
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	    <style>
			#telaLogin {
			  	border-style:solid; 
				border-color:green; 
						  }
		</style>
		
	</head>
<body>
<body>
	
		<!--Tela Login -->
		<div class="container-fluid">
		<div class="row" style="padding-top:100px">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="bs-component">
	              <div class="jumbotron" id="telaLogin" style="border-style:solid; border-color:green; backgroung">
	                <img src="images/scala3.png" width="300px" heigth="800px" class="center-block">
	                <form action="/ScalA3/LoginServlet" method="post" class="form-horizontal">
					  <fieldset>
					    <div class="form-group">
					      <label for="inputEmail" class="col-lg-4 control-label">Usu�rio</label>
					      <div class="col-lg-5">
					        <input class="form-control" id="inputEmail" name="login" placeholder="Usu�rio" type="text">
					      </div>
					    </div>
					    <div class="form-group">
					      <label for="inputPassword" class="col-lg-4 control-label">Senha</label>
					      <div class="col-lg-5">
					        <input class="form-control" id="inputPassword" name="senha" placeholder="Senha" type="password">
					        <div class="checkbox">
					          <label>
					            <input type="checkbox"> Lembrar-me
					          </label>
					        </div>
					      </div>
					    </div>
					    
					    <div class="form-group">
					      <div class="col-lg-5"></div>
						  <div class="col-lg-7">
					        
					        <button type="submit" name="logOp" value="Entrar" class="btn btn-primary" >Entrar</button>
					      </div>
					    </div>
					  </fieldset>
					  	<p>
						<%
			            	out.println((request.getAttribute("falha_autenticacao") == null) ? ("") : (request.getAttribute("falha_autenticacao")));
			        	%>
			        	</p>
					</form> 
	                
	              </div>
				</div>
            </div>
			<div class="col-lg-12"></div>
		</div>
		</div>



</body>
</html>
