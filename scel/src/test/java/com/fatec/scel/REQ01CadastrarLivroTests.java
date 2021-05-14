package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	void ct01_quando_dados_validos_retorna_cadastro_com_sucesso() {
		// Dado - que o atendente tem um livro nao cadastrado
		repository.deleteAll();
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// Quando - o atendente cadastra um livro com informações válidas
		repository.save(livro);
		// Então - o sistema valida os dados E confirma a operação
		assertEquals(1, repository.count());
	}

	@Test
	void ct02_quando_dados_validos_violacoes_retorna_vazio() {
		// Dado - que o atendente tem um livro nao cadastrado
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// Quando - o atendente cadastra um livro com informações válidas
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// Entao - o sistema verifica os dados e retorna que nao existem violacoes
		assertTrue(violations.isEmpty());
	}

	@Test
	void ct03_quando_titulo_branco_msg_titulo_invalido() {
		// Dado - que o atendente tem um livro nao cadastrado
		Livro livro = new Livro("3333", "", "Delamaro");
		// Quando - o atendente cadastra um livro com informações válidas
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// Entao - o sistema verifica os dados e retorna titulo invalido
		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
		assertEquals("Titulo deve ter entre 1 e 50 caracteres. ", violations.iterator().next().getMessage());
	}

	@Test
	void ct04_quando_isbn_ja_cadastrado_retornar_violacao_de_integridade() {
		// Dado - que o ISBN ja esta cadastrado
		repository.deleteAll();
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		//Quando - o usuario registra as informações do livro e confirma a operação
		livro = new Livro("3333", "Teste de Software", "Delamaro");
		//Entao o sistema valida as informações E retorna violacao de integridade
		try {
			repository.save(livro);
		}catch (DataIntegrityViolationException e) {
			assertEquals(1, repository.count());
		}
	}

}
