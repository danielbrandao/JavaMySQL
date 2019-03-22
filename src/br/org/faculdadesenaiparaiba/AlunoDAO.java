// pacote onde a classe se encontra
package br.org.faculdadesenaiparaiba;

// bibliotecas da Linguagem java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// biblioteca de conex�o com banco MySQL
import com.mysql.jdbc.PreparedStatement;

// importanto outras classes
import br.org.faculdadesenaiparaiba.AlunoDTO;
import br.org.faculdadesenaiparaiba.BdConnect;

public class AlunoDAO {
	
	// m�todo -- listarTodos -- do tipo array
	public List<AlunoDTO> listarTodos(){
		List<AlunoDTO> listaAlunos = new ArrayList<AlunoDTO>();
		
		// try � o lan�amento de uma exce��o
		try {
			// criando objeto conncection
			Connection connection = BdConnect.getInstance().getConnection();
			
			// criando atributo -- sql --
			String sql = "Select * from pessoas";
			
			// criando objeto -- statement --
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			
			// criando bojeto --resultset--
			ResultSet resultset = statement.executeQuery();
			
			// la�o que procura resultados na consulta SQL
			while(resultset.next()) {
				AlunoDTO alunoDTO = new AlunoDTO();
				alunoDTO.setId(resultset.getLong("id"));
				alunoDTO.setNome(resultset.getString("nome"));
				
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
		AlunoDAO alunoDAO = new AlunoDAO();
		List<AlunoDTO> listaAlunos = new ArrayList<>();
		
		// atribuindo ao objeto -listaAlunos- o valor resultante da consulta
		listaAlunos = alunoDAO.listarTodos();
		
		// la�o de repeti��o para impprimir resultados
		for(int i = 0; i<listaAlunos.size(); i++) {
			// comando de impress�o no console dos resultados
			System.out.println(listaAlunos.get(i));
		}
		
	}

}
