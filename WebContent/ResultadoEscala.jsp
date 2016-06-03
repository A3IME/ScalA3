<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Funcionario" import="java.util.ArrayList"
	import="java.util.List,model.DiaServicoView" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/style2.css" rel="stylesheet">

		<script src="js/jquery.js"></script>		
		<script src="js/bootstrap.min.js"></script>
		<script src="js/npm.js"></script>
		
		<style>
			th {
				text-align:center;
				color: green;
			}
		</style>
	</head>
<body>
<% List<DiaServicoView> views = (List<DiaServicoView>)request.getAttribute("views"); %>
	<div class="container-fluid text-center bs-docs-section" style="padding-top:100px">
		<div class="row content">
			<div class="col-sm-2 sidenav"></div>
				<div class="col-sm-8">
				<% for (DiaServicoView view : views) { %>
					<table class="table table striped table-hover text-center well">
						<thead> 
							<th colspan="4">Data: __/__/__</th><br>
						</thead>
						<tbody>
							<tr>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td>Mopa</td>
								<td>MOPAA</td>
							</tr>
						</tbody>
					</table>
					<% } %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>