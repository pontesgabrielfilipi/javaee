<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Agenda de Conatos</title>
<link rel="icon" href="Imagens/favicon.png">
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body class="container">
	<h1>Editar contato</h1>
	<form name="frmcontato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly value="<%out.print(request.getAttribute("idcon")); %>"></td>
			</tr>
			<!-- tr para linhas e td para colunas -->
			<tr>
				<!-- td de table data -->
				<!-- placeholde Permite a inserção de dados | é oque sera exibido antes da inserção de dados -->
				<td><input class="Caixa1" type="text" name="nome" value="<%out.print(request.getAttribute("nome")); %>"></td>
			</tr>
			<tr>
				<td><input class="Caixa2" type="text" name="fone" value="<%out.print(request.getAttribute("fone")); %>"></td>
			</tr>
			<tr>
				<td><input class="Caixa1" type="text" name="email" value="<%out.print(request.getAttribute("email")); %>"></td>
			</tr>
		</table>
		<input type="submit" value="Salvar" class="botao1" onclick="validar()">
		
	</form>
	
	<script type="text/javascript" src="scripts/validacao.js"></script>
</body>
</html>