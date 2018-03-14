package br.ufpi.carrinho.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufpi.carrinho.dao.ProdutoDao;
import br.ufpi.carrinho.model.Produto;
import br.ufpi.carrinho.model.Tipo;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject private ProdutoDao produtoDao;
	private Produto produto;
	
	public ProdutoBean() {
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init()");
		produto = new Produto();
	}
	
	public void salvaProduto() {
		produtoDao.salvar(produto);
		produto = new Produto();
	}
	
	public List<Produto> listarProdutos(){
		return produtoDao.listar();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Tipo[] tipos() {
		return Tipo.values();
	}
	

}
