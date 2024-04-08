package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
//conectar o url do controller com o main
/**
 * The Class Controle.
 */
//mapeamento
@WebServlet(urlPatterns = { "/Controller", "/main", "/main2", "/insert", "/select", "/update", "/delete", "/report"})
//@WebServlet(urlPatterns= {"/controller","/main2"})

public class Controle extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();
	

	/**
	 * Instantiates a new controle.
	 */
	public Controle() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// teste de conexão
		// dao.testedeconexao();

		String requisicao = request.getServletPath();
		System.out.println(requisicao);

		if (requisicao.equals("/main")) {
			contatos(request, response);
		}
		else if (requisicao.equals("/insert")) {
			novoContato(request, response);
		}
		
		else if (requisicao.equals("/select")) {
			selecionarContato(request, response);
		}
		
		else if (requisicao.equals("/update")) {
			updateContato(request, response);
		}
		
		else if (requisicao.equals("/delete")) {
			removerContato(request, response);
		}
		
		else if(requisicao.equals("/report")) {
			gerarRelatorio(request, response);
		}
		
		else if (requisicao.equals("/main2")) {
			//listacontatos(request, response);
			response.sendRedirect("Index.html");
		}
		
		else {
			response.sendRedirect("Index.html");
		}
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");
		
		//Criando um objeto que vai receber os dados da classe JavaBeans
		//Criar uma arraylist com herança do JavaBeans
		ArrayList<JavaBeans> lista = dao.listarcontatos();
		
		//Encaminhamento do objeto lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
		//teste de recebimento de lista
		/*for (int i = 0; i < lista.size(); i++) {
		*	System.out.println(lista.get(i).getIdcon());
		*	System.out.println(lista.get(i).getNome());
		*	System.out.println(lista.get(i).getFone());
		*	System.out.println(lista.get(i).getEmail());
		*}
		*/
	}

	/*protected void listacontatos(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	*	
	*	response.sendRedirect("listadecontatos.jsp");
	*}
	*/
	
	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//teste de recebimento de dados do formulário novo.html
		/*
		 * System.out.println(request.getParameter("nome"));
		 *System.out.println(request.getParameter("fone"));
		 *System.out.println(request.getParameter("email"));
		 */
		
		//setar os atributos da classe JavaBeans.java
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//invocar o metodo inserir contato passando o objeto contato (como parametro)
		dao.inserirContato(contato);
		//Redirecionar para a Pagina agenda.jsp
		response.sendRedirect("main");
	}
	
	/**
	 * Selecionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Editar contatos
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//Recebimento do id de contato que sera editado
		String idcon = request.getParameter("idcon");
		
		//Teste de receptação de atributo
		//System.out.println(idcon);
		
		// "Integer.parseInt()" converter "String" para "int"
		contato.setIdcon(Integer.parseInt(idcon));
		//Executar o método selecionarContato pelo DAO
		dao.selecaoContato(contato);
		
		//teste de recebimento
		/*System.out.println(contato.getIdcon());
		*System.out.println(contato.getNome());
		*System.out.println(contato.getFone());
		*System.out.println(contato.getEmail());
		*/
		
		//settar os atributos ao formulário com o conteúdo da classe JavaBeans. passar os atributos(valores) para o editar.jsp.
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		
		//Encaminha para o documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("/editar.jsp");
		
		//O "forward" encaminha o "request" e o "response"
		rd.forward(request, response);
	}
	
	/**
	 * Update contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void updateContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//teste de recebimento de dados do formulário editar.html
		
		/*System.out.println(request.getParameter("idcon"));
		 *System.out.println(request.getParameter("nome"));
		 *System.out.println(request.getParameter("fone"));
		 *System.out.println(request.getParameter("email"));
		 */
		
		//settar os atributos da classe JavaBeans.java
		contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//invocar o metodo inserir contato passando o objeto contato (como parametro)
		dao.atualizarContato(contato);
		//Redirecionar para a Pagina agenda.jsp
		response.sendRedirect("main");
	}
	
	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		//System.out.println(idcon);
		
		if(idcon != null) {
			contato.setIdcon(Integer.parseInt(idcon));
			dao.excluir(contato);
			response.sendRedirect("main");
		}else {
			response.sendRedirect("main");
		}
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Document documento = new Document();
		
		try {
			//Tipo de conteúdo
			response.setContentType("application/pdf");
			
			//Nome do documento
			response.addHeader("Content-Disposition", "inline; filename="+"Contatos");
			
			//Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			//Abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de contatos: "));
			
			//listagem de contatos
			documento.add(new Paragraph(" "));
			
			//Criar uma tabela no pdf
			PdfPTable tabela = new PdfPTable(4);
			
			//Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Id: "));
			PdfPCell col2 = new PdfPCell(new Paragraph("Nome: "));
			PdfPCell col3 = new PdfPCell(new Paragraph("Fone: "));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail: "));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			//Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarcontatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(Integer.toString(lista.get(i).getIdcon()));
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			
			documento.add(tabela);
			
			
			
			documento.close();
			} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
