package org.allvens.controllers_managers;

import org.allvens.controllers_ui.Development_Documentation_Viewer_UI_Manager;
import org.allvens.data_manager.project.project_development_info_.DocumentDevelopment_XML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Development_Documentation_Viewer_Manager {

    private Development_Documentation_Viewer_UI_Manager ui_manager;
    private DocumentDevelopment_XML document;

    public Development_Documentation_Viewer_Manager(){
        document = new DocumentDevelopment_XML(false);
    }

    public void setUp_UIManager(){
        ui_manager = new Development_Documentation_Viewer_UI_Manager();
    }

    public void present_Constants(Label lbName, Label lbDateCreated, Label lbDateEdited, Label lbHashtags){
        lbName.setText(document.getDocument().getDocumentName());
        lbDateCreated.setText(document.getDocument().getDocumentDateCreated());
        lbDateEdited.setText(document.getDocument().getDocumentDateEdited());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < document.getDocument().getDocumentHashtags().length; i++) {
            sb.append(document.getDocument().getDocumentHashtags()[i]);
            if((1 + i) < document.getDocument().getDocumentHashtags().length){
                sb.append(", ");
            }
        }
        lbHashtags.setText(sb.toString());
    }

    public void presentContent(VBox vbDevelopmentContent){

        ArrayList<Node> textOrImages = new ArrayList<>();
        boolean broke;

        for(int i = 0; i < (document.getDocument().getImagePaths().length + document.getDocument().getTextDocumentation().length); i++){
            broke = false;

            for(int j = 0; j < document.getDocument().getTextDocumentation().length; j++) {
                if (document.getDocument().getTextDocumentation()[j][0].equals(Integer.toString(i))) {
                    textOrImages.addAll(ui_manager.createTextSection(document.getDocument().getTextDocumentation()[j][1]));
                    broke = true;
                    break;
                }
            }

            if(!broke){
                for(int j = 0; j < document.getDocument().getImagePaths().length; j++) {
                    if(document.getDocument().getImagePaths()[j][0].equals(Integer.toString(i))){
                        textOrImages.add(ui_manager.createImageView(document.getDocument().getImagePaths()[j][1]));
                        break;
                    }
                }
            }
        }

        vbDevelopmentContent.getChildren().addAll(textOrImages);
    }

    public String get_DocID(){
        return document.getDocument().getDocumentID();
    }
}
