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

	public void inicializa() {
		Livro livro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(livro);
		livro = new Livro("2222", "Engenharia de Software", "Pressman");
		repository.save(livro);
		ArrayList<Livro> lista = new ArrayList<Livro>();
		List<Livro> livros = repository.findAll();
		livros.forEach(umLivro -> {
			lista.add(umLivro);
		});
	}

	@Test
	void ct01_quando_cosulta_todos_retorna2() {
		// Dado - que existem 2 registros cadastrados
		inicializa();
		// Quando - solicita uma requisicao get consulta todos
		ParameterizedTypeReference<List<Livro>> tipoRetorno = new ParameterizedTypeReference<List<Livro>>() {
		};
		ResponseEntity<List<Livro>> resposta = testRestTemplate.exchange("/api/v1/livros", HttpMethod.GET, null,
				tipoRetorno);
		List<Livro> lista = resposta.getBody();
		// Entao - retorna 2
		assertEquals(2, lista.size());
	}

}
