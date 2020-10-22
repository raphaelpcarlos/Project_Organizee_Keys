package com.organizee.model.utils;

import com.organizee.model.entities.UsuarioSeguranca;

public class RetornUserSeg {

	public static UsuarioSeguranca userSeguranca = null;

	public static UsuarioSeguranca recebeUser(UsuarioSeguranca usuarioSeguranca) {

		userSeguranca = usuarioSeguranca;

		return userSeguranca;
	}

	public static UsuarioSeguranca retornaUser() {

		return userSeguranca;
	}

}
