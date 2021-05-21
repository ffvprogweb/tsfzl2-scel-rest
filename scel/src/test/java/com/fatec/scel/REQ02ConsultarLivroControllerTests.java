package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class REQ02ConsultarLivroControllerTests {
	@Autowired
	TestRestTemplate testRestTemplate;
	@Autowired
	LivroRepository repository;
	@BeforeAll
	void inicializa() {
		repository.deleteAll();
		Livro umLivro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(umLivro);
		umLivro = new Livro("2222", "Engenharia de Software", "Pressman");
		repository.save(umLivro);
		List<Livro> livros = repository.findAll();
		ArrayList<Livro> lista = new ArrayList<Livro>();
		livros.forEach(cliente -> lista.add(cliente));
		lista.forEach(cli-> System.out.println("clientes nesta sessao =>" + cli.toString()));

	}
	@Test
	void ct01_quando_cosulta_todos_retorna2() {
		// Dado - que existem 2 registros cadastrados
		// Quando - solicita uma requisicao get consulta todos
		ParameterizedTypeReference<List<Livro>> tipoRetorno = new ParameterizedTypeReference<List<Livro>>() {
		};
		ResponseEntity<List<Livro>> resposta = testRestTemplate.exchange("/api/v1/livros", HttpMethod.GET, null,
				tipoRetorno);
		List<Livro> lista = resposta.getBody();
		// Entao - retorna 2
		assertEquals(2, lista.size());
		// validacao do estado
		Livro re = new Livro("1111", "Teste de Software", "Delamaro");
		Livro ro = resposta.getBody().get(0);
		re.setId(ro.getId());
		assertEquals(re.getId(), ro.getId());
		assertTrue(re.equals(ro));

	}
	@Test
	public void ct02_quando_consulta_pelo_id_retorna_os_detalhes_do_livro() throws Exception {
		// Dado - que existem dois registros no banco de dados
		// Quando - o usuario consulta pelo id
		Livro re = repository.findByIsbn("1111");
		Long id = re.getId();
		ResponseEntity<Livro> resposta = testRestTemplate.getForEntity("/api/v1/livro/" + id, Livro.class);
		Livro ro = resposta.getBody();
		// Entao - retorna os detalhes do livro
	
		assertTrue(re.equals(ro));
		assertEquals("200 OK", resposta.getStatusCode().toString());
	}
	@Test
	public void ct03_quando_consulta_pelo_isbn_entao_retorna_os_detalhes_do_livro() throws Exception {
		// Dado - que existem dois registros no banco de dados
		// Quando - o usuario consulta pelo isbn
		String isbn = "1111";
		ResponseEntity<Livro> resposta = testRestTemplate.getForEntity("/api/v1/livros/" + isbn, Livro.class);
		Livro ro = resposta.getBody();
		// Entao - retorna os detalhes do livro
		Livro re = new Livro("1111", "Teste de Software", "Delamaro");
		re.setId(ro.getId()); // id deve ser inicializado no teste
		
		assertTrue(re.equals(ro));
		assertEquals("200 OK", resposta.getStatusCode().toString());
	}


}
