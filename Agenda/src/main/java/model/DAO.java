package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	// Módulo de conexão
	// parametros para conexão

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "backend@24";
	
	

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Metodo de conexão
	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
			// TODO: handle exception
		}
	}
	
	
	
	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	//CRUD -> CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos(nome,fone,email) values(?,?,?);";
		
		try {
			//Abrir a conexão
			Connection con = conectar();
			//Preparar a consulta(query) para execução no Banco de Dados
			PreparedStatement pst = con.prepareStatement(create);
			//Substituir os parametros (?) pelo conteudo dos atributos da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			//Executar a query (Ctrl + Enter)
			pst.executeUpdate();
			//Encerrar a conexao com o BD
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	
	
	/**
	 * Listarcontatos.
	 *
	 * @return the array list
	 */
	//CRUD -> READ
	public ArrayList<JavaBeans> listarcontatos(){
		//Criando um objeto para cessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		//comando usado no MySQL para exibir os dados
		String read = "select * from contatos order by idcon;";
		
		//try catch para conectar
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			//O laço abixo sera excutato emquanto houver contatos
			//next para emquanto não tiver o mesmo valor do ResultSet ele vai trazer o rs
			while(rs.next()) {
				//variaveis de apoio que recebem os dados do BD
				int idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				//Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
		
	}
	
	
	
	//CRUD -> UPDATE
			/**
	 * Selecao contato.
	 *
	 * @param contato the contato
	 */
	//Selecionar contato
	public void selecaoContato(JavaBeans contato) {
		//pst
		String read2 = "select * from contatos where idcon = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			
			pst.setString(1, Integer.toString(contato.getIdcon()));
			//ou "pst.setInt(1, contato.getIdcon());"
			
			ResultSet rs = pst.executeQuery();
			
			//Laço while para receber dados do BD e enviar para a classe JavaBeans
			while(rs.next()) {
				contato.setIdcon(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
			/**
			 * Atualizar contato.
			 *
			 * @param contato the contato
			 */
			//Atualizar o contato
	public void atualizarContato(JavaBeans contato) {
		//pst
		String read3 = "update contatos set nome=?,fone=?,email=? where idcon=?;";
		
		try {
			//Abrir a conexão
			Connection con = conectar();
			//Preparar a consulta(query) para execução no Banco de Dados
			PreparedStatement pst = con.prepareStatement(read3);
			//Substituir os parametros (?) pelo conteudo dos atributos da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, Integer.toString(contato.getIdcon()));
			//Executar a query (Ctrl + Enter)
			pst.executeUpdate();
			//Encerrar a conexao com o BD
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}

	
	
	/**
	 * Excluir.
	 *
	 * @param contato the contato
	 */
	//CRUD -> DELETE
	public void excluir(JavaBeans contato) {
		//pst
		String read4 = "delete from contatos where idcon=?;";
		
		try {
			Connection con = conectar();
			
			PreparedStatement pst = con.prepareStatement(read4);
			pst.setString(1, Integer.toString(contato.getIdcon()));
			//System.out.println(pst.toString());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	// teste de conexão
	
	/*public void testedeconexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}*/
	
}
