package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/organizee/view/MenuView.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/organizee/viewNegociacaoView.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/organizee/view/InstituicaoView.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/organizee/view/DadosAcessoView.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/organizee/view/UsuarioSegurancaView.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/organizee/view/LogInView.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/organizee/view/BuscarDados.fxml"));
			Parent parent = loader.load();
			
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Organizze Keys");
			
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
