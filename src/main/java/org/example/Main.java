package org.example;

import org.example.assets.Constants_Prefs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.assets.Project_CompilePaths;

/**
 * Project_WorkSpace Allows Users To Contain Projects
 */
public class Main extends Application implements Project_CompilePaths {

	@Override
	public void start(Stage primaryStage) {
		try {
			String screenToShow;
			// todo THIS DOESNT WORK
			if(pref.getBoolean(Constants_Prefs.PROJECT_FirstTime, true)) {
				screenToShow = FILE_FXML_FirstTime;
			}else {
				screenToShow = FILE_FXML_Main;
			}
			Parent root = FXMLLoader.load(App.class.getResource(screenToShow));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(App.class.getResource(FILE_CSS).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		pref.putDouble(Constants_Prefs.WINDOW_SIZE_Width, 1500.0);
		pref.putDouble(Constants_Prefs.WINDOW_SIZE_Height, 800.0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
