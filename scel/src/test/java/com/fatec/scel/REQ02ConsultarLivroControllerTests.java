package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
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
class REQ02ConsultarLivroControllerTests {
	@Autowired
	TestRestTemplate testRestTemplate;
	@Autowired
	LivroRepository repository;

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
		re.setId(1L); // id deve ser inicializado no teste
		Livro ro = resposta.getBody().get(0);
		assertEquals(re.getId(), ro.getId());
		assertTrue(re.equals(ro));

	}
	@Test
	public void ct02_quando_consulta_pelo_id_retorna_os_detalhes_do_livro() throws Exception {
		// Dado - que existem dois registros no banco de dados
		// Quando - o usuario consulta pelo id
		Long id = 1L;
		ResponseEntity<Livro> resposta = testRestTemplate.getForEntity("/api/v1/livro/" + id, Livro.class);
		Livro ro = resposta.getBody();
		// Entao - retorna os detalhes do livro
		Livro re = new Livro("1111", "Teste de Software", "Delamaro");
		re.setId(1L); // id deve ser inicializado no teste
		assertEquals (re.getId(),ro.getId());
		assertTrue(re.equals(ro));
		assertEquals("200 OK", resposta.getStatusCode().toString());
	}

}
