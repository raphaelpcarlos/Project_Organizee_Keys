package com.organizee.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.organizee.db.DbException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;



public class ControllerMenu implements Initializable {

	@FXML
	private MenuBar menuCadastro;

	@FXML
	private MenuItem menuiItemCadNEgociacao;

	@FXML
	private MenuItem menuiItemCadInstituicao;

	@FXML
	private MenuItem menuiItemCadUsuarioAcesso;

	@FXML
	private Menu menuChave;

	@FXML
	private MenuItem menuItemCadChaves;

	@FXML
	private MenuItem menuItemBuscarChave;

	@FXML
	private Menu menuAbout;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	public void handleMenuItemCadNegociacao() {
		try {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/organizee/view/NegociacaoView.fxml"));
			anchorPane.getChildren().setAll(a);

		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	@FXML
	public void handleMenuItemCadInstituicao() {
		try {
			AnchorPane a = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/organizee/view/InstituicaoView.fxml"));
			anchorPane.getChildren().setAll(a);

		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	@FXML
	public void handleMenuItemCadUsuarioSeguranca() {
		try {
			AnchorPane a = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/organizee/view/UsuarioSegurancaView.fxml"));
			anchorPane.getChildren().setAll(a);

		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	@FXML
	public void handleMenuItemChaveBuscarDadosAcesso() {
		try {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/organizee/view/BuscarDados.fxml"));
			anchorPane.getChildren().setAll(a);

		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	@FXML
	public void handleMenuItemChaveDadosAcesso() {
		try {
			AnchorPane a = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/organizee/view/DadosAcessoView.fxml"));
			anchorPane.getChildren().setAll(a);

		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
