package br.ufpi.carrinho.listeners;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import br.ufpi.carrinho.model.Produto;

public class ProdutoListener {

	@PrePersist
	public void antesAdicionarProduto(Produto produto) {
		if (produto.getPreco() > 20) {
			System.out.println("O valor não pode ser maior que R$ 20,00!!");
		}
	}

	@PostPersist
	public void depoisAdicionarProduto(Produto produto) {
		System.out.println("O produto foi salvo com sucesso!!");
	}


}
