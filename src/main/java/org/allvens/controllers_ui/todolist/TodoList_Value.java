package org.allvens.controllers_ui.todolist;

import javafx.scene.layout.VBox;

public class TodoList_Value {

    private VBox vbContainer;
    private String value;
    private TodoList_Task task;

    public void set_ClickedOnContainer(VBox vbContainer){
        this.vbContainer = vbContainer;
    }

    public VBox get_ClickedOnContainer(){
        return vbContainer;
    }

    public void set_Value(String value) {
        this.value = value;
    }

    public String get_Value() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TodoList_Task getTask() {
        return task;
    }

    public void setTask(TodoList_Task task) {
        this.task = task;
    }
}
