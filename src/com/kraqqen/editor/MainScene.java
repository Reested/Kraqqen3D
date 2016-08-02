package com.kraqqen.editor;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MainScene extends Pane {
	
	public Button button;
	
	public MainScene()
	{
		button = new Button();
		button.setOnMouseClicked((event) -> {
			button.setText(getWidth() + ", " + getHeight());
		});
		getChildren().add(button);
	}

}
