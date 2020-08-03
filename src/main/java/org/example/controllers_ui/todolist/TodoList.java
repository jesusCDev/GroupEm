package org.example.controllers_ui.todolist;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TodoList {

    private VBox vbContainer;
    public ArrayList<TodoList_Task> todoList;
    int TASK_TYPE_NORMAL = 1;
    int TASK_TYPE_DELETE = -1;

    public String[] get_CurrentTodoListValues(){
        String[] currentTodoList_Values = new String[todoList.size()];
        for(int i = 0; i < todoList.size(); i++){
            currentTodoList_Values[i] = todoList.get(i).getTaskName();
        }
        return currentTodoList_Values;
    }

    public void set_Container(VBox vbContainer){
        this.vbContainer = vbContainer;
    }

    public void set_TodoList(ArrayList<TodoList_Task> todoList){
        this.todoList = todoList;
    }

    private TodoList_Value valueContainer;

    public void create_TodoListView(int type){
        vbContainer.getChildren().clear();

        valueContainer = new TodoList_Value();

        for(TodoList_Task task: todoList) {
            vbContainer.getChildren().add(create_TaskView(type, task));
        }
    }

    private VBox create_TaskView(int type, TodoList_Task task){
        if(type == TASK_TYPE_NORMAL){
            return addFunctionality_Normal(create_TaskContainer(type, task), task);
        }else{
            return addFunctionality_Delete(create_TaskContainer(type, task), task);
        }
    }

    private VBox create_TaskContainer(int type, TodoList_Task task){
        HBox hbContainer = new HBox();
        hbContainer.setAlignment(Pos.CENTER_LEFT);

        Label todoList_Item = new Label();
        todoList_Item.setPadding(new Insets(0, 0, 0, 10));

        if(type == TASK_TYPE_NORMAL){
            todoList_Item.setText("=");
        }else if(type == TASK_TYPE_DELETE){
            todoList_Item.setText("x");
        }

        todoList_Item.getStyleClass().add("paragraph");
        hbContainer.getStyleClass().add("todoList_task");
        hbContainer.getChildren().add(todoList_Item);

        hbContainer.getChildren().add(task.getTodoList_View());
        hbContainer.setPrefHeight(Double.MIN_VALUE);

        VBox vbListValueContainer = new VBox();
        vbListValueContainer.getChildren().add(hbContainer);

        vbListValueContainer.getStyleClass().add("todoList_task");
        vbListValueContainer.setFillWidth(true);
        vbListValueContainer.setPrefHeight(Double.MIN_VALUE);
        return vbListValueContainer;
    }

    private VBox addFunctionality_Delete(VBox vbListValueContainer, TodoList_Task task){

        vbListValueContainer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                valueContainer.set_ClickedOnContainer(vbListValueContainer);
                valueContainer.setTask(task);
                delete_Task_FromView();
            }
        });

        vbListValueContainer.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vbListValueContainer.getStyleClass().add("todoList_taskHover");
            }
        });
        vbListValueContainer.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vbListValueContainer.getStyleClass().remove("todoList_taskHover");
            }
        });

        return vbListValueContainer;
    }

    private VBox addFunctionality_Normal(VBox vbListValueContainer, TodoList_Task task){
        vbListValueContainer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vbListValueContainer.getStyleClass().add("todoList_taskSelected");
                valueContainer.set_ClickedOnContainer(vbListValueContainer);
                valueContainer.setTask(task);
            }
        });
        vbListValueContainer.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vbListValueContainer.getStyleClass().remove("todoList_taskSelected");
            }
        });

        vbListValueContainer.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = vbListValueContainer.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(task.getTaskName());
                db.setContent(content);
                event.consume();
            }
        });

        vbListValueContainer.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                move_Task_FromView(vbContainer, vbContainer.getChildren().indexOf(vbListValueContainer));
                event.consume();
            }
        });

        vbListValueContainer.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.consume();
            }
        });

        vbListValueContainer.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.setDropCompleted(true);
                event.consume();
            }
        });

        vbListValueContainer.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                vbListValueContainer.getStyleClass().remove("todoList_taskSelected");
                event.consume();
            }
        });
        return vbListValueContainer;
    }

    private void delete_Task_FromView(){
        vbContainer.getChildren().remove(valueContainer.get_ClickedOnContainer());
        delete_Task_FromList();
    }

    private void delete_Task_FromList(){
        todoList.remove(valueContainer.getTask());
    }

    private void move_Task_FromView(VBox vbContainer, int index){
        vbContainer.getChildren().remove(valueContainer.get_ClickedOnContainer());
        vbContainer.getChildren().add(index, valueContainer.get_ClickedOnContainer());
        move_Task_FromArray(index);
    }

    private void move_Task_FromArray(int index){
        todoList.remove(valueContainer.getTask());
        todoList.add(index, valueContainer.getTask());
    }

    public void add_Task(int type, TodoList_Task newTask){
        todoList.add(0, newTask);
        vbContainer.getChildren().add(0, create_TaskView(type, newTask));
    }
}
