package org.allvens.controllers_ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.util.ArrayList;

public class Development_Documentation_Editor_UI_Manager {

    /// keeps track of buttons and content field incase needs to be deleted
    private ArrayList<Button> editBtns = new ArrayList<>();
    private ArrayList<StackPane> contentField = new ArrayList<>();
    private ArrayList<String> contentBeingAdded = new ArrayList<>();

    public ArrayList<StackPane> get_ContentField() {
        return contentField;
    }

    public ArrayList<Button> get_EditBtns(){
        return editBtns;
    }

    public ArrayList<String> get_ContentBeingAdded(){
        return contentBeingAdded;
    }

    public StackPane createContentField(Node content, String textOrImage){
        StackPane sp = new StackPane();
        // add it to stack pane first
        sp.getChildren().add(content);
        contentBeingAdded.add(textOrImage);

        // Save Area
        contentField.add(sp);
        // Create Button
        Button btn = new Button("x");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contentBeingAdded.remove(textOrImage);
                contentField.remove(sp);

                sp.getChildren().clear();
                editBtns.remove(btn);

            }
        });

        btn.setStyle("-fx-font-family: 'Actor'; -fx-font-size: 12px; -fx-background-radius: 4em;");
        btn.getStyleClass().add("deleteBtn");
        btn.setVisible(false);
        StackPane.setAlignment(btn, Pos.TOP_RIGHT);

        editBtns.add(btn);
        sp.getChildren().add(btn);

        return sp;
    }

    public TextArea createTextArea(String message){
        TextArea ta = new TextArea(message);
        return ta;
    }

    public ImageView createImageView(String imagePath){
        ImageView iv = new ImageView();
        iv.setImage(new Image(new File(imagePath).toURI().toString()));
        iv.setFitWidth((300));
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        return iv;
    }
}
