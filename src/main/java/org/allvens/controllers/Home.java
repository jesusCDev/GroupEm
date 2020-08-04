package org.allvens.controllers;

import org.allvens.assets.Project_CompilePaths;
import org.allvens.assets.Constants_Prefs;
import org.allvens.controllers_managers.Home_Manager;
import org.allvens.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Home extends Common_ControllerMethods implements Project_CompilePaths {

	@FXML
	BorderPane bpAll;
	@FXML
	VBox vbOngoingProjectListMenu;
	@FXML
	VBox vbReturnToProjectListMenu;
	@FXML
	VBox vbCompleteProjectListMenu;
	@FXML
	Button btnDeleteProject;

	private Home_Manager manager;
	private int deleteIterator = 1;

	public void initialize() {
        set_ScreenSize(bpAll);

        manager = new Home_Manager();
        manager.setUp_UiManager(vbOngoingProjectListMenu, vbReturnToProjectListMenu, vbCompleteProjectListMenu, bpAll);
        //Font.loadFont(Home.class.getResource("/res/Lobster-Regular.ttf").toExternalForm(), 100);

	}

    /****************************************
     /**** BUTTON ACTIONS
     ****************************************/
	@FXML
	public void btn_ChangeToDeleteMode() {
		if(deleteIterator == 1) {
		    manager.set_DeleteBtn(btnDeleteProject);
		}else {
            manager.set_ActionBtn(btnDeleteProject);
		}
		deleteIterator *= -1;
	}

	@FXML
	public void btn_goToSettings(ActionEvent e) {
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_Settings, e, WINDOW_TITLE_Settings, bpAll, false);
	}
	
	@FXML
	public void btn_AddNewProject(ActionEvent e) {
		pref.putBoolean(Constants_Prefs.FILE_EDITING_EditingSelectedFile, false);
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_CreateNewProject, e, WINDOW_TITLE_CreateNewProject, bpAll, false);
	}
}