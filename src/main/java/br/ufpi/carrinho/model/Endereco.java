package br.ufpi.carrinho.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	private String bairro;
	private String logradouro;
	private Integer numero;
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Endereco() {
	}
}
