package org.example.controllers_managers;

import org.example.controllers_ui.Home_UI_Manager;
import org.example.data_manager.projects.Projects_XML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Home_Manager {

    private Home_UI_Manager ui_manager;
    private Projects_XML projects_Info;

    public Home_Manager(){
        projects_Info = new Projects_XML();
    }

    public void setUp_UiManager(VBox vbOngoingProjectListMenu, VBox vbReturnToProjectListMenu, VBox vbCompleteProjectListMenu, BorderPane bpAll) {
        ui_manager = new Home_UI_Manager(vbOngoingProjectListMenu, vbReturnToProjectListMenu, vbCompleteProjectListMenu, bpAll);
        ui_manager.create_Screen(projects_Info, false);
    }

    /****************************************
     /**** BUTTON MANAGEMENT
     ****************************************/

    public void set_ActionBtn(Button btn) {
        btn.getStyleClass().remove("deleteBtn");
        btn.getStyleClass().add("actionBtn");
        btn.setText("Delete Project");
        ui_manager.create_Screen(projects_Info, false);
    }

    public void set_DeleteBtn(Button btn) {
        btn.getStyleClass().remove("actionBtn");
        btn.getStyleClass().add("deleteBtn");
        btn.setText("Cancel");
        ui_manager.create_Screen(projects_Info, true);
    }
}
