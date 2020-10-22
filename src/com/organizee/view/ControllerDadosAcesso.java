package com.organizee.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.organizee.model.entities.Instituicao;
import com.organizee.model.entities.UsuarioSeguranca;
import com.organizee.model.utils.Alerts;
import com.organizee.model.utils.ControllKeys;
import com.organizee.model.utils.RetornUserSeg;
import com.organizee.services.ServiceDadosAcesso;
import com.organizee.services.ServiceInstituicao;
import com.organizee.services.ServiceUsuarioSeguranca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerDadosAcesso implements Initializable {

	ServiceInstituicao serviceInstituicao = new ServiceInstituicao();
	ServiceDadosAcesso serviceDadosAcesso = new ServiceDadosAcesso();
	ServiceUsuarioSeguranca serviceUsuarioSeguranca = new ServiceUsuarioSeguranca();
	Instituicao instituicao = null;

	@FXML
	private TextField txtUsuarioAcesso;

	@FXML
	private TextField txtSenha01;

	@FXML
	private TextField txtSenha02;

	@FXML
	private Button btnGerarSenha;

	@FXML
	private TextField txtQdCaracter;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnLimpar;

	// Lista de instituicao
	List<Instituicao> listInstituicao = new ArrayList<Instituicao>();
	ObservableList<Instituicao> obsInstituicao;

	@FXML
	private ComboBox<Instituicao> cbInstituicao;

	@FXML
	void btnGerarSenhaAction(ActionEvent event) {

		if (Integer.parseInt(txtQdCaracter.getText()) < 5) {

			Alerts.Alerta("Organizze Keys", "informe uma quantidade maior que 5", AlertType.WARNING);

		} else {

			String code = ControllKeys.gerarKeys(Integer.parseInt(txtQdCaracter.getText()));

			txtSenha01.setText(code);
			txtSenha02.setText(code);

		}
	}

	@FXML
	void btnInserirAction(ActionEvent event) {

		if (txtUsuarioAcesso.getText().equalsIgnoreCase("") || txtSenha01.getText().equalsIgnoreCase("")
				|| txtSenha02.getText().equalsIgnoreCase("") || selecionarIntituicao() == null) {

			Alerts.Alerta("Organizze Keys", "Existem campos em brancos!", AlertType.WARNING);

			limparCampos();

		} else if (!(ControllKeys.compareKeys(txtSenha01.getText(), txtSenha02.getText()))) {

			limparCampos();

		} else {

			if (ControllKeys.verificaQuantidade(txtSenha01.getText())) {

				UsuarioSeguranca usuarioSeguranca = RetornUserSeg.retornaUser();
				
				serviceDadosAcesso.inserirRegistro(null, txtUsuarioAcesso.getText(),
						ControllKeys.encrypt(txtSenha01.getText()), selecionarIntituicao(), usuarioSeguranca);

				limparCampos();

			}
		}

	}

	@FXML
	void btnLimparAction(ActionEvent event) {
		limparCampos();
		txtUsuarioAcesso.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTipoInstituicao();

	}

	@FXML
	public Instituicao selecionarIntituicao() {
		instituicao = cbInstituicao.getSelectionModel().getSelectedItem();

		return instituicao;
	}

	private void carregarTipoInstituicao() {

		listInstituicao.addAll(serviceInstituicao.buscarLista());

		obsInstituicao = FXCollections.observableArrayList(listInstituicao);

		cbInstituicao.setItems(obsInstituicao);
	}

	// Metodo para limpar campos
	private void limparCampos() {
		txtSenha01.clear();
		txtSenha02.clear();
		txtQdCaracter.clear();

	}

}
