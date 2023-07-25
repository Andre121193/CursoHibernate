package br.com.alura.loja.testes;

import java.math.BigDecimal;

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

public class CadastroDePedido {

	public static void main(String[] args) {
		
		populaBancoDeDados(); // salvando o produto e a categoria no banco de dados.
		
		// recuperando o produo recem salvo do banco de dados.
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscaPorId(1l);
		Cliente cliente = clienteDao.buscaPorId(1l);
		
		em.getTransaction().begin();
			
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionaItem(new ItemPedido(10, produto, pedido));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();

	}
	
	private static void populaBancoDeDados() {
		Categoria celulares = new Categoria("celulares");
		Produto celular = new Produto("Xiaomi redmi", "Inovador", new BigDecimal("1500"), celulares);
		Cliente cliente = new Cliente("Andr�", "12345678");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		
		
		em.getTransaction().commit();
		em.close();
	}

}
