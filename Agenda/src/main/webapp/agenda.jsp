<!--configurar o jsp-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@page import="model.JavaBeans" %>
	<%@page import="java.util.ArrayList" %>
	
	<% 
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
	%>
	
	<%--
	for(int i = 0; i<lista.size(); i++){
		out.println(lista.get(i).getIdcon());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getFone());
		out.println(lista.get(i).getEmail());
	}
	--%>
	
<!DOCTYPE html>
<html lang="ptbr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Agenda de contatos</title>
<link rel="stylesheet" type="text/css" href="Style.css">
<link rel="icon" href="Imagens/favicon.png">
</head>
<body class="container">

	<div class="alinharmeio">
		<header><h1>Agenda de contatos</h1></header>
		
		 <div class="botoesAgenda1">
		 	<a href="novo.html" class="botao1" style="text-align: center;">Novo contato</a>
		 	<a href="report" class="botao1">Relatório</a>
		 </div>
		 <br><br>
	</div>
	 
	
		<table border="1" class="tabela">
			<thead>
				<tr>
					<th>Id: </th>
					<th>Nome: </th>
					<th>Numero de telefone: </th>
					<th>E-mail: </th>
					<th>Opções</th>
				</tr>
			</thead>
		
			<tbody>
				<%for(int i = 0; i<lista.size();i++){%>
				<tr>
					<!-- quando se usa o scriptlet com "=", se obtem o conteúdo que se quer exibir. obs: Não se usa ";" -->
					<td > <%= lista.get(i).getIdcon() %> </td>
					<td > <%= lista.get(i).getNome() %> </td>
					<td > <%= lista.get(i).getFone() %> </td>
					<td > <%= lista.get(i).getEmail() %> </td>
					
					<!-- "?" serve para dizer que vamos passar um parametro -->
					<td ><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="botao1" style="color:white; background-color: blue;">Editar</a>
					&#160; &#160;
					<a href="javascript:confirmar(<%=lista.get(i).getIdcon() %>)" class="botao1" style="color:white; background-color: red;">Deletar</a></td>
				</tr>
				<%}%>
			</tbody>
		</table>
		
	<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</html>