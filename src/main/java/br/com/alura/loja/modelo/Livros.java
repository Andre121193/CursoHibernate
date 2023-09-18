package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Livros extends Produto {

	private String autor;
	private int numeroPaginas;

	public Livros() {
	}

	public Livros(String autor, int numeroPaginas) {
		this.autor = autor;
		this.numeroPaginas = numeroPaginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

}
