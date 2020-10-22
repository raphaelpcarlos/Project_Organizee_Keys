package com.organizee.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.organizee.model.entities.DadosAcesso;
import com.organizee.model.entities.Instituicao;
import com.organizee.model.entities.TipoNegociacao;
import com.organizee.model.utils.ControllKeys;
import com.organizee.model.utils.RetornUserSeg;
import com.organizee.services.ServiceDadosAcesso;
import com.organizee.services.ServiceInstituicao;
import com.organizee.services.ServiceNegociacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerBuscaAcesso implements Initializable {

	ServiceDadosAcesso serviceDadosAcesso = new ServiceDadosAcesso();
	ServiceInstituicao serviceInstituicao = new ServiceInstituicao();
	ServiceNegociacao serviceNegociacao = new ServiceNegociacao();

	DadosAcesso dadosAcesso = null;
	Instituicao instituicao = null;
	TipoNegociacao tipoNegociacao = null;

	@FXML
	private Label lblSenha;
	
	@FXML
	private Button btnPesquisar;

	@FXML
	private ComboBox<Instituicao> cbInstituição;

	@FXML
	private ComboBox<TipoNegociacao> cbTipoNegociacao;

	@FXML
	private TableView<DadosAcesso> tbvAcesso;

	@FXML
	private TableColumn<DadosAcesso, Integer> tbcId;

	@FXML
	private TableColumn<DadosAcesso, String> tbcUsuario;

	@FXML
	private TableColumn<DadosAcesso, String> tbcPass;

	@FXML
	private TableColumn<Instituicao, Instituicao> tbcInstituicao;

	@FXML
	private TableColumn<TipoNegociacao, TipoNegociacao> tbcTipo;

	@FXML
	private TextField txtDadosAcesso;

	@FXML
	void btnPesquisarAction(ActionEvent event) {

		if (txtDadosAcesso.getText().equals("")) {

			CarregarListaDados();

		} else {

			setCell();

			limparListas();
			listDadosAcesso.addAll(serviceDadosAcesso.buscarNome(txtDadosAcesso.getText(),RetornUserSeg.retornaUser()));
			listDadosAcesso.addAll(serviceDadosAcesso.buscarTipoNegociacao(txtDadosAcesso.getText(),RetornUserSeg.retornaUser()));
			listDadosAcesso.addAll(serviceDadosAcesso.buscarInstituicao(txtDadosAcesso.getText(),RetornUserSeg.retornaUser()));

			obsDadosAcesso = FXCollections.observableArrayList(listDadosAcesso);

			tbvAcesso.setItems(obsDadosAcesso);

		}

	}

	@FXML
	private void selecionarTipoNegociacao(ActionEvent event) {

		tipoNegociacao = cbTipoNegociacao.getSelectionModel().getSelectedItem();

		txtDadosAcesso.setText(tipoNegociacao.getTipoNegociacao());

	}

	@FXML
	private void selectionarInstituicao(ActionEvent event) {

		instituicao = cbInstituição.getSelectionModel().getSelectedItem();

		txtDadosAcesso.setText(instituicao.getNomeInstituicao());

	}

	List<DadosAcesso> listDadosAcesso = new ArrayList<DadosAcesso>();
	List<Instituicao> listInstituicaos = new ArrayList<Instituicao>();
	List<TipoNegociacao> listNegociacaos = new ArrayList<TipoNegociacao>();

	ObservableList<DadosAcesso> obsDadosAcesso;
	ObservableList<Instituicao> obsInstituicao;
	ObservableList<TipoNegociacao> obsTipoNegociacao;

	
	@FXML
	public void clicarSenha() {
		
		
		txtDadosAcesso.setText(ControllKeys.decrypt(tbvAcesso.getSelectionModel().getSelectedItem().getSenhaAcesso()));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		CarregarListaInstituicao();
		CarregarListaTipoNegociacao();
	}

	private void CarregarListaDados() {
		setCell();

		listDadosAcesso = serviceDadosAcesso.buscarLista(RetornUserSeg.retornaUser());

		obsDadosAcesso = FXCollections.observableArrayList(listDadosAcesso);

		tbvAcesso.setItems(obsDadosAcesso);
	}

	private void CarregarListaInstituicao() {

		listInstituicaos = serviceInstituicao.buscarLista();

		obsInstituicao = FXCollections.observableArrayList(listInstituicaos);

		cbInstituição.setItems(obsInstituicao);

	}

	private void CarregarListaTipoNegociacao() {

		listNegociacaos = serviceNegociacao.buscarList();

		obsTipoNegociacao = FXCollections.observableArrayList(listNegociacaos);

		cbTipoNegociacao.setItems(obsTipoNegociacao);

	}

	private void setCell() {
		tbcId.setCellValueFactory(new PropertyValueFactory<>("idAcesso"));
		tbcUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeAcesso"));
		tbcPass.setCellValueFactory(new PropertyValueFactory<>("senhaAcesso"));
		tbcInstituicao.setCellValueFactory(new PropertyValueFactory<>("instituicao"));
//		tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoNegociacao"));

	}

	private void limparListas() {
		listDadosAcesso.clear();
		listInstituicaos.clear();
		listNegociacaos.clear();
	}

}
