package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@SpringBootTest
class REQ02ConsultarLivroTests {
	
	@Autowired
	LivroRepository repository;

	@Test
	void ct01_quando_consulta_isbn_cadastrado_retorna_os_detalhes_do_livro() {
		//Dado - que o isbn esta cadastrado
		repository.deleteAll();
		Livro livro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(livro);
		//Quando - o usuario consulta pelo isbn
		Livro ro = repository.findByIsbn("1111");
		livro.setId(ro.getId());
		//Entao - retorna os detalhes do livro
		assertThat(ro).isEqualTo(livro);
	}
	@Test
	void ct02_quando_consulta_titulo_parcial_retorna3() {
		//Dado que existe 3 livros cadastrados com o titulo teste e 1 com o titulo engenharia
		repository.deleteAll();
		Livro livro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(livro);
		livro = new Livro("2222", "Teste", "Delamaro");
		repository.save(livro);
		livro = new Livro("3333", "Teste de desempenho", "Delamaro");
		repository.save(livro);
		livro = new Livro("4444", "Engenharia de Software", "Sommerville");
		repository.save(livro);
		//Quando - o usuario consulta pelo titulo "teste"
		List<Livro> ro = repository.findAllByTituloIgnoreCaseContaining("Teste");
		//Então - retorna 3 livros
	}

}
