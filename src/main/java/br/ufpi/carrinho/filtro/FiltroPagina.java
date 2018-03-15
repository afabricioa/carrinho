package br.ufpi.carrinho.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpi.carrinho.model.Cliente;

@WebFilter(urlPatterns = {"/*"})

public class FiltroPagina implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		Cliente usuarioLogado = (Cliente) httpRequest.getSession().getAttribute("usuarioLogado");
		
		String paginaAcessada = httpRequest.getRequestURI();
		
		boolean requestDoLogin = paginaAcessada.contains("/carrinho/login.xhtml");
		
		if(usuarioLogado != null) {
			if(requestDoLogin) {
				httpResponse.sendRedirect("/carrinho/produto.xhtml");
			}else {
				chain.doFilter(request, response);
			}
		}else {
			if(!requestDoLogin && !paginaAcessada.contains("javax.faces.resource") && !paginaAcessada.contains("images")) {
				httpResponse.sendRedirect("/carrinho/login.xhtml");
			}else {
				chain.doFilter(request, response);
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
