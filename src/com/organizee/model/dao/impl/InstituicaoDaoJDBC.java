package com.organizee.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.organizee.db.DB;
import com.organizee.db.DbException;
import com.organizee.model.dao.InstituicaoDao;
import com.organizee.model.entities.Instituicao;
import com.organizee.model.entities.TipoNegociacao;

public class InstituicaoDaoJDBC implements InstituicaoDao {

	Connection conn = null;

	public InstituicaoDaoJDBC(Connection conn) {
		this.conn = conn;

	}

	@Override
	public void insert(Instituicao obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement(
					"INSERT INTO `organize_keys`.`instituicao` (`nome_instituicao`, `site`, `id_tipo_negociacao`) VALUES (?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomeInstituicao());
			st.setString(2, obj.getSite());
			st.setInt(3, obj.getTipoNegociacao().getIdNegociacao());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdInstituicao(id);

				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado, nenhnum registro foi afetado!");

			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Instituicao obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement(
					"UPDATE `organize_keys`.`instituicao` SET `nome_instituicao` = ?, `site` = ?, `id_tipo_negociacao` = ? WHERE (`id_instituicao` = ?);");

			st.setString(1, obj.getNomeInstituicao());
			st.setString(2, obj.getSite());
			st.setInt(3, obj.getTipoNegociacao().getIdNegociacao());
			st.setInt(4, obj.getIdInstituicao());

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

			st = conn.prepareStatement("DELETE FROM `organize_keys`.`instituicao` where `id_instituicao` = ?;");

			st.setInt(1, id);

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new DbException("Não há dados para deletar!");
			}

		} catch (SQLException e) {
			throw new com.organizee.db.DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Instituicao findbyId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT  * FROM organize_keys.instituicao as inst inner Join tipo_negociacao as tneg on inst.id_instituicao = tneg.id_negociacao	where id_instituicao = ?;");

			st.setInt(1, id);

			rs = st.executeQuery();

			while (rs.next()) {

				Instituicao instituicao = new Instituicao();

				instituicao.setIdInstituicao(rs.getInt("id_instituicao"));
				instituicao.setNomeInstituicao(rs.getString("nome_instituicao"));
				instituicao.setSite(rs.getString("site"));
				TipoNegociacao tipoNegociacao = new TipoNegociacao();
				tipoNegociacao.setIdNegociacao(rs.getInt("id_negociacao"));
				tipoNegociacao.setTipoNegociacao(rs.getString("tipo_negociacao"));
				instituicao.setTipoNegociacao(tipoNegociacao);

				return instituicao;

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
	public List<Instituicao> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT inst.id_instituicao, inst.nome_instituicao, inst.site, tneg.tipo_negociacao FROM organize_keys.instituicao inst left join tipo_negociacao tneg on inst.id_tipo_negociacao = tneg.id_negociacao");

			rs = st.executeQuery();

			List<Instituicao> listInsInstituicaos = new ArrayList<Instituicao>();

			while (rs.next()) {

				Instituicao instituicao = new Instituicao();
				TipoNegociacao tipoNegociacao = new TipoNegociacao();

				instituicao.setIdInstituicao(rs.getInt("id_instituicao"));
				instituicao.setNomeInstituicao(rs.getString("nome_instituicao"));
				instituicao.setSite(rs.getString("site"));
				tipoNegociacao.setTipoNegociacao(rs.getString("tipo_negociacao"));
				instituicao.setTipoNegociacao(tipoNegociacao);

				listInsInstituicaos.add(instituicao);
			}
			return listInsInstituicaos;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
