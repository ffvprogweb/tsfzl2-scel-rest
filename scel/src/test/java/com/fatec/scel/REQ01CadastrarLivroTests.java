package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@SpringBootTest
class REQ01CadastrarLivroTests {
	@Autowired
	LivroRepository repository;
	Validator validator;
	ValidatorFactory validatorFactory;
	
	@Test
	void ct01_quando_dados_validos_retorna1() {
		//Dado que o atendente tem um livro nao cadastrado
		repository.deleteAll();
		Livro livro = new Livro ("1111", "Teste de Software", "Delamaro");
		//Quando - o atendente cadastra um livro com informações válidas
		repository.save(livro);
		//Entao - o sistema valida os dados E confirma a operação
		assertEquals(1,repository.count());
	}
	
	@Test
	void ct02_quando_dados_validos_violacoes_retorna_vazio() {
		//Dado que o atendente tem um livro nao cadastrado
		Livro livro = new Livro ("1111", "Teste de Software", "Delamaro");
		//Quando o atendente cadastrar um livro com informacoes validas
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		//Entao o sistema verifica os dados E confirma operacao
		assertTrue(violations.isEmpty());
		
	}
	@Test
	void ct03_quando_titulo_branco_msg_titulo_invalido() {
		//Dado que o atendente tem um livro nao cadastrado
		Livro livro = new Livro ("1111", "", "Delamaro");
		//Quando o atendente cadastrar um livro com informacoes invalidas
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		//Entao o sistema verifica os dados E retorna titulo invalido
		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
		assertEquals("Titulo deve ter entre 1 e 50 caracteres", violations.iterator().next().getMessage());

	}
	
	@Test
	void ct04_quando_isbn_ja_cadastrado_retorna_violacao_de_integridade() {
		//Dado que o isbn do livro ja esta cadastrado
		repository.deleteAll();
		Livro livro = new Livro ("1111", "Teste de Software", "Delamaro");
		repository.save(livro);
		//Quando o usuario confirma a operacao 
		livro = new Livro ("1111", "Teste de Software", "Delamaro");
		//Entao o sistema valida as informacoes E retorna violacao de integridade
		try {
			repository.save(livro);
		}catch(DataIntegrityViolationException e) {
			assertEquals("could not execute statement", e.getMessage().substring(0, 27));
			assertEquals(1,repository.count());
		}
		
	}

}
