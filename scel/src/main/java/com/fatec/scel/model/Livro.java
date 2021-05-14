package com.fatec.scel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min=4, max=4, message="ISBN deve ter 4 caracteres")
	@Column(unique=true)
	String isbn;
	@NotNull
	@Size(min=1, max=50, message="Titulo deve ter entre 1 e 50 caracteres. ")
	String titulo;
	@NotNull
	@Size(min=1, max=50, message="Autor deve ter entre 1 e 50 caracteres. ")
	String autor;
	
	public Livro (String isbn, String titulo, String autor) {
		this.isbn=isbn;
		this.titulo=titulo;
		this.autor=autor;
	}
	
	public Livro() {
		
	}

}
