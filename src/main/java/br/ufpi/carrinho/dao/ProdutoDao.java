package br.ufpi.carrinho.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufpi.carrinho.model.Produto;

@Stateless
public class ProdutoDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Produto produto){
		em.persist(produto);
	}
	
	public void atualizar(Produto produto){
		em.merge(produto);
	}
	
	public void remover(Produto produto){
		em.remove(produto);
	}
	
	public List<Produto> listar(){
		TypedQuery<Produto> query = 
				em.createQuery("Select p from Produto p", Produto.class);
		return query.getResultList();
	}
	
	public Produto buscar(Long id) {
		TypedQuery<Produto> query = 
	em.createQuery("Select p from Produto p WHERE p.id = :id", Produto.class)
				.setParameter("id", id);
		return query.getSingleResult();
	}
	
	
}
