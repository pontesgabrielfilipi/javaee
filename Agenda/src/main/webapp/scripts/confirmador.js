/**
 * Comfirmação de exclusão de um contato
 * @author aluno Gabriel Filipi Pontes
 * 
 */

 function confirmar(idcon){
	 let resposta = confirm("Deseja realmente excluir este contato?")
	 if(resposta===true){
		 //alert(idcon)
		 window.location.href="delete?idcon=" +idcon
	 }else{
		 alert("Contato NÃO excluido!!!");
	 }
	 
 }