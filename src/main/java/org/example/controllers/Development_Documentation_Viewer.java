package org.example.controllers;

import org.example.assets.Project_CompilePaths;
import org.example.assets.Constants_Prefs;
import org.example.controllers_managers.Development_Documentation_Viewer_Manager;
import org.example.default_methods.Common_ControllerMethods;
import org.example.data_manager.project.projec_development_info_button.DocumentDevelopment_Button_XML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Development_Documentation_Viewer extends Common_ControllerMethods implements Project_CompilePaths {

	@FXML
	BorderPane bpAll;
	@FXML
	Label lbName;
	@FXML
	Label lbDateEdited;
	@FXML
	Label lbDateCreated;
	@FXML
	Label lbHashtags;
	@FXML
	VBox vbDevelopmentContent;

	private Development_Documentation_Viewer_Manager manager;

	@FXML
	public void initialize() {
		set_ScreenSize(bpAll);

        manager = new Development_Documentation_Viewer_Manager();
        manager.present_Constants(lbName, lbDateCreated, lbDateEdited, lbHashtags);
        manager.setUp_UIManager();
        manager.presentContent(vbDevelopmentContent);
	}

    /****************************************
     /**** BUTTON ACTIONS
     ****************************************/

	@FXML
	public void btn_DeleteCurrentDocument(ActionEvent e) {
		DocumentDevelopment_Button_XML document = new DocumentDevelopment_Button_XML(false);
		document.deleteDocument(document.getDocumentById(manager.get_DocID()));
		document.updateXMLFile();
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_ProjectView, e, WINDOW_TITLE_ProjectView, bpAll, false);
	}
	@FXML
	public void btn_EditCurrentDocument(ActionEvent e) {
		pref.putBoolean(Constants_Prefs.FILE_EDITING_EditingSelectedFile, true);
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_DocumentEditor, e, WINDOW_TITLE_DocumentEditor, bpAll, false);
	}
	
	@FXML
	public void btn_ReturnToProjectView(ActionEvent e) {
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_ProjectView, e, WINDOW_TITLE_ProjectView, bpAll, false);
	}
}
