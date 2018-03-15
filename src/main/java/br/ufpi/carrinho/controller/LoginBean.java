package br.ufpi.carrinho.controller;
import java.io.IOException;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufpi.carrinho.dao.ClienteDao;
import br.ufpi.carrinho.model.Cliente;
import br.ufpi.carrinho.util.Utils;

@Named
@RequestScoped
public class LoginBean {
	private String email;
	private String senha;

	@Inject
	private ClienteDao clienteDao;

	public void login() {
		Cliente clienteBD = clienteDao.procurarClientePorEmailSenha(this.email, Utils.convertStringToMd5(this.senha));

		if(clienteBD != null) {
			try {
				Utils.salvarUsuarioNaSessao(clienteBD);

				FacesContext.getCurrentInstance().getExternalContext().redirect("produto.xhtml");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Problema ao fazer login!", "Email e/ou senha incorretos!"));
		}
	}

	public void logout() {
		Map<String, Object> sessionMap = FacesContext
											.getCurrentInstance()
											.getExternalContext()
											.getSessionMap();
		sessionMap.remove("usuarioLogado");
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
																.getCurrentInstance()
																.getExternalContext()
																.getRequest();
		request.getSession().invalidate();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
