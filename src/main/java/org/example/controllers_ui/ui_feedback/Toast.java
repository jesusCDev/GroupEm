package org.example.controllers_ui.ui_feedback;

import org.example.assets.Project_CompilePaths;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Notify User
 */
public class Toast {

    /**
     * Sets up basic message toast
     * @param message
     * @param windowTitle
     */
    public void showMessage(String message, String windowTitle){
        createNewWindow(message, windowTitle);
    }

    /**
     * Creates Toast With A New Stage
     * @param message
     * @param windowTitle
     */
    private void createNewWindow(String message, String windowTitle){
        Stage stage = new Stage();

        BorderPane bpAll = new BorderPane();
        bpAll.getStyleClass().add("background");
        VBox vbContainer = new VBox();
        vbContainer.getStyleClass().add("container");
        vbContainer.getStyleClass().add("vbContainer");

        Label lbMessage = new Label(message);
        lbMessage.getStyleClass().add("outlabels");
        lbMessage.getStyleClass().add("title_2");

        Button btnCloseWindow = new Button(Project_CompilePaths.TEXT_TOAST_MESSAGE_Okay);
        btnCloseWindow.getStyleClass().add("btnDefault");

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                stage.hide();
            }
        });
        btnCloseWindow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.hide();
            }
        });

        vbContainer.setAlignment(Pos.CENTER);
        vbContainer.getChildren().add(lbMessage);
        vbContainer.getChildren().add(btnCloseWindow);
        bpAll.setCenter(vbContainer);

        stage.setTitle(windowTitle);
        stage.resizableProperty().setValue(false);

        Scene scene = new Scene(bpAll, Project_CompilePaths.WINDOW_SIZE_TOAST_Width, Project_CompilePaths.WINDOW_SIZE_TOAST_Height);
        scene.getStylesheets().add(getClass().getResource(Project_CompilePaths.FILE_CSS).toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
    }
}
