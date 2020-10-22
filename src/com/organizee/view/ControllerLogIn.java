package com.organizee.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.organizee.model.entities.UsuarioSeguranca;
import com.organizee.model.utils.Alerts;
import com.organizee.model.utils.ControllKeys;
import com.organizee.model.utils.RetornUserSeg;
import com.organizee.services.ServiceUsuarioSeguranca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLogIn implements Initializable {

	ServiceUsuarioSeguranca serviceUsuarioSeguranca = new ServiceUsuarioSeguranca();
	UsuarioSeguranca usuarioSeguranca = null;

	@FXML
	private Button btnEntrar;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtUsuarioAcesso;

	@FXML
	private PasswordField txtSenhaAcesso;

	@FXML
	void btnCancelarAction(ActionEvent event) {

	}

	@FXML
	void btnEntrarAction(ActionEvent event) {

		selecionarUsuario();

		if (usuarioSeguranca == null
				|| (!(txtSenhaAcesso.getText().equals(ControllKeys.decrypt(usuarioSeguranca.getPass()))))) {

			Alerts.Alerta("Controller Keys", "Usuários ou senha invalidos!", AlertType.WARNING);

		} else {

			Stage stage = new Stage();
			try {

				Parent parent = FXMLLoader.load(getClass().getResource("MenuView.fxml"));

				Scene scene = new Scene(parent);

				stage.setScene(scene);
				stage.setTitle("Organizee keys");
				stage.show();

				btnEntrar.getScene().getWindow().hide();
				
				RetornUserSeg.recebeUser(selecionarUsuario());

			} catch (IOException e) {
				e.getMessage();
			}

		}

	}

	public UsuarioSeguranca selecionarUsuario() {

		usuarioSeguranca = serviceUsuarioSeguranca.buscarUsuarioSeguranca(txtUsuarioAcesso.getText());

		return usuarioSeguranca;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
