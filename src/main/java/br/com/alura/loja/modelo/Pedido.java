package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	private int id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	private LocalDate dataCadastro = LocalDate.now();

	@ManyToOne
	private Cliente cliente;
	
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
		this.itens.add(item);
	}

	public int getId() {
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

}
