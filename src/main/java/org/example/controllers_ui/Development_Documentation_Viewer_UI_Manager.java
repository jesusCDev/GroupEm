package org.example.controllers_ui;

import org.example.assets.Path_Manager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import org.example.markupLanguage.MarkUpLanguage_Render;

import java.io.File;
import java.util.ArrayList;

public class Development_Documentation_Viewer_UI_Manager {
    public ArrayList<TextFlow> createTextSection(String textAreaContent){

        MarkUpLanguage_Render markupSummary = new MarkUpLanguage_Render(textAreaContent);
        return markupSummary.getTextFlowPanes();
    }

    public ImageView createImageView(String imageName){
        ImageView iv = new ImageView();
        iv.setImage(new Image(new File(new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES + imageName).toURI().toString()));
        iv.setFitWidth((300));
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        return iv;
    }
}
