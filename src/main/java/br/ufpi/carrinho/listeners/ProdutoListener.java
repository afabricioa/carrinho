package br.ufpi.carrinho.listeners;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import br.ufpi.carrinho.model.Produto;
import br.ufpi.carrinho.util.MensagensManager;

public class ProdutoListener {

	@PrePersist
	public void antesAdicionarProduto(Produto produto) {
		if (produto.getPreco() > 20) {
			System.out.println(MensagensManager.readMessage("warning.preco"));
		}
	}

	@PostPersist
	public void depoisAdicionarProduto(Produto produto) {
		FacesContext
		.getCurrentInstance()
		.addMessage(null, new FacesMessage(MensagensManager.readMessage("sucesso"),
				MensagensManager.readMessage("produto.salvo.sucesso", "Produto", produto.getDescricao())));
	}


}
