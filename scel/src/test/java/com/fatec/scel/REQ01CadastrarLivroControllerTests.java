package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

class REQ01CadastrarLivroControllerTests {
   @Autowired
   TestRestTemplate testRestTemplate;
   @Autowired
   LivroRepository repository;
   
	@Test
	void ct01_quando_seleciona_cadastrar_livro_retorna_201() {
		//Dado - que o servico está disponivel
		//Quando - o usuario faz uma requisicao POST para cadastrar livro
		Livro livro = new Livro("1111", "Teste de Software", "Delamaro");
		HttpEntity<Livro> httpEntity = new HttpEntity<>(livro);
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/livro", HttpMethod.POST, httpEntity, String.class);
		//Entao - retorna HTTP200
		assertEquals("201 CREATED", resposta.getStatusCode().toString());
	}
	@Test
	void ct02_quando_requisicao_invalida_retorna_400() {
		//Dado - que o servico está disponivel
		//Quando - o usuario faz uma requisicao POST invalida
		Livro livro = new Livro("1111", "", "Delamaro");
		HttpEntity<Livro> httpEntity = new HttpEntity<>(livro);
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/livro", HttpMethod.POST, httpEntity, String.class);
		//Entao - retorna HTTP400
		assertEquals("400 BAD_REQUEST", resposta.getStatusCode().toString());
	}
	@Test
	void ct03_quando_livro_ja_cadastrado_retorna_400() {
		//Dado - que o livro esta cadastrado
		repository.deleteAll();
		Livro livro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(livro);
		//Quando - o usuario faz uma requisicao POST invalida
		livro = new Livro("1111", "Teste de Software", "Delamaro");
		HttpEntity<Livro> httpEntity = new HttpEntity<>(livro);
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/livro", HttpMethod.POST, httpEntity, String.class);
		//Entao - retorna HTTP400
		assertEquals("400 BAD_REQUEST", resposta.getStatusCode().toString());
	}
	
}
