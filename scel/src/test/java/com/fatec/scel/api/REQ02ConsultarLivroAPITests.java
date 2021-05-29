package com.fatec.scel.api;

import static org.junit.jupiter.api.Assertions.*;

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

import java.util.List;
@SpringBootTest (webEnvironment=WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class REQ02ConsultarLivroAPITests {
	@Autowired
	TestRestTemplate testRestTemplate;
	@Autowired
	LivroRepository repository;
	@BeforeAll
	void inicializa() {
		repository.deleteAll();
		Livro umLivro = new Livro ("1111", "Teste de Software", "Delamaro");
		repository.save(umLivro);
		umLivro = new Livro ("2222", "Engenharia de Software", "Pressman");
		repository.save(umLivro);
		
	}

	@Test
	void ct01_quando_consulta_todos_retorna2() {
		//Dado que existem 2 livros cadastrados
		//Quando solicita uma requisicao GET consulta todos
		ParameterizedTypeReference<List<Livro>> tipoRetorno = new ParameterizedTypeReference<List<Livro>>() {
		};
		ResponseEntity<List<Livro>> resposta = testRestTemplate.exchange("/api/v1/livros",HttpMethod.GET,null,tipoRetorno);
		//Entao retorna 2 registros
		List<Livro> lista = resposta.getBody();
        assertEquals("200 OK", resposta.getStatusCode().toString());
       
	}
	@Test
	void ct02_quando_consulta_todos_retorna2() {
		//Dado que existem 2 livros cadastrados
		//Quando solicita uma requisicao GET consulta todos
		ParameterizedTypeReference<List<Livro>> tipoRetorno = new ParameterizedTypeReference<List<Livro>>() {
		};
		ResponseEntity<List<Livro>> resposta = testRestTemplate.exchange("/api/v1/livros",HttpMethod.GET,null,tipoRetorno);
		//Entao retorna 2 registros
		List<Livro> lista = resposta.getBody();
       
        assertEquals(2, lista.size());
	}

}
