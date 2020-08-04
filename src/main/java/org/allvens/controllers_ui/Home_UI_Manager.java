package org.allvens.controllers_ui;

import org.allvens.assets.Constants_Prefs;
import org.allvens.assets.Project_CompilePaths;
import org.allvens.data_manager.projects.Projects_Info;
import org.allvens.data_manager.projects.Projects_XML;
import org.allvens.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Home_UI_Manager implements Project_CompilePaths {

    private VBox vbOngoingProjectListMenu;
    private VBox vbReturnToProjectListMenu;
    private VBox vbCompleteProjectListMenu;
    private BorderPane bpAll;

    public Home_UI_Manager(VBox vbOngoingProjectListMenu, VBox vbReturnToProjectListMenu, VBox vbCompleteProjectListMenu, BorderPane bpAll) {
        this.vbOngoingProjectListMenu = vbOngoingProjectListMenu;
        this.vbReturnToProjectListMenu = vbReturnToProjectListMenu;
        this.vbCompleteProjectListMenu = vbCompleteProjectListMenu;
        this.bpAll = bpAll;
    }

    public void create_Screen(Projects_XML projects, boolean deleteEdition) {

        vbOngoingProjectListMenu.getChildren().clear();
        vbReturnToProjectListMenu.getChildren().clear();
        vbCompleteProjectListMenu.getChildren().clear();

        if(projects.getProjects().size() != 0) {
            for(int i = 0; i < projects.getProjects().size(); i++) {
                VBox btnForProjectOnMainScreen = create_Buttons(projects, projects.getProjects().get(i), deleteEdition);

                // todo maybe organize this in the class
                if(projects.getProjects().get(i).getProjectStatus().equals(kw_StatusOngoing)) {
                    vbOngoingProjectListMenu.getChildren().add(btnForProjectOnMainScreen);
                }else if(projects.getProjects().get(i).getProjectStatus().equals(kw_StatusReturnTo)) {
                    vbReturnToProjectListMenu.getChildren().add(btnForProjectOnMainScreen);
                }else {
                    vbCompleteProjectListMenu.getChildren().add(btnForProjectOnMainScreen);
                }

            }
        }else {
            Label emptyList = new Label("Create Your First Project");
            emptyList.getStyleClass().add("subText");

            // Ongoing
            vbOngoingProjectListMenu.getChildren().add(emptyList);
            vbOngoingProjectListMenu.setAlignment(Pos.BASELINE_CENTER);
            // Return To
            emptyList = new Label("Create Your First Project");
            emptyList.getStyleClass().add("subText");
            vbReturnToProjectListMenu.getChildren().add(emptyList);
            vbReturnToProjectListMenu.setAlignment(Pos.BASELINE_CENTER);
            // Complete
            emptyList = new Label("Create Your First Project");
            emptyList.getStyleClass().add("subText");
            vbCompleteProjectListMenu.getChildren().add(emptyList);
            vbCompleteProjectListMenu.setAlignment(Pos.BASELINE_CENTER);
        }

    }

    /**
     * Creates Projects Button
     * @param projectInfo - projects info container
     * @param newProject - project button info
     * @param deletingEdition - decides whether it will be deleted or not
     * @return
     */
    private VBox create_Buttons(final Projects_XML projectInfo, Projects_Info newProject, boolean deletingEdition) {

        VBox menuBtnContainer = new VBox();

        // Title & Status
        Label title = new Label(newProject.getProjectName());
        title.getStyleClass().add("title_main");

        // Purpose
        Label purpose = new Label(newProject.getProjectPurpose());
        //TODO WHY DOES THIS COLLAPSE WHEN THERE ARE TO MANY PROJECTS
        purpose.setWrapText(true);
        purpose.getStyleClass().add("paragraph");

        Label status = new Label(newProject.getProjectStatus());
        status.getStyleClass().add("subText");
        status.setMinWidth(0);
        status.setMaxHeight(Double.MAX_VALUE);

        Button extendProjectBtn;
        // Changes button presentation and action
        if(!deletingEdition) {
            extendProjectBtn = new Button(sHome_btnText);
            extendProjectBtn.setId(newProject.getProjectID());
            extendProjectBtn.getStyleClass().add("actionBtn");

            extendProjectBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    pref.put(Constants_Prefs.PROJECT_SELECTED_SelectedFromMainScreen, newProject.getProjectName());
                    Common_ControllerMethods screen = new Common_ControllerMethods();
                    screen.changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_ProjectView, e, WINDOW_TITLE_ProjectView, bpAll, false);
                }
            });
        }else {
            extendProjectBtn = new Button(sHome_btnDeleteText);
            extendProjectBtn.getStyleClass().add("deleteBtn");

            extendProjectBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    projectInfo.deleteProject(projectInfo.getProjectById(newProject.getProjectID()));
                    create_Screen(projectInfo, true);
                }
            });
        }

        AnchorPane toolBar = new AnchorPane();
        toolBar.getChildren().addAll(status, extendProjectBtn);
        AnchorPane.setLeftAnchor(status, 0.0);
        AnchorPane.setRightAnchor(extendProjectBtn, 0.0);

        menuBtnContainer.getChildren().addAll(title, purpose, toolBar);
        VBox.setMargin(title, new Insets(10,10,10,10));
        VBox.setMargin(purpose, new Insets(0,10,0,10));
        VBox.setMargin(toolBar, new Insets(10,10,10,10));
        menuBtnContainer.getStyleClass().add("card");
        return menuBtnContainer;

    }
}
