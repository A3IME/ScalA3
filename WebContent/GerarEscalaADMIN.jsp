<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="control.jdbc.JDBCTipoServicoDAO, java.util.List, java.util.HashMap, java.util.Calendar, java.text.SimpleDateFormat"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta HTTP-EQUIV=Expires CONTENT="0">   
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">  
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Inserir</title>

	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/style2.css" rel="stylesheet">

		<script src="js/jquery.js"></script>		
		<script src="js/bootstrap.min.js"></script>
		<script src="js/npm.js"></script>
<script type="text/javascript">
<%
  JDBCTipoServicoDAO tipoServicoDao = new JDBCTipoServicoDAO();
  tipoServicoDao.open();
  //List<String> cores = tipoServicoDao.listCores();
  HashMap<String, List<String>> cores_funcao = tipoServicoDao.mapearTipos();
  Calendar dia0 = Calendar.getInstance();
  dia0.add(Calendar.DATE, 7);
%>
var cor_funcao = [
<%
  for (String cor: cores_funcao.keySet()) {
%>
{cor:<%=("\"" + cor + "\"")%>,
  funcoes:[
  <%
    for (String funcao: cores_funcao.get(cor)) {
  %>
  <%=("\"" + funcao + "\"")%>, 
  <%
    }
  %>
  ]
},
<%
  }
%>
];

function checkSelect(el, divEl) {
	var funcoes;
	for (i = 0; i < cor_funcao.length; i++) {
		if (cor_funcao[i].cor == el.options[el.selectedIndex].text) {
			funcoes = cor_funcao[i].funcoes;
			break;
		}
	}
	
	var deleted = document.getElementById(divEl.getAttribute("id") + "funcao");
	while (deleted.hasChildNodes()) {  
	    deleted.removeChild(deleted.firstChild);
	}
	
	for (i = 0; i < funcoes.length; i++) {
		var divFuncao = document.createElement("DIV");
		divFuncao.setAttribute("id", divEl.getAttribute("id") + funcoes[i]);
		var txtFuncao = document.createTextNode(funcoes[i]);
		var inputFuncao = document.createElement("INPUT");
		inputFuncao.setAttribute("type", "number");
		inputFuncao.setAttribute("name", divEl.getAttribute("id") + funcoes[i]);
		inputFuncao.setAttribute("value", 0);
		divFuncao.appendChild(txtFuncao);
		divFuncao.appendChild(inputFuncao);
		deleted.appendChild(divFuncao);
	}
   //if (el.options[el.selectedIndex].text.length > 0)
    //document.getElementById('text1').style.display = 'block';
  //else
   //document.getElementById('text1').style.display = 'none';
}
</script>
</head>
<body>
<%
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
<!-- Barra de Navegação-->
		
		<nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container-fluid">
		  	<div class="navbar-header">
				<img class="navbar-brand" src="images/scala3.png" height="100px" width="100px" />
			</div>
		       
		   <div class="collapse navbar-collapse" >
		   	<ul class="nav navbar-nav">
				<li><a href="InserirFuncionarioADMIN.jsp">Inserir</a></li>
				<li><a href="BuscarFuncionarioADMIN.jsp">Buscar</a></li>
				<li><a href="ConsultarEscalaADMIN.jsp">Consultar Escala</a>
				<li><a href="GerarEscalaADMIN.jsp">Gerar Escala</a>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="TelaLogin.jsp"><span class="glyphicon glyphicon-log-in">Sair</span></a></li>
			
			</ul>
		   </div>
		  </div>
		</nav>

<form action="AdminServlet" method="post" style="padding-top:70px; padding-left:20px">
<%
  for (int i = 0; i < 7; i++) {
%>
  <div id=<%=("\"d" + i + "\"") %>>
  <p>Dia <%=(new SimpleDateFormat("dd/MM/yyyy")).format(dia0.getTime())%></p>
  Selecione a cor:
  <select onchange="checkSelect(this, this.parentElement)" name=<%=("\"d" + i + "cor\"")%>>
    <option></option>
    <%
      for (String cor : cores_funcao.keySet()) {
    %>
    <option><%= cor %></option>
    <%
      }
    %>
  </select>
  <div id=<%=("\"d" + i + "funcao\"") %>></div>
  
<%
dia0.add(Calendar.DATE, +1);
} %>
<input type="submit" name="opt" value="Gerar"/>
</div>
</form>

</body>