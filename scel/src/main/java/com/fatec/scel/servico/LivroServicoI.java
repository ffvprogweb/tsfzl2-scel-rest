package com.fatec.scel.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@Service
public class LivroServicoI implements LivroServico{
	
	@Autowired
	LivroRepository repository;

	@Override
	public ResponseEntity<List<Livro>> consutaTodos() {
		List<Livro> livros = repository.findAll();
		return ResponseEntity.ok().body(livros);
	}

	@Override
	public ResponseEntity<Livro> consultaPorIsbn(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Livro> consultaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> save(Livro livro) {
		try {
			repository.save(livro);
			return new ResponseEntity<> ("Cliente cadastrado",HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<> ("Cliente já cadastrado",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
