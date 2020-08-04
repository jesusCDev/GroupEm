package org.allvens.controllers_managers;

import org.allvens.controllers_ui.Settings_UI_Manager;
import org.allvens.controllers_ui.tags.Tag_Value;
import org.allvens.data_manager.project.project_info_manager.Project_Values;
import org.allvens.data_manager.settings.Settings_XML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Settings_Manager {

    private Settings_XML document;
    private Settings_UI_Manager ui_manager;

    private Button btn_TodoListEdit;

    public Settings_Manager(Button btn_TodoListEdit){
        this.btn_TodoListEdit = btn_TodoListEdit;
        document = new Settings_XML(false);
        ui_manager = new Settings_UI_Manager();
    }

    public void reset_DefaultValues() {
        document.reset_DefaultValues();
    }

    public void create_TodoList(Button btnTodoListEdit, TabPane tpTodoList, VBox vbTodoStart, VBox vbTodoPlan, VBox vbTodoWork, VBox vbTodoFix, VBox vbTodoFinish) {
        ui_manager.create_TodoList(btnTodoListEdit, tpTodoList, vbTodoStart, vbTodoPlan, vbTodoWork, vbTodoFix, vbTodoFinish, document);
    }

    public void create_Sets(HBox hbDevices, HBox hbTech) {
        ui_manager.create_setTables(document, hbDevices, hbTech);
    }

    public void add_ValueToSet(String set, String newTask) {
        String currentSet = Project_Values.TECHNOLOGIES;
        if(set.equalsIgnoreCase(Project_Values.TECHNOLOGIES)) {
            ui_manager.get_TechSet().add_Tag(new Tag_Value(newTask));
        }else{
            currentSet = Project_Values.DEVICES;
            ui_manager.get_DeviceSet().add_Tag(new Tag_Value(newTask));
        }
        ui_manager.update_CurrentValuesInSet(currentSet, document);
    }

    public boolean check_IfTaskIsInTodoList(String task) {
        String[] values = ui_manager.get_CurrentTodoList().get_CurrentTodoListValues();

        for(String value: values){
            if(value.equalsIgnoreCase(task)) return true;
        }
        return false;
    }

    public void add_TaskTodoList(String task){
        if(!check_IfTaskIsInTodoList(task)){
            ui_manager.get_CurrentTodoList().add_TaskToSettingsList(task);
            ui_manager.update_CurrentTodoListValuesInDocument(document);
        }
    }

    public void change_CurrentTodoListView() {
        int currentTodoList_Type = ui_manager.get_CurrentTodoList().getCurrentType();
        ui_manager.change_EditTodoListBtn(currentTodoList_Type * -1, btn_TodoListEdit);
        ui_manager.get_CurrentTodoList().change_TodoListFunctionality(currentTodoList_Type * -1);
        ui_manager.get_CurrentTodoList().setCurrentType(currentTodoList_Type * -1);
    }

    public boolean check_IfValueIsInSet(String selectedSet, String value) {
        for(String setValue: ui_manager.get_CurrentSet(selectedSet).get_TagValues()){
            if(setValue.equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
}
