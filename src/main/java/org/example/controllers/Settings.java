package org.example.controllers;

import org.example.assets.Project_CompilePaths;
import org.example.assets.Constants_Prefs;
import org.example.controllers_managers.Settings_Manager;
import org.example.data_manager.project.project_info_manager.Project_Values;
import org.example.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Settings extends Common_ControllerMethods implements Project_CompilePaths {

    @FXML
    BorderPane bpAll;
    @FXML
    HBox hbDevices;
    @FXML
    HBox hbTech;
    @FXML
    TextField tfProductsAdding;
    @FXML
    TextField tfTechAdding;
    @FXML
    TextField tfTodoAdding;
    @FXML
    VBox vbTodoStart;
    @FXML
    VBox vbTodoPlan;
    @FXML
    VBox vbTodoWork;
    @FXML
    VBox vbTodoFix;
    @FXML
    VBox vbTodoFinish;
    @FXML
    TabPane tpTodoList;
    @FXML
    Button btnTodoListEdit;

    private Settings_Manager manager;

    public void initialize(){
        set_ScreenSize(bpAll);

        manager = new Settings_Manager(btnTodoListEdit);
        manager.create_Sets(hbDevices, hbTech);
        manager.create_TodoList(btnTodoListEdit, tpTodoList, vbTodoStart, vbTodoPlan, vbTodoWork, vbTodoFix, vbTodoFinish);
    }

    @FXML
    public void btn_addTodoItem(ActionEvent e){
        String task = tfTodoAdding.getText();
        if(!task.equals("") && !task.isEmpty() && !manager.check_IfTaskIsInTodoList(task)){
            manager.add_TaskTodoList(task);
            tfTodoAdding.setText("");
        }
    }

    @FXML
    public void btn_ShowUserInfo(ActionEvent e){
        presentUserInformation("Global variables will be added as defaults when creating a new project.");
    }

    @FXML
    public void btn_RestDefaults(ActionEvent e){
        manager.reset_DefaultValues();
        Stage stage = (Stage) ((Button)e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_HardReset(ActionEvent e){
        // ass user if they are sure they want to delete everything
        pref.putBoolean(Constants_Prefs.PROJECT_FirstTime, false);

        // TODO DELETE ALL FILES

        Stage stage = (Stage) ((Button)e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_AddValuesToTechSet(ActionEvent e){
        String tfValue = tfTechAdding.getText();
        if(!tfValue.equals("") && !tfValue.isEmpty() && !manager.check_IfValueIsInSet(Project_Values.DEVICES, tfValue)){
            manager.add_ValueToSet(Project_Values.TECHNOLOGIES, tfValue);
            tfTechAdding.setText("");
        }
    }

    @FXML
    public void btn_ChangeScreen(ActionEvent e){
        manager.change_CurrentTodoListView();
    }

    @FXML
    public void btn_AddValueToDevicesSet(ActionEvent e){
        String tfValue = tfProductsAdding.getText();
        if(!tfValue.equals("") && !tfValue.isEmpty() && !manager.check_IfValueIsInSet(Project_Values.DEVICES, tfValue)){
            manager.add_ValueToSet(Project_Values.DEVICES, tfValue);
            tfProductsAdding.setText("");
        }
    }

    @FXML
    public void btn_returnToHomeScreen(ActionEvent e){
        changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_Main, e, WINDOW_TITLE_Main, bpAll, false);
    }
}
