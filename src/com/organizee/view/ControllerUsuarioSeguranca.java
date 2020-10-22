package com.organizee.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.organizee.model.entities.UsuarioSeguranca;
import com.organizee.model.utils.Alerts;
import com.organizee.model.utils.ControllKeys;
import com.organizee.services.ServiceUsuarioSeguranca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerUsuarioSeguranca implements Initializable {

	ServiceUsuarioSeguranca serviceUsuarioSeguranca = new ServiceUsuarioSeguranca();
	UsuarioSeguranca usuarioSeguranca = null;

	@FXML
	private CheckBox cbUsuarioAtivo;

	@FXML
	private Button btnInserir;

	@FXML
	private TextField txtNome;

	@FXML
	private PasswordField txtSenha01;

	@FXML
	private PasswordField txtSenha02;

	@FXML
	private Button btnGerarSenha;

	@FXML
	private Button btnLimparDados;

	@FXML
	private TextField txtQuantidade;

	@FXML
	void btnGerarSenha(ActionEvent event) {

		try {

			if (Integer.parseInt(txtQuantidade.getText()) < 5) {

				Alerts.Alerta("Organizze Keys", "informe uma quantidade maior que 5", AlertType.WARNING);

			} else {

				String code = ControllKeys.gerarKeys(Integer.parseInt(txtQuantidade.getText()));

				txtSenha01.setText(code);
				txtSenha02.setText(code);

			}
		} catch (NumberFormatException e) {
			Alerts.Alerta("Organizze Keys", "Error!", AlertType.ERROR);
		}
	}

	@FXML
	public void btnLimparDados() {
		limparDados();
	}

	@FXML
	void btnInserirAction() {

		if (validarUsuario()) {

			if (txtNome.getText().equalsIgnoreCase("") || txtSenha01.getText().equalsIgnoreCase("")
					|| txtSenha02.getText().equalsIgnoreCase("")) {

				Alerts.Alerta("Organizze Keys", "Existem campos em brancos!", AlertType.WARNING);

			} else if (!(ControllKeys.compareKeys(txtSenha01.getText(), txtSenha02.getText()))) {

			} else {

				if (cbUsuarioAtivo.selectedProperty().getValue() != null) {

					serviceUsuarioSeguranca.inseririUsuarioSeguranca(null, txtNome.getText(),
							ControllKeys.encrypt(txtSenha01.getText()), 0);
					limparDados();
				} else
					serviceUsuarioSeguranca.inseririUsuarioSeguranca(null, txtNome.getText(),
							ControllKeys.encrypt(txtSenha01.getText()), 1);
				limparDados();

			}

		} else {
			Alerts.Alerta("Controller keys", "Usuário informado em uso!", AlertType.INFORMATION);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	private void limparDados() {

		txtNome.setText("");
		txtSenha01.setText("");
		txtSenha02.setText("");
		txtQuantidade.setText("0");

	}

	private boolean validarUsuario() {

		if (serviceUsuarioSeguranca.buscarUsuarioSeguranca(txtNome.getText()) == null) {

			return true;
		} else {
			return false;
		}
	}
}
