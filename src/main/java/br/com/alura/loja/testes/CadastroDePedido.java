package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		populaBancoDeDados(); // salvando o produto e a categoria no banco de dados.
		
		System.out.println("-------------------------------------------------------fim da população de banco de dados-------------------------------------------------------");
		
		// recuperando o produo recem salvo do banco de dados.
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscaPorId(1l);
		Produto produto2 = produtoDao.buscaPorId(2l);
		Produto produto3 = produtoDao.buscaPorId(3l);
		Cliente cliente = clienteDao.buscaPorId(1l);
		
		em.getTransaction().begin();
			
		
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionaItem(new ItemPedido(10, produto, pedido1));
		pedido1.adicionaItem(new ItemPedido(40, produto2, pedido1));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionaItem(new ItemPedido(2, produto3, pedido1));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido1);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL VENDIDO: " + totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
		
		System.out.println("-------------------------------------------------------fim da criação de cadastro de pedidos-------------------------------------------------------");
		
		Pedido pedido = em.find(Pedido.class, 1l);
		System.out.println(pedido.getItens().size());
		
		Pedido pedidoJoinFetch = pedidoDao.buscarPedidoComCliente(1l);
		System.out.println(pedidoJoinFetch.getCliente().getNome());
		em.close();
		

	}
	
	private static void populaBancoDeDados() {
		Categoria celulares = new Categoria("celulares");
		Categoria videogames = new Categoria("videogames");
		Categoria informatica = new Categoria("informatica");
		
		Produto celular = new Produto("Xiaomi redmi", "Inovador", new BigDecimal("1500"), celulares);
		Produto videogame = new Produto("PS5", "Playstatio 5", new BigDecimal("3500"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook Pro", new BigDecimal("9859"), informatica);
		
		Cliente cliente = new Cliente("André", "12345678");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		
		clienteDao.cadastrar(cliente);
		
		
		em.getTransaction().commit();
		em.close();
	}

}
