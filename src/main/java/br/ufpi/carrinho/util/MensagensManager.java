package br.ufpi.carrinho.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MensagensManager {
//	public static String readMessage(String key) {
//		ResourceBundle bundle;
//		bundle = ResourceBundle.getBundle("mensagens", 
//				new Locale("pt_br"));
//		return bundle.getString(key);
//	}
	
	public static String readMessage(String key, Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("mensagens", new Locale("pt_br"));
		String mensagem = MessageFormat.format(bundle.getString(key), params);
		
		return mensagem;
	}
}
