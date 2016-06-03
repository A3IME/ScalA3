<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="control.jdbc.JDBCTipoServicoDAO, java.util.List, java.util.HashMap, java.util.Calendar"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<form action="AdminServlet" method="post">
<%
  for (int i = 0; i < 7; i++) {
%>
  <div id=<%=("\"d" + i + "\"") %>>
  <p>Dia <%=dia0.getTime()%></p>
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
  </div>
<%
dia0.add(Calendar.DATE, +1);
} %>
<input type="submit" name="opt" value="Gerar"/>
</form>
</body>