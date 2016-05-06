<!DOCTYPE html>
<html>
<head>
	<title>ScalA3</title>
	<link type="text/css"  rel="stylesheet" href="/ScalA3/css/ScalA3.css" />
</head>
<body>
	<div>
		<img src="/ScalA3/images/scala3.png" id="img"/>
		<form action="/ScalA3/LoginServlet" method="post" class="center" >
			<table>
				<tbody>
					<tr>
						<td style="padding-left:100px;">Login:</td>
						<td><input type="text" name="login"></td>
					</tr>	
				 	<tr>
						<td style="padding-left:100px;">Senha:</td>
						<td><input type="password" size="60" name="senha"></td>	
					</tr>
					<tr>
						<td colspan="2" style="text-align:center">
							<!-- Se mudar o value abaixo deve mudar no servlet -->
							<input type="submit" name="logOp" value="Entrar">
							<!--<button type="submit" name="Enviar">Entrar</button>-->
						</td>
					</tr>
				</tbody>
			</table>
		<p>
		<%
            out.println((request.getAttribute("falha_autenticacao") == null) ? ("") : (request.getAttribute("falha_autenticacao")));
        %>
        </p>
		</form>
		
	</div>


</body>
</html>
