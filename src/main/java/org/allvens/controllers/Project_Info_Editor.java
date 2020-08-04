package org.allvens.controllers;

import javax.swing.JOptionPane;
import org.allvens.assets.Project_CompilePaths;
import org.allvens.controllers_managers.Project_Info_Editor_Manager;
import org.allvens.data_manager.projects.Projects_Values;
import org.allvens.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This class will be used to save values the user puts
 * Then it will create a project
 * @author jesuscdev
 * TODO CAHNGE NAME OF THIS TO EDIT OR SEOMTHING ELSE SINCE WE ARE USING IT TO CREATE BUTTONS
 *
 */
public class Project_Info_Editor extends Common_ControllerMethods implements Project_CompilePaths, Projects_Values {

	@FXML
	BorderPane  bpAll;
	@FXML
	TextField tfProjectTitle;
	@FXML
	TextField tfProjectName;
	@FXML
	TextArea tfProjectPurpose;
	@FXML
	TextArea tfProjectSummary;
	@FXML
	RadioButton rbStatusOngoing;
	@FXML
	RadioButton rbStatusReturnTo;
	@FXML
	RadioButton rbStatusComplete;
	@FXML
	Button btnSubmitInfo;
	@FXML
	Label lbProjectTitle;
	@FXML
    HBox hbDevices;
	@FXML
    HBox hbTech;

	private Project_Info_Editor_Manager manager;

	public void initialize() {
	    set_ScreenSize(bpAll);

        manager = new Project_Info_Editor_Manager();
        manager.setUp_UIManager();
        manager.create_CheckBoxes(hbTech, hbDevices);
        manager.set_DocumentValues(btnSubmitInfo, lbProjectTitle, tfProjectName, tfProjectTitle,
                tfProjectPurpose, tfProjectSummary,
                rbStatusOngoing, rbStatusReturnTo, rbStatusComplete);
        manager.set_TextFieldLimit(tfProjectPurpose);
        manager.setUp_Toggles(rbStatusOngoing, rbStatusReturnTo, rbStatusComplete);
	}

	/******* BUTTON ACTION *******/
	
	@FXML
	public void btn_InformUserAboutMarkUpLanguage(ActionEvent e) {
		presentUserInformation(markupLanguageInfo);
	}
	
	@FXML
	public void btn_PresentExistingProjects(ActionEvent e) {
	    manager.show_UserExistingProjects();
	}
	
	@FXML
	public void btn_ReturnToMainMenu(ActionEvent e){
		String fxmlToReturnTo = FILE_FXML_Main;
		String windowTitle = WINDOW_TITLE_Main;
		if(manager.get_FileBeingEdited()) {
			fxmlToReturnTo = FILE_FXML_ProjectView;
            windowTitle = WINDOW_TITLE_ProjectView;
		}
		changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, fxmlToReturnTo, e, windowTitle, bpAll, false);
	}

	/**
	 * Creates projects by verrifying its availibility and gathers its info
	 * @param e
	 */
	@FXML
	public void btn_CreateProject(ActionEvent e) {

		String projectName = tfProjectName.getText();

		if (manager.get_FileBeingEdited() || (!projectName.trim().isEmpty() && !manager.check_IfProjectAlreadyExist(projectName))) {

			String projectTitle = tfProjectTitle.getText();
			String projectStatus = manager.getRbCurrentStatus();
			String projectPurpose = tfProjectPurpose.getText();
			String projectSummary = tfProjectSummary.getText();

			if(!manager.edit_Project(projectName, projectTitle, projectPurpose, projectStatus, projectSummary)) {
			    manager.create_NewProject(projectName, projectTitle, projectStatus, projectPurpose, projectSummary);
			}
			changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_ProjectView, e, WINDOW_TITLE_ProjectView, bpAll, false);

		}else {
			if(manager.check_IfProjectAlreadyExist(projectName)) {
				JOptionPane.showMessageDialog(null, "Project_WorkSpace Already Created.");
			}else {
				JOptionPane.showMessageDialog(null, "Please enter at least a name for the project.");
			}
		}
	}
}
