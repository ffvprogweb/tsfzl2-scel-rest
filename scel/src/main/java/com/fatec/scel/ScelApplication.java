package com.fatec.scel;

import java.util.List;
import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@SpringBootApplication
public class ScelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScelApplication.class, args);
	}
	@Bean
	CommandLineRunner init(LivroRepository repository) {
		return args -> {
			Livro livro = new Livro("1111","Teste de Software", "Delamaro");
			repository.save(livro);
			livro = new Livro("2222", "Desenvolvimento de Software", "Pilone");
			repository.save(livro);
			List<Livro> livros = repository.findAll();
			livros.forEach(umLivro -> {
				System.out.println(umLivro.toString());
			});
		};
	}
}
