package com.organizee.services;

import java.util.List;

import com.organizee.model.dao.DaoFactory;
import com.organizee.model.dao.InstituicaoDao;
import com.organizee.model.entities.Instituicao;
import com.organizee.model.entities.TipoNegociacao;

public class ServiceInstituicao {

	InstituicaoDao instituicaoDao = DaoFactory.createInstituicaoDao();
	Instituicao instituicao = null;
	
	public void inserirInstituicao(Integer id, String nomeInstituicao, String site, TipoNegociacao tipoNegociacao) {
		
		instituicao = new Instituicao(id, nomeInstituicao, site, tipoNegociacao);
		
		instituicaoDao.insert(instituicao);
	}
	
	public List<Instituicao> buscarLista(){
		
		List<Instituicao>  listInstituicaos = instituicaoDao.findAll();
		
		return listInstituicaos;
	}
	
	public void editarInstituicao(Integer idInstituicao, String nomeInstituicao, String site, TipoNegociacao tipoNegociacao) {

		instituicao = new Instituicao(idInstituicao, nomeInstituicao, site, tipoNegociacao);
		instituicaoDao.update(instituicao);
	}
	

	

}
