package com.organizee.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.organizee.model.entities.Instituicao;
import com.organizee.model.entities.TipoNegociacao;
import com.organizee.services.ServiceInstituicao;
import com.organizee.services.ServiceNegociacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerInstituicao implements Initializable {

	ServiceNegociacao serviceNegociacao = new ServiceNegociacao();
	ServiceInstituicao ServiceInstituicao = new ServiceInstituicao();
	TipoNegociacao tipoNegociacao = null;

	@FXML
	private TableView<Instituicao> tbvInstituicao;

	@FXML
	private TableColumn<Instituicao, Integer> tbvId;

	@FXML
	private TableColumn<Instituicao, String> TbvNomeInstituicao;

	@FXML
	private TableColumn<Instituicao, String> tbvSite;

	@FXML
	private TableColumn<Instituicao, String> tbvNegociacao;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnRemover;

	@FXML
	private Button btnLimparDados;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNomeInst;

	@FXML
	private TextField txtSite;

	@FXML
	private ComboBox<TipoNegociacao> cbNegociacao;

	@FXML
	void btnEditar(ActionEvent event) {

		ServiceInstituicao.editarInstituicao(Integer.parseInt(txtId.getText()), txtNomeInst.getText(),
				txtSite.getText(), tipoNegociacao);

		limparDados();

		atualizarListaInstituicao();
		

	}

	@FXML
	void btnLimparDadosAction(ActionEvent event) {

		limparDados();

	}

	@FXML
	void btnInserir(ActionEvent event) {

		String nomeInstituicao = txtNomeInst.getText();
		String idInstituicao = txtId.getText();
		String siteInstituicao = txtSite.getText();

		if (idInstituicao.equalsIgnoreCase("")) {
			ServiceInstituicao.inserirInstituicao(null, nomeInstituicao, siteInstituicao, selecionarTipoNegociacao());
			limparDados();

			atualizarListaInstituicao();

		} else {

		}

	}

	@FXML
	void btnRemover(ActionEvent event) {

	}

	@FXML
	public TipoNegociacao selecionarTipoNegociacao() {
		tipoNegociacao = cbNegociacao.getSelectionModel().getSelectedItem();

		return tipoNegociacao;
	}

	@FXML
	public void clicarItemListInstituicao() {

		Instituicao instituicao = tbvInstituicao.getSelectionModel().getSelectedItem();
		
		if (instituicao == null) {

		} else {

			txtId.setText(String.valueOf(instituicao.getIdInstituicao()));
			txtNomeInst.setText(instituicao.getNomeInstituicao());
			txtSite.setText(instituicao.getSite());
			cbNegociacao.setValue((instituicao.getTipoNegociacao()));

			tipoNegociacao = cbNegociacao.getSelectionModel().getSelectedItem();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTipoNegocicacao();
		selecionarTipoNegociacao();

		carregarInstitucao();
	}

	// Lista do tipo de negociacao
	List<TipoNegociacao> tipoNegociacaos = new ArrayList<TipoNegociacao>();

	ObservableList<TipoNegociacao> obsTipoNegociacaos;

	// Lista de tipo de instituicao
	List<Instituicao> listInstituicaos = new ArrayList<Instituicao>();

	ObservableList<Instituicao> obsInstituicaos;

	// Metodo para carregar os tipos de negociacação
	private void carregarTipoNegocicacao() {

		tipoNegociacaos.addAll(serviceNegociacao.buscarList());

		obsTipoNegociacaos = FXCollections.observableArrayList(tipoNegociacaos);

		cbNegociacao.setItems(obsTipoNegociacaos);

	}

	// Metodo para carregar as intituições
	public void carregarInstitucao() {
		tbvId.setCellValueFactory(new PropertyValueFactory<>("idInstituicao"));
		TbvNomeInstituicao.setCellValueFactory(new PropertyValueFactory<>("nomeInstituicao"));
		tbvSite.setCellValueFactory(new PropertyValueFactory<>("site"));
		tbvNegociacao.setCellValueFactory(new PropertyValueFactory<>("tipoNegociacao"));

		atualizarListaInstituicao();
	}

	// Metodo para atualizar a lista de instituição
	private void atualizarListaInstituicao() {

		listInstituicaos = ServiceInstituicao.buscarLista();

		obsInstituicaos = FXCollections.observableArrayList(listInstituicaos);
		tbvInstituicao.setItems(obsInstituicaos);

	}

	public void limparDados() {

		txtId.clear();
		txtNomeInst.clear();
		txtSite.clear();
	}

}
