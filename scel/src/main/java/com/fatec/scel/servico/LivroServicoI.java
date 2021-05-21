package com.fatec.scel.servico;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@Service
public class LivroServicoI implements LivroServico{
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	LivroRepository repository;
	@Override
	public ResponseEntity<List<Livro>> consultaTodos() {
		List<Livro> livros = repository.findAll();
		return ResponseEntity.ok().body(livros);
	}

	@Override
	public ResponseEntity<Object> save(Livro livro) {
		
		try {
			repository.save(livro);
			logger.info(">>>>>> 2. controller chamou servico save");
			return new ResponseEntity<>("Cliente cadastrado", HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Cliente já cadastrado", HttpStatus.BAD_REQUEST);
		}

	}

}
