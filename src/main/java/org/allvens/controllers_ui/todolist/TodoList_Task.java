package org.allvens.controllers_ui.todolist;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class TodoList_Task {
    private String taskName;
    private Node todoList_View;

    TodoList_Task(String taskName, Node todoList_View){
        this.taskName = taskName;
        this.todoList_View = todoList_View;
    }

    public Node getTodoList_View() {
        return todoList_View;
    }

    public void setTodoList_View(Node todoList_View) {
        this.todoList_View = todoList_View;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean get_ValueFromCheckbox() {
        return ((CheckBox)((VBox)todoList_View).getChildren().get(0)).isSelected();
    }
}
