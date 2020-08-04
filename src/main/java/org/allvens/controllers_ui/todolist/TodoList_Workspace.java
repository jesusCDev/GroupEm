package org.allvens.controllers_ui.todolist;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TodoList_Workspace extends TodoList{

    private int currentType = 1;
    private VBox vbContainer;

    public TodoList_Workspace(String[][] tasksAndValues, VBox vbContainer) {
        this.vbContainer = vbContainer;
        set_Container(vbContainer);
        set_TodoList(create_Task(tasksAndValues));
        create_TodoListView(TASK_TYPE_NORMAL);
    }

    public void change_TodoListFunctionality(int type){
        create_TodoListView(type);
    }

    private ArrayList<TodoList_Task> create_Task(String[][] taskAndValues){
        ArrayList<TodoList_Task> todolist = new ArrayList<>();
        for(String[] task: taskAndValues){
            todolist.add(new TodoList_Task(task[0], create_Node(task)));
        }
        return todolist;
    }

    public void add_TaskToSettingsList(String task){
        add_Task(currentType, new TodoList_Task(task, create_Node(new String[]{task, Boolean.toString(false)})));
    }

    private VBox create_Node(String[] task){
        VBox vbContainer = new VBox();

        CheckBox cb = new CheckBox();
        cb.getStyleClass().add("todoList_Value");
        cb.setText(task[0]);
        cb.setSelected(Boolean.parseBoolean(task[1]));

        vbContainer.getChildren().add(cb);
        vbContainer.setFillWidth(true);
        vbContainer.setPrefHeight(Double.MIN_VALUE);

        return vbContainer;
    }

    public int getCurrentType() {
        return currentType;
    }

    public void setCurrentType(int currentType) {
        this.currentType = currentType;
    }

    public String[][] get_TodoListAndValues() {
        String[][] todoListValues = new String[todoList.size()][2];
        for(int i = 0; i < todoList.size(); i++){
            todoListValues[i][0] = todoList.get(i).getTaskName();
            todoListValues[i][1] = Boolean.toString(todoList.get(i).get_ValueFromCheckbox());
        }
        return todoListValues;
    }

    public String[] get_TodoList() {
        String[] todoListValues = new String[todoList.size()];
        for(int i = 0; i < todoList.size(); i++){
            todoListValues[i] = todoList.get(i).getTaskName();
        }
        return todoListValues;
    }

    public VBox getVbContainer() {
        return vbContainer;
    }

    public void setVbContainer(VBox vbContainer) {
        this.vbContainer = vbContainer;
    }
}
