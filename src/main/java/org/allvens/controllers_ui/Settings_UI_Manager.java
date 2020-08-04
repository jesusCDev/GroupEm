package org.allvens.controllers_ui;

import org.allvens.controllers_ui.tags.TagSet;
import org.allvens.controllers_ui.todolist.TodoList_Settings;
import org.allvens.data_manager.project.project_info_manager.Project_Values;
import org.allvens.data_manager.settings.Settings_XML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class Settings_UI_Manager {

    private TagSet devicesSet;
    private TagSet techSets;

    public TagSet get_TechSet(){
        return techSets;
    }
    public TagSet get_DeviceSet(){
        return devicesSet;
    }

    public TagSet get_CurrentSet(String setTitle){
        if(Project_Values.DEVICES.equalsIgnoreCase(setTitle)){
            return devicesSet;
        }else{
            return techSets;
        }
    }

    public void update_CurrentValuesInSet(String setTitle, Settings_XML document){
        TagSet currentTodoList = get_CurrentSet(setTitle);

        document.getDocument().set_SetValue(setTitle, currentTodoList.get_TagValues());
        document.updateXMLFile();
    }

    public void create_setTables(Settings_XML document, HBox hbDevice, HBox hbTech) {
        devicesSet = new TagSet(hbDevice, document.getDocument().getDevices());
        devicesSet.set_Type(devicesSet.TYPE_SET_EDIT);
        hbDevice.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                update_CurrentValuesInSet(Project_Values.DEVICES, document);
            }
        });
        devicesSet.create_TagSet();

        techSets = new TagSet(hbTech, document.getDocument().getTech());
        techSets.set_Type(devicesSet.TYPE_SET_EDIT);
        hbTech.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                update_CurrentValuesInSet(Project_Values.TECHNOLOGIES, document);
            }
        });
        techSets.create_TagSet();
    }

    public void change_EditTodoListBtn(int type, Button btn) {
        int TASK_TYPE_NORMAL = 1;
        int TASK_TYPE_DELETE = -1;

        if (type == TASK_TYPE_NORMAL){
            btn.setText("Delete");
        }else if(type == TASK_TYPE_DELETE){
            btn.setText("Cancel");
        }
    }

    private int current_TodoList;
    public TodoList_Settings todoList_Start;
    public TodoList_Settings todoList_Plan;
    public TodoList_Settings todoList_Work;
    public TodoList_Settings todoList_Fix;
    public TodoList_Settings todoList_Finish;

    public void update_CurrentTodoListValuesInDocument(Settings_XML document){
        TodoList_Settings currentTodoList = get_CurrentTodoList();
        document.getDocument().set_TodoListValues(current_TodoList, currentTodoList.get_TodoList());
        document.updateXMLFile();
    }

    public void create_TodoList(Button btn_TodoListEdit, TabPane tpTodoList, VBox vbStart, VBox vbPlan, VBox vbWork, VBox vbFix, VBox vbFinish, Settings_XML document) {
        todoList_Start = new TodoList_Settings(new ArrayList<>(Arrays.asList(document.getDocument().getTodoList_Starting())), vbStart);
        todoList_Plan = new TodoList_Settings(new ArrayList<>(Arrays.asList(document.getDocument().getTodoList_Planning())), vbPlan);
        todoList_Work = new TodoList_Settings(new ArrayList<>(Arrays.asList(document.getDocument().getTodoList_Working())), vbWork);
        todoList_Fix = new TodoList_Settings(new ArrayList<>(Arrays.asList(document.getDocument().getTodoList_Fixing())), vbFix);
        todoList_Finish = new TodoList_Settings(new ArrayList<>(Arrays.asList(document.getDocument().getTodoList_Finished())), vbFinish);

        vbStart.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                update_CurrentTodoListValuesInDocument(document);
            }
        });
        vbPlan.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                update_CurrentTodoListValuesInDocument(document);
            }
        });
        vbWork.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                update_CurrentTodoListValuesInDocument(document);
            }
        });
        vbFix.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                update_CurrentTodoListValuesInDocument(document);
            }
        });
        vbFinish.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                update_CurrentTodoListValuesInDocument(document);
            }
        });

        tpTodoList.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                switch (t1.getText()){
                    case Project_Values.sProjectView_TodoListTitleStart:
                        current_TodoList = 0;
                        break;
                    case Project_Values.sProjectView_TodoListTitlePlan:
                        current_TodoList = 1;
                        break;
                    case Project_Values.sProjectView_TodoListTitleWork:
                        current_TodoList = 2;
                        break;
                    case Project_Values.sProjectView_TodoListTitleFix:
                        current_TodoList = 3;
                        break;
                    default:
                        current_TodoList = 4;
                        break;
                }
                change_EditTodoListBtn(1, btn_TodoListEdit);
                get_CurrentTodoList().setCurrentType(1);
                get_CurrentTodoList().change_TodoListFunctionality(1);
            }
        }
        );
    }

    public TodoList_Settings get_CurrentTodoList(){
        TodoList_Settings list;
        switch (current_TodoList){
            case 0:
                list = todoList_Start;
                break;
            case 1:
                list = todoList_Plan;
                break;
            case 2:
                list = todoList_Work;
                break;
            case 3:
                list = todoList_Fix;
                break;
            default:
                list = todoList_Finish;
                break;
        }
        return list;
    }
}
