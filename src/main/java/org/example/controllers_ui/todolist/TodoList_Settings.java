package org.example.controllers_ui.todolist;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TodoList_Settings extends TodoList{

    private int currentType = 1;

    public TodoList_Settings(ArrayList<String> tasks, VBox vbContainer) {
        set_Container(vbContainer);
        set_TodoList(create_Task(tasks));
        create_TodoListView(TASK_TYPE_NORMAL);
    }

    public void change_TodoListFunctionality(int type){
        create_TodoListView(type);
    }

    private ArrayList<TodoList_Task> create_Task(ArrayList<String> tasks){
        ArrayList<TodoList_Task> todolist = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++){
            todolist.add(new TodoList_Task(tasks.get(i), create_Node(tasks.get(i))));
        }
        return todolist;
    }

    public void add_TaskToSettingsList(String task){
        add_Task(currentType, new TodoList_Task(task, create_Node(task)));
    }

    private VBox create_Node(String task){
        VBox vbContainer = new VBox();

        Label lbTask = new Label(task);
        lbTask.getStyleClass().add("paragraph");
        lbTask.getStyleClass().add("todoList_task");
        vbContainer.getChildren().add(lbTask);
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

    public String[] get_TodoList() {
        String[] todoListValues = new String[todoList.size()];
        for(int i = 0; i < todoList.size(); i++){
            todoListValues[i] = todoList.get(i).getTaskName();
        }
        return todoListValues;
    }
}
