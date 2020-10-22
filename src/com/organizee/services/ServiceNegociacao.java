package com.organizee.services;

import java.util.List;

import com.organizee.model.dao.DaoFactory;
import com.organizee.model.dao.TipoNegociacaoDao;
import com.organizee.model.entities.TipoNegociacao;

public class ServiceNegociacao {

	TipoNegociacaoDao tipoNegociacaoDao = DaoFactory.createTipoNegociacaoDao();

	TipoNegociacao tipoNegociacao = null;

	// Metodo para buscar pelo ID
	public TipoNegociacao buscaId(Integer id) {

		tipoNegociacao = tipoNegociacaoDao.findbyId(id);

		return tipoNegociacao;
	}

	// Metodo para retornar a lista de tipo de negociacao
	public List<TipoNegociacao> buscarList() {

		List<TipoNegociacao> listNegociacaos = tipoNegociacaoDao.findAll();

		return listNegociacaos;
	}

	// Metodo para insetir um tipo
	public void inserirNegociacao(String dados) {

		TipoNegociacao tipoNegociacao = new TipoNegociacao(null, dados);

		tipoNegociacaoDao.insert(tipoNegociacao);

	}

	// Metodo para editar um tipo de negociacao

	public void editarNegociacao(Integer id, String nome) {

		tipoNegociacao = new TipoNegociacao(id, nome);

		tipoNegociacaoDao.update(tipoNegociacao);
	}

	public void excluirNegociacao(Integer id) {

		tipoNegociacaoDao.deleteById(id);
	}

}
