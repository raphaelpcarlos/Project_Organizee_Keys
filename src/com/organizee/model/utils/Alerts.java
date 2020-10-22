package com.organizee.model.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	
	public static void Alerta(String title,String content, AlertType type) {
		
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(content);
		alert.setResizable(false);
		alert.show();
	}

}
