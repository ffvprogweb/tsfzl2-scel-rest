package com.fatec.scel.model;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends CrudRepository <Livro, Long>{
	
feature/req03_excluir_livro
	public Livro findByIsbn(@Param("isbn") String isbn);
	List<Livro> findAllByTituloIgnoreCaseContaining(String titulo);
	
}
