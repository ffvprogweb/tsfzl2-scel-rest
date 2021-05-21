package com.fatec.scel.servico;
import java.util.*;

import org.springframework.http.ResponseEntity;

import com.fatec.scel.model.Livro;
public interface LivroServico {
	
	ResponseEntity<List<Livro>> consultaTodos();
	ResponseEntity<Object> save (Livro livro);

}
