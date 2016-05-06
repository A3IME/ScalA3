<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscar administrador</title>
</head>
<body>
	<a href="InserirAdmin.html">Inserir</a>
	<a href="#">Buscar</a>
	<hr/>
	<br/>
	<form method="post" action="ControleSU" target="display">
		<p>Valor</p><input type="text" name="valor"/><br/>
		<p>Campo</p>
		<input type="radio" name="campo" value="id">Id<br/>
		<input type="radio" name="campo" value="nome">Nome<br/>
		<input type="radio" name="campo" value="matricula">Matricula<br/>
		<input type="hidden" name="origem" value="busca">
		<input type="submit" value="Pesquisar">
	</form>
	<iframe name="display" width="100%" height="100%"></iframe>

</body>
</html>