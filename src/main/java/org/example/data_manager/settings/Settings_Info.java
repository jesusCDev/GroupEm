package org.example.data_manager.settings;

import org.example.data_manager.project.project_info_manager.Project_Values;

public class Settings_Info {

    private String[] devices;
    private String[] tech;

    private String[] todolist_Starting;
    private String[] todolist_Planning;
    private String[] todolist_Working;
    private String[] todolist_Fixing;
    private String[] todolist_Finished;

    public String[] getDevices(){
        return devices;
    }

    public String[] getTech(){
        return tech;
    }

    public void setDevices(String[] devices){
        this.devices = devices;
    }

    public void setTechs(String[] tech){
        this.tech = tech;
    }

    public void set_TodoListValues(int developmentKey, String[] elements){
        switch (developmentKey){
            case 0:
                setTodoList_Starting(elements);
                break;
            case 1:
                setTodoList_Planning(elements);
                break;
            case 2:
                setTodoList_Working(elements);
                break;
            case 3:
                setTodoList_Fixing(elements);
                break;
            default:
                setTodoList_Finished(elements);
                break;
        }
    }

    public String[] getTodoList_Starting(){
        return todolist_Starting;
    }
    public String[] getTodoList_Planning(){
        return todolist_Planning;
    }
    public String[] getTodoList_Working(){
        return todolist_Working;
    }
    public String[] getTodoList_Fixing(){
        return todolist_Fixing;
    }
    public String[] getTodoList_Finished(){
        return todolist_Finished;
    }

    public void setTodoList_Starting(String[] todolist_Starting){
        this.todolist_Starting = todolist_Starting;
    }
    public void setTodoList_Planning(String[] todolist_Planning){
        this.todolist_Planning = todolist_Planning;
    }
    public void setTodoList_Working(String[] todolist_Working){
        this.todolist_Working = todolist_Working;
    }
    public void setTodoList_Fixing(String[] todolist_Fixing){
        this.todolist_Fixing = todolist_Fixing;
    }
    public void setTodoList_Finished(String[] todolist_Finished){
        this.todolist_Finished = todolist_Finished;
    }

    public void set_SetValue(String setTitle, String[] tagValues) {
        if(Project_Values.DEVICES.equalsIgnoreCase(setTitle)){
            setDevices(tagValues);
        }else{
            setTechs(tagValues);
        }
    }
}
