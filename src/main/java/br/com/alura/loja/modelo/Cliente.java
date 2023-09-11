package br.com.alura.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded // essa anotação faz com que os atributos da classe dados pessoais sejam considerados na classe cliente mesmo estando em outra classe
	private DadosPessoais dadosPessoais;

	public Cliente() {

	}

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}
	
	public String getCpf() { // metodo delegando a chamada do getCpf para nao estourar erros do código ja existente
		return this.dadosPessoais.getCpf();
	}
	
	public String getNome() { // metodo delegando a chamada do getNome para nao estourar erros do código ja existente
		return this.dadosPessoais.getNome();
	}

	public Long getId() {
		return id;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

}
