package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@SpringBootTest
class REQ03ExcluirLivroTests {
	@Autowired
	LivroRepository repository;
	@Test
	void ct01_quando_exclui_pelo_id_consulta_por_isbn_retorna_null() {
		//Dado que o livro esta cadastrado
		repository.deleteAll();
		Livro livro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(livro);
		//Quando o usuario solicita exclusao
		Livro ro = repository.findByIsbn("1111");
		repository.deleteById(ro.getId());
		//Entao o resultado obtido da consulta deve ser null
		assertThat(repository.findByIsbn("1111")).isEqualTo(null);
	}

}
