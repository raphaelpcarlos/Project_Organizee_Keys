package com.organizee.model.utils;

import org.apache.commons.codec.binary.Base64;

import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Raphael
 *
 */
public class ControllKeys {

	// Metodo para criar senha automática
	public static String gerarKeys(int qtd) {
		int qtdeMaximaCaracteres = qtd;

		String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "+", "-",
				"/", "*", "_", "!", "@", "$", "%", "&" };

		StringBuilder senha = new StringBuilder();

		for (int i = 0; i < qtdeMaximaCaracteres; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}
		return senha.toString();

	}

	// Criptografia
	public static String encrypt(String code) {

		String newCode = code;

		newCode = org.apache.commons.codec.binary.Base64.encodeBase64String(newCode.getBytes());

		return newCode;

	}

	public static String decrypt(String code) {

		byte[] decoded = Base64.decodeBase64(code.getBytes());

		String decodedString = new String(decoded);

		return decodedString;
	}

	//Metodo para comparar keys
	public static boolean compareKeys(String key1, String key2) {

		if (!(key1.equalsIgnoreCase(key2))) {

			
			Alerts.Alerta("Senha", "As senhas não coincidem", AlertType.WARNING);
			
			return false;
			
		} else {
			
			return true;

		}
	}
	
	public static boolean verificaQuantidade(String dados) {
		
		
		if(dados.length() < 8) {
			
			Alerts.Alerta("Organizee Keys", "Senha menor que 8 dígitos", AlertType.WARNING);
			
			return false;
		}else {
			return true;
		}
	}

}
