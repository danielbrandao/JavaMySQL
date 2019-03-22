	// pacote onde a classe se encontra
	package br.org.faculdadesenaiparaiba;

	import java.io.InputStream;
	// bibliotecas da Linguagem java
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	// biblioteca de conex�o com banco MySQL
	import com.mysql.jdbc.PreparedStatement;

	// importanto outras classes
	import br.org.faculdadesenaiparaiba.AlunoDTO;
	import br.org.faculdadesenaiparaiba.BdConnect;

public class ListandoDados {

		// m�todo -- listarTodos -- do tipo array
		public List<AlunoDTO> listarTodos(){
			List<AlunoDTO> listaAlunos = new ArrayList<AlunoDTO>();
			
			// try � o lan�amento de uma exce��o
			try {
				// criando objeto conncection
				Connection connection = BdConnect.getInstance().getConnection();
				
				// criando atributo -- sql --
				String sql = "Select * from aluno";
				
				// criando objeto -- statement --
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
				
				// criando bojeto --resultset--
				ResultSet resultset = statement.executeQuery();
				
				// la�o que procura resultados na consulta SQL
				while(resultset.next()) {
					AlunoDTO alunoDTO = new AlunoDTO();
					alunoDTO.setId(resultset.getLong("id"));
					alunoDTO.setNome(resultset.getString("nome"));
					alunoDTO.setEmail(resultset.getString("email"));
					alunoDTO.setCpf(resultset.getString("cpf"));
					
					// adicionando os dados da consulta no array -- listaAlunos
					listaAlunos.add(alunoDTO);
				}
				
				// encerrando conex�o com banco de dados
				connection.close();
				
				// lan�amento das exce��es
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			//retorno do m�todo -- listarTodos --
			return listaAlunos;
			
		}
		// m�todo main respons�vel por executar o programa
		public static void main (String[] args) {
			
			// criando os objetos
			AlunoDTO alunoDTO = new AlunoDTO();
			NovoDAO alunoDAO = new NovoDAO();
			
			// TODO Auto-generated method stub
			InputStream entrada = System.in;
			Scanner scanner = new Scanner(entrada);
			
			System.out.println("Digite seu nome: ");
			scanner.hasNextLine();
			alunoDTO.setNome(scanner.nextLine());
			
			System.out.println("Digite seu CPF: ");
			scanner.hasNextLine();
			alunoDTO.setCpf(scanner.nextLine());
			
			System.out.println("Digite seu email: ");
			scanner.hasNextLine();
			alunoDTO.setEmail(scanner.nextLine());
			
			System.out.println("Digite uma senha: ");
			scanner.hasNextLine();
			alunoDTO.setSenha(scanner.nextLine());
			
			// Inserindo dados atrav�s do objeto -alunoDAO- pelo m�todo -inserir-
			alunoDAO.inserir(alunoDTO);
			
			// atribuindo ao objeto -listaAlunos- o valor resultante da consulta
			List<AlunoDTO> listaAlunos = new ArrayList<>();
			listaAlunos = alunoDAO.listarTodos();
			
			// la�o de repeti��o para impprimir resultados
			for(int i = 0; i<listaAlunos.size(); i++) {
				// comando de impress�o no console dos resultados
				System.out.println(listaAlunos.get(i));
			}
		}
}