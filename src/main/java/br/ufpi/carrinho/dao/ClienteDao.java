package br.ufpi.carrinho.dao;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufpi.carrinho.model.Cliente;
import br.ufpi.carrinho.util.Utils;

@Stateless
public class ClienteDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public ClienteDao() {
	}

	public Cliente procurarClientePorEmailSenha(String email, String senha) {
		TypedQuery<Cliente> query = em
				.createQuery("SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha", Cliente.class);
		query.setParameter("email", email);
		query.setParameter("senha", Utils.convertStringToMd5(senha));

		List<Cliente> clientes = query.getResultList();

		if (clientes.size() == 1)
			return clientes.get(0);

		return null;
	}


	public List<Cliente> listar() {
		TypedQuery<Cliente> query = em.createQuery("Select c from Cliente c", Cliente.class);
		return query.getResultList();
	}

	public void salvar(Cliente cliente) {
		String senha = Utils.convertStringToMd5(cliente.getSenha());
		cliente.setSenha(senha);
		em.merge(cliente);
	}

	public Cliente buscar(String cpf) {
		Cliente c = null;
		try {
			TypedQuery<Cliente> query = em.createQuery("Select c from Cliente c WHERE c.cpf like :cpf", Cliente.class)
					.setParameter("cpf", cpf);
			c = query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}

}