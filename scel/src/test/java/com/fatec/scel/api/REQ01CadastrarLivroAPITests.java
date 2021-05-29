package com.fatec.scel.api;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@SpringBootTest (webEnvironment=WebEnvironment.RANDOM_PORT)
class REQ01CadastrarLivroAPITests {
	@Autowired
	TestRestTemplate testRestTemplate;
	@Autowired
	LivroRepository repository;

	@Test
	void ct01_quando_seleciona_cadastrar_livro_retorna_201() {
		//Dado que o servico esta disponivel
		//Quando o usuario faz uma requisicao POST para cadastrar livro
		Livro livro = new Livro ("3333", "User Stories", "Cohm");
		HttpEntity<Livro> httpEntity = new HttpEntity<>(livro);
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/livro",HttpMethod.POST, httpEntity, String.class);
		//Entao retorna HTTP 201
		assertEquals("201 CREATED", resposta.getStatusCode().toString());
	}
	@Test
	void ct02_quando_livro_ja_cadastrado_retorna_400() {
		//Dado que o livro esta cadastrado
		repository.deleteAll();
		Livro livro = new Livro ("5555", "Teste de Desempenho", "Boyo");
		repository.save(livro);
		//Quando o usuario faz uma requisicao POST para cadastrar livro
		livro = new Livro ("5555", "Teste de Desempenho", "Boyo");
		HttpEntity<Livro> httpEntity = new HttpEntity<>(livro);
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/livro",HttpMethod.POST, httpEntity, String.class);
		//Entao retorna http 400
		assertEquals("400 BAD_REQUEST", resposta.getStatusCode().toString());
	}

}
