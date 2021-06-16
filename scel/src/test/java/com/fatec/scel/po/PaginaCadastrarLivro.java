package com.fatec.scel.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaCadastrarLivro {
	private WebDriver driver;
	private By isbnBy = By.id("isbn");
	private By autorBy = By.id("autor");
	private By tituloBy = By.id("titulo");
	private By btnMenuBy = By.linkText("Livros");
	private By btnCadastrarLivroBy = By.cssSelector(".btn:nth-child(1)");
	private By resultadoCadastroComSucessoBy = By.id("paginaConsulta");
	private By resultadoLivroJaCadastradoBy = By.cssSelector(".text-danger");
	private By resultadoISBNInvalidoBy = By.cssSelector(".text-danger:nth-child(3)");
	private By btnExcluirBy = By.linkText("Excluir");
	private By btnVoltarBy = By.linkText("Voltar");

	public PaginaCadastrarLivro(WebDriver driver) {
		this.driver = driver;
	}

	public PaginaCadastrarLivro cadastrar(String isbn, String autor, String titulo) {
		espera();
		driver.findElement(btnMenuBy).click();
		driver.findElement(isbnBy).sendKeys(isbn);
		driver.findElement(autorBy).sendKeys(autor);
		driver.findElement(tituloBy).sendKeys(titulo);
		driver.findElement(btnCadastrarLivroBy).click();
		return new PaginaCadastrarLivro(driver);
	}

	public String getResultadoCadastroComSucesso() {
		espera();
		return driver.findElement(resultadoCadastroComSucessoBy).getText();
	}

	public String getResultadoLivroJaCadastrado() {
		return driver.findElement(resultadoLivroJaCadastradoBy).getText();
	}

	public String getResultadoISBNInvalido() {
		return driver.findElement(resultadoISBNInvalidoBy).getText();
	}

	public void excluiRegistro() {
		driver.findElement(btnExcluirBy).click();
	}

	public void voltarParaMenu() {
		driver.findElement(btnVoltarBy).click();
	}

	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
