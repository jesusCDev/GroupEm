package org.allvens.controllers;

import org.allvens.assets.Path_Manager;
import org.allvens.assets.Project_CompilePaths;
import org.allvens.assets.Constants_Prefs;
import org.allvens.controllers_managers.Project_Workspace_Manager;
import org.allvens.data_manager.project.project_info_manager.Project_Values;
import org.allvens.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class Project_WorkSpace extends Common_ControllerMethods implements Project_CompilePaths, Project_Values {

	@FXML
	BorderPane bpAll;
	@FXML
	Label lbCurrentProject;
	@FXML
	Label lbProjectTitle;
	@FXML
	Label lbProjectName;
	@FXML
	Label lbProjectStatus;
	@FXML
	Label lbProjectPurpose;
	@FXML
	HBox hbTech;
	@FXML
    HBox hbDevices;
	@FXML
	HBox hbHashTags;
	@FXML
	VBox vbProjectDevelopment;
	@FXML
	VBox vbProjectDocumentationDevelopmentBtns;
    @FXML
    VBox vbTodo_Starting;
    @FXML
    VBox vbTodo_Planning;
    @FXML
    VBox vbTodo_Working;
    @FXML
    VBox vbTodo_Fixing;
    @FXML
    VBox vbTodo_Finished;
	@FXML
	ScrollPane sbHas;
	@FXML
    TabPane tpTodoList;
	@FXML
    TextField tfLocalTodoList;
	@FXML
    Button btn_TodoListEdit;

	private Project_Workspace_Manager manager;

	@FXML
	public void initialize() {
	    set_ScreenSize(bpAll);

        manager = new Project_Workspace_Manager();
        manager.setUp_Workspace_UI_Manager(bpAll, hbHashTags, vbProjectDocumentationDevelopmentBtns);
        manager.update_ConstantLabels(lbCurrentProject, lbProjectTitle, lbProjectName, lbProjectStatus, lbProjectPurpose,
                vbProjectDevelopment);
        manager.create_TagSets(hbTech, hbDevices);
		manager.setUp_TodoList(btn_TodoListEdit, tpTodoList, vbTodo_Starting, vbTodo_Planning, vbTodo_Working, vbTodo_Fixing, vbTodo_Finished);
		manager.setUp_Development_HashTagContainers();
	}

    /****************************************
     /**** BUTTON ACTIONS
     ****************************************/

    public void btn_FolderContainer(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(new Path_Manager().DIR_PROJECT_WORKSPACE));
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog((((Button)e.getSource()).getScene().getWindow()));
    }

    @FXML
    public void btn_deleteLocalTask(ActionEvent e){
        manager.change_CurrentTodoListView(((Button)e.getSource()));
    }

    @FXML
    public void btn_AddLocalTask(ActionEvent e){
        String task = tfLocalTodoList.getText();
        manager.add_TaskTodoList(task, tfLocalTodoList);
    }

	/**
	 * Returns user to previous screen
	 * @param e
	 */
	@FXML
	public void btn_ReturnToMainScreen(ActionEvent e) {
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_Main, e, WINDOW_TITLE_Main, bpAll, false);
	}
	
	/**
	 * Allows user to edit current project
	 * @param e
	 */
	@FXML
	public void btn_EditCurrentProject(ActionEvent e) {
		pref.putBoolean(Constants_Prefs.FILE_EDITING_EditingSelectedFile, true);
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_CreateNewProject, e, WINDOW_TITLE_CreateNewProject, bpAll, false);
	}
	
	/**
	 * Adds development
	 * @param e
	 */
	@FXML
	public void btn_addProjectDevelopment(ActionEvent e) {
		pref.putBoolean(Constants_Prefs.FILE_EDITING_EditingSelectedFile, false);
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_DocumentEditor, e, WINDOW_TITLE_DocumentEditor, bpAll, false);
	}

	// todo maybe update when leaving the scene (quiting or changin gscreens)
}
