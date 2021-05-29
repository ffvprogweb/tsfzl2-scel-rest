package com.fatec.scel.servico;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fatec.scel.model.Livro;
public interface LivroServico {
	
	ResponseEntity<List<Livro>> consutaTodos();
	ResponseEntity<Livro> consultaPorIsbn (String isbn);
	ResponseEntity<Livro> consultaPorId (Long id);
	ResponseEntity<Object> save (Livro livro);
	void delete (Long id);
	

}
