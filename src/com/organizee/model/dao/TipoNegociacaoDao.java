package com.organizee.model.dao;

import java.util.List;

import com.organizee.model.entities.TipoNegociacao;

public interface TipoNegociacaoDao {
	
	void insert (TipoNegociacao obj);
	
	void update (TipoNegociacao obj);
	
	void deleteById(Integer id);
	
	TipoNegociacao findbyId(Integer id);
	
	List<TipoNegociacao> findAll();

}
