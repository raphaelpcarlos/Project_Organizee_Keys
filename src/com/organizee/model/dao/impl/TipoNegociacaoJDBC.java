package com.organizee.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.organizee.db.DB;
import com.organizee.db.DbException;
import com.organizee.model.dao.TipoNegociacaoDao;
import com.organizee.model.entities.TipoNegociacao;

public class TipoNegociacaoJDBC implements TipoNegociacaoDao {

	Connection conn = null;

	public TipoNegociacaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(TipoNegociacao obj) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("INSERT INTO `organize_keys`.`tipo_negociacao` (`tipo_negociacao`) VALUES (?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getTipoNegociacao());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {

					int id = rs.getInt(1);
					obj.setIdNegociacao(id);

					DB.closeResultSet(rs);

				} else {
					throw new DbException("Erro inesperado, nenhnum registro foi afetado!");
				}
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(TipoNegociacao obj) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"UPDATE organize_keys.tipo_negociacao SET tipo_negociacao = ? WHERE id_negociacao = ?;");

			st.setString(1, obj.getTipoNegociacao());
			st.setInt(2, obj.getIdNegociacao());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("DELETE FROM tipo_negociacao WHERE id_negociacao = ?;");

			st.setInt(1, id);

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new DbException("Não há dados para deletar!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public TipoNegociacao findbyId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM organize_keys.tipo_negociacao where id_negociacao = ?;");

			st.setInt(1, id);

			rs = st.executeQuery();

			while (rs.next()) {

				TipoNegociacao tipoNegociacao = new TipoNegociacao();

				tipoNegociacao.setIdNegociacao(rs.getInt("id_negociacao"));
				tipoNegociacao.setTipoNegociacao(rs.getString("tipo_negociacao"));

				return tipoNegociacao;
			}

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<TipoNegociacao> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM organize_keys.tipo_negociacao;");

			rs = st.executeQuery();

			List<TipoNegociacao> listTipoNegociacao = new ArrayList<TipoNegociacao>();

			while (rs.next()) {

				TipoNegociacao tipoNegociacao = new TipoNegociacao();

				tipoNegociacao.setIdNegociacao(rs.getInt("id_negociacao"));
				tipoNegociacao.setTipoNegociacao(rs.getString("tipo_negociacao"));

				listTipoNegociacao.add(tipoNegociacao);
			}
			return listTipoNegociacao;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
