package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate dataCadastro = LocalDate.now();

	// relacionamentos ...ToOne são chamados tambem de Eager, uma estrategia de carregamento da jpa faz com que os Eagers sejam carregados no ato
	@ManyToOne(fetch = FetchType.LAZY)// é uma boa pratica alterar todos os relacionamentos ToOne(padrao Eager para Lazy)
	private Cliente cliente;
	
	
	// relacionamentos ...ToMany são chamados tambem de Lazy, uma estrategia de carregamento da jpa faz com que os Lazy sejam carregados quando for preciso
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // a string que esta sendo passada é o nome do atributo que esta sendo passado na classe itemPedido, o cascade faz com que tudo que é feito com o pedido tambem seja feito com o itemPedido
	private List<ItemPedido> itens = new ArrayList<>(); // é bom inicializar a lista vazia para que não seja necessario uma verificação ao rodar o codigo

	public Pedido() {
		super();
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	// metodo que adiciona um item a lista, por ser bidirecional se adiciona tanto pela entidade Pedido como pela entidade ItemPedido
	public void adicionaItem(ItemPedido item) {
		item.setPedido(this);
		this.getItens().add(item);
		valorTotal = this.valorTotal.add(item.getValor());
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

}
