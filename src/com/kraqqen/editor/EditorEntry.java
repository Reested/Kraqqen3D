package com.kraqqen.editor;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorEntry extends Application {
	
	public static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		(EditorEntry.stage = stage).setTitle("Kraqqen3D");
		stage.setScene(new Scene(FXMLLoader.load(new File("res/engine/ui.fxml").toURI().toURL()), 1280, 700));
		stage.getScene().getStylesheets().add(new File("res/engine/style/main.css").toURI().toURL().toExternalForm());
		
		stage.show();
	}
	
	public static void main(String[] args) { launch(args); }

}
