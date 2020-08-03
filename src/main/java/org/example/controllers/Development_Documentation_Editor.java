package org.example.controllers;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.example.assets.Project_CompilePaths;
import org.example.controllers_managers.Development_Documentation_Editor_Manager;
import org.example.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Development_Documentation_Editor extends Common_ControllerMethods implements Project_CompilePaths {

    @FXML
    Button btnEditButton;
	@FXML
	BorderPane bpAll;
	@FXML
	TextField tfTitle;
	@FXML
	TextField tfHashTags;
	@FXML
	TextArea taOutline;
	@FXML
	VBox vbDevelopmentContent;

    // Tracks Button Display
	private Development_Documentation_Editor_Manager manager;

    @FXML
	public void initialize() {
        set_ScreenSize(bpAll);

        manager = new Development_Documentation_Editor_Manager();
        manager.setUp_UIManager();
        manager.setUp_EditingDocumentValues(tfTitle, tfHashTags, taOutline, vbDevelopmentContent);
	}


    /****************************************
     /**** BUTTON ACTIONS
     ****************************************/

	@FXML
    public void btn_showOrHideEditButtons(ActionEvent e){
	    manager.showHide_EditScreen(e);
    }

    @FXML
	public void btn_ReturnToProjectView(ActionEvent e) {
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_ProjectView, e, WINDOW_TITLE_ProjectView, bpAll, false);
	}

    @FXML
    public void btn_AddPicture(ActionEvent e){
	    manager.close_EditScreen(btnEditButton);
        manager.create_ImageSection(vbDevelopmentContent);
    }

    @FXML
    public void btn_AddTextSection(ActionEvent e){
	    manager.close_EditScreen(btnEditButton);
        manager.create_TextSection(vbDevelopmentContent);
    }

    @FXML
    public void btn_InformUserAboutMarkUpLanguage(ActionEvent e){
        presentUserInformation(markupLanguageInfo);
    }

	@FXML
	public void btn_SaveDocumentation(ActionEvent e) {
	    manager.save_Values(tfTitle, tfHashTags, taOutline, vbDevelopmentContent);
	    manager.goto_NextScene(e, bpAll);
	}
}
