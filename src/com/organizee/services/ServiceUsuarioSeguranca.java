package com.organizee.services;

import com.organizee.model.dao.DaoFactory;
import com.organizee.model.dao.UsuarioSegurancaDao;
import com.organizee.model.entities.UsuarioSeguranca;
import com.organizee.model.entities.enums.StatusUsuarioSeguranca;

public class ServiceUsuarioSeguranca {

	UsuarioSegurancaDao usuarioSegurancaDao = DaoFactory.createUsuarioSegurancaDao();

	UsuarioSeguranca usuarioSeguranca = null;

	public void inseririUsuarioSeguranca(Integer id, String nomeUsuarioSeguranca, String pass, Integer status) {

		usuarioSeguranca = new UsuarioSeguranca(id, nomeUsuarioSeguranca, pass,
				StatusUsuarioSeguranca.setStatus(status));

		usuarioSegurancaDao.insert(usuarioSeguranca);
	}

	public UsuarioSeguranca buscarUsuarioSeguranca(String nome) {

		usuarioSeguranca = usuarioSegurancaDao.findbyNome(nome);
	
		return usuarioSeguranca;
	}

}
