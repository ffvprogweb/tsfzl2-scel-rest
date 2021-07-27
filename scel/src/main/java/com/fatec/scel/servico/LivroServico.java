
package com.fatec.scel.servico;
import java.util.*;

import org.springframework.http.ResponseEntity;

import com.fatec.scel.model.Livro;
public interface LivroServico {
	
	ResponseEntity<List<Livro>> consultaTodos();
	ResponseEntity<Livro> consultaPorIsbn(String isbn);
	ResponseEntity<Livro> consultaPorId(Long id);
	ResponseEntity<Object> save(Livro Livro);
	void delete (Long id);


}
