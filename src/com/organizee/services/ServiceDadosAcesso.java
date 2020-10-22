package com.organizee.services;

import java.util.List;

import com.organizee.model.dao.DadosAcessoDao;
import com.organizee.model.dao.DaoFactory;
import com.organizee.model.entities.DadosAcesso;
import com.organizee.model.entities.Instituicao;
import com.organizee.model.entities.UsuarioSeguranca;

public class ServiceDadosAcesso {

	DadosAcessoDao acessoDao = DaoFactory.createDadosAcessoDao();
	DadosAcesso dadosAcesso = null;

	public void inserirRegistro(Integer idAcesso, String nomeAcesso, String senhaAcesso, Instituicao instituicao,
			UsuarioSeguranca usuarioSeguranca) {

		dadosAcesso = new DadosAcesso(idAcesso, nomeAcesso, senhaAcesso, instituicao, usuarioSeguranca);

		acessoDao.insert(dadosAcesso);

	}

	public List<DadosAcesso> buscarLista(UsuarioSeguranca usuarioSeguranca) {

		List<DadosAcesso> listDadosAcesso = acessoDao.findAll(usuarioSeguranca);

		return listDadosAcesso;
	}

	public List<DadosAcesso> buscarNome(String nome, UsuarioSeguranca usuarioSeguranca ) {

		List<DadosAcesso> listDadosAcesso = acessoDao.findBuscaNome(nome,usuarioSeguranca);

		return listDadosAcesso;
	}

	public List<DadosAcesso> buscarTipoNegociacao(String tipoNegociacao, UsuarioSeguranca usuarioSeguranca) {

		List<DadosAcesso> listDadosAcesso = acessoDao.findBuscaTipoNegociacao(tipoNegociacao,usuarioSeguranca);

		return listDadosAcesso;
	}

	public List<DadosAcesso> buscarInstituicao(String instituicao,UsuarioSeguranca usuarioSeguranca) {

		List<DadosAcesso> listDadosAcesso = acessoDao.findBuscaInstituicao(instituicao,usuarioSeguranca);

		return listDadosAcesso;
	}
}
