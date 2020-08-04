package org.allvens.controllers_managers;

import org.allvens.controllers_ui.Project_Workspace_UI_Manager;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import org.allvens.markupLanguage.MarkUpLanguage_Render;
import org.allvens.data_manager.project.projec_development_info_button.DocumentDevelopment_Button_XML;
import org.allvens.data_manager.project.project_info_manager.Project_XML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Project_Workspace_Manager {

    private Project_Workspace_UI_Manager ui_manager;
    private Project_XML document;

    public Project_Workspace_Manager() {
        document = new Project_XML(false);
    }

    public void setUp_TodoList(Button btnTodoListEdit, TabPane tpTodoList, VBox vbStart, VBox vbPlan, VBox vbWork, VBox vbFix, VBox vbFinish){
        ui_manager.create_TodoList(btnTodoListEdit, tpTodoList, vbStart, vbPlan, vbWork, vbFix, vbFinish, document);
    }

    public void setUp_Workspace_UI_Manager(BorderPane bpAll, HBox hbHashTags, VBox vbProjectDocumentationDevelopmentBtns){
        ui_manager = new Project_Workspace_UI_Manager(bpAll, hbHashTags, vbProjectDocumentationDevelopmentBtns);
    }

    public void setUp_Development_HashTagContainers(){
        DocumentDevelopment_Button_XML fhDocumentDevelopment = new DocumentDevelopment_Button_XML(false);
        Set<String> hashSet = new HashSet<String>();
        hashSet.add("All");

        for(int i = 0; i < fhDocumentDevelopment.getDocuments().size(); i++) {
            for(int j = 0; j < fhDocumentDevelopment.getDocuments().get(i).getDocumentHashtags().length; j++) {
                hashSet.add(fhDocumentDevelopment.getDocuments().get(i).getDocumentHashtags()[j]);
            }
        }

        ArrayList<String> hashTags = new ArrayList<>();
        Iterator<String> it = hashSet.iterator();

        while(it.hasNext()){
            hashTags.add(it.next());
        }

        ui_manager.create_HashTagBtns(fhDocumentDevelopment.getDocuments(),hashTags, "All");
        ui_manager.recreate_DevDocumentation(fhDocumentDevelopment.getDocuments(), "All");
    }

    public void update_ConstantLabels(Label lbCurrentProject, Label lbProjectTitle, Label lbProjectName, Label lbProjectStatus, Label lbProjectPurpose,
                                      VBox vbProjectDevelopment) {
        // SET LABELS
        lbCurrentProject.setText(document.getProject().getProjectName());
        lbProjectTitle.setText(document.getProject().getProjectTitle());
        lbProjectName.setText(document.getProject().getProjectName());
        lbProjectStatus.setText(document.getProject().getProjectStatus());
        lbProjectPurpose.setText(document.getProject().getProjectPurpose());

        // SET SUMMARY
        MarkUpLanguage_Render markupSummary = new MarkUpLanguage_Render(document.getProject().getProjectSummary());
        vbProjectDevelopment.getChildren().addAll(markupSummary.getTextFlowPanes());

        if(vbProjectDevelopment.getChildren().size() == 0){
            vbProjectDevelopment.setPrefHeight(0);
        }
    }

    public void create_TagSets(HBox gpTechnologies, HBox gpDevices){
        // SET TABLES
        ui_manager.create_table(document.getProject().getProjectTechnologies(), gpTechnologies);
        ui_manager.create_table(document.getProject().getProjectDevices(), gpDevices);
    }

    private boolean check_IfTaskIsInTodoList(String task) {
        String[] values = ui_manager.get_CurrentTodoList().get_CurrentTodoListValues();

        for(String value: values){
            if(value.equalsIgnoreCase(task)) return true;
        }
        return false;
    }

    public void add_TaskTodoList(String task, TextField tf){
        if(!check_IfTaskIsInTodoList(task)){
            ui_manager.get_CurrentTodoList().add_TaskToSettingsList(task);
            ui_manager.update_CurrentTodoListValuesInDocument(document);
            tf.setText("");
        }
    }

    public void change_CurrentTodoListView(Button btn_TodoListEdit) {
        int currentTodoList_Type = ui_manager.get_CurrentTodoList().getCurrentType();
        ui_manager.change_EditTodoListBtn(currentTodoList_Type * -1, btn_TodoListEdit);
        ui_manager.get_CurrentTodoList().change_TodoListFunctionality(currentTodoList_Type * -1);
        ui_manager.get_CurrentTodoList().setCurrentType(currentTodoList_Type * -1);
    }
}
