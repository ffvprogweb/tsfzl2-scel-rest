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
	
}
