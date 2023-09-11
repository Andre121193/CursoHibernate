package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;

@Embeddable // essa anotação faz com que os atributos da classe DadosPessoais sejam incluidos na classe Cliente
public class DadosPessoais {
	
	private String nome;
	private String cpf;
	
	public DadosPessoais() {
	}
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
