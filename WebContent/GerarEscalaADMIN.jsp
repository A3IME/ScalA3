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
	
	var deleted = document.getElementById(divEl.getAttribute("id") + "input_funcao");
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

<form action="AdminServlet">
<%
  for (int i = 0; i < 7; i++) {
%>
  <div id=<%=("\"d" + i + "\"") %>>
  <p>Dia <%=dia0.getTime()%></p>
  Selecione a cor:
  <select onchange="checkSelect(this, this.parentElement)" name=<%=("\"d" + i + "select\"")%>>
    <option></option>
    <%
      for (String cor : cores_funcao.keySet()) {
    %>
    <option><%= cor %></option>
    <%
      }
    %>
  </select>
  <div id=<%=("\"d" + i + "input_funcao\"") %>></div>
  </div>
<%
dia0.add(Calendar.DATE, +1);
} %>
<input type="submit" name="submit"/>
</form>
</body>