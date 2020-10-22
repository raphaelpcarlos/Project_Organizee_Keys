package com.organizee.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.organizee.model.entities.TipoNegociacao;
import com.organizee.services.ServiceNegociacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControllerNegociacao implements Initializable {

	ServiceNegociacao serviceNegociacao = new ServiceNegociacao();

	@FXML
	private SplitPane spPrincipal;

	@FXML
	private AnchorPane spEsquerda;

	@FXML
	private TableView<TipoNegociacao> tbvNegociacao;

	@FXML
	private TableColumn<TipoNegociacao, Integer> tbvCodigo;

	@FXML
	private TableColumn<TipoNegociacao, String> tbvNome;

	// Atributo da lista de Tipo de negociaçao
	List<TipoNegociacao> listTipoNegociacao = new ArrayList<TipoNegociacao>();

	// ObservableList de tipo de negociaçao
	ObservableList<TipoNegociacao> obseravableListTipoNegociacao;

	@FXML
	private AnchorPane spDireita;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimparDados;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtNome;

	@FXML
	void btnEditar(ActionEvent event) {
		serviceNegociacao.editarNegociacao(Integer.parseInt(txtCodigo.getText()), txtNome.getText());
		txtNome.setText("");

		atualizarListaNegociacao();

	}

	@FXML
	void btnExcluir(ActionEvent event) {

		Integer id = Integer.parseInt(txtCodigo.getText());
		txtNome.setText("");

		serviceNegociacao.excluirNegociacao(id);

		atualizarListaNegociacao();

	}

	@FXML
	void btnInserir(ActionEvent event) {

		String dados = txtNome.getText();
		String id = txtCodigo.getText();

		if (id.equalsIgnoreCase("")) {
			serviceNegociacao.inserirNegociacao(dados);
			txtNome.setText("");

			atualizarListaNegociacao();

		} else {

		}

	}

	@FXML
	void limparDadosAction(ActionEvent event) {

		txtCodigo.clear();
		txtNome.clear();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTableViewTipoNegociacao();

	}

	// Carregar a tableView
	public void carregarTableViewTipoNegociacao() {

		tbvCodigo.setCellValueFactory(new PropertyValueFactory<>("idNegociacao"));
		tbvNome.setCellValueFactory(new PropertyValueFactory<>("tipoNegociacao"));

		atualizarListaNegociacao();
	}

	// Metodo para selecionar item ao clicar
	@FXML
	public void clicarItemListNegociacao() {
		TipoNegociacao tipoNegociacao = tbvNegociacao.getSelectionModel().getSelectedItem();

		if (tipoNegociacao == null) {

		} else {

			txtCodigo.setText(String.valueOf(tipoNegociacao.getIdNegociacao()));
			txtNome.setText(tipoNegociacao.getTipoNegociacao());
		}

	}

	// Metodo para atualizar lista de negociação
	private void atualizarListaNegociacao() {

		listTipoNegociacao = serviceNegociacao.buscarList();

		obseravableListTipoNegociacao = FXCollections.observableArrayList(listTipoNegociacao);
		tbvNegociacao.setItems(obseravableListTipoNegociacao);
	}
}
