package org.example.controllers_ui;

import org.example.assets.Constants_Prefs;
import org.example.controllers_ui.tags.TagSet;
import org.example.controllers_ui.todolist.TodoList_Workspace;
import org.example.data_manager.project.projec_development_info_button.DocumentDevelopment_Button_Info;
import org.example.data_manager.project.project_info_manager.Project_Values;
import org.example.data_manager.project.project_info_manager.Project_XML;
import org.example.default_methods.Common_ControllerMethods;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import static org.example.assets.Project_CompilePaths.FILE_FXML_DocumentViewer;
import static org.example.assets.Project_ConstantStrings.WINDOW_TITLE_DocumentViewer;

public class Project_Workspace_UI_Manager {

    private BorderPane bpAll;
    private HBox hbHashTags;
    private VBox vbProjectDocumentationDevelopmentBtns;

    public Project_Workspace_UI_Manager(BorderPane bpAll, HBox hbHashTags, VBox vbProjectDocumentationDevelopmentBtns){
        this.bpAll = bpAll;
        this.hbHashTags = hbHashTags;
        this.vbProjectDocumentationDevelopmentBtns = vbProjectDocumentationDevelopmentBtns;
    }
    /****************************************
     /**** DEVELOPMENT - TO DO-LIST METHODS
     ***************************************/

    public TodoList_Workspace get_CurrentTodoList(){
        TodoList_Workspace list;
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

    public void change_EditTodoListBtn(int type, Button btn) {
        int TASK_TYPE_NORMAL = 1;
        int TASK_TYPE_DELETE = -1;

        if (type == TASK_TYPE_NORMAL){
            btn.setText("Delete");
        }else if(type == TASK_TYPE_DELETE){
            btn.setText("Cancel");
        }
    }

    private int current_TodoList = 1;
    private TodoList_Workspace todoList_Start;
    private TodoList_Workspace todoList_Plan;
    private TodoList_Workspace todoList_Work;
    private TodoList_Workspace todoList_Fix;
    private TodoList_Workspace todoList_Finish;

    public void update_CurrentTodoListValuesInDocument(Project_XML document){
        TodoList_Workspace currentTodoList = get_CurrentTodoList();
        document.getProject().setProjectDevelopment_Elements(current_TodoList, currentTodoList.get_TodoList());
        document.getProject().setProjectDevelopment_Values(current_TodoList, currentTodoList.get_TodoListAndValues());
        document.updateXMLFile();
    }

    public void create_TodoList(Button btnTodoListEdit, TabPane tpTodoList, VBox vbStart, VBox vbPlan, VBox vbWork, VBox vbFix, VBox vbFinish, Project_XML document){

        todoList_Start = new TodoList_Workspace(document.getProject().getProjectDevelopment_Starting(), vbStart);
        todoList_Plan = new TodoList_Workspace(document.getProject().getProjectDevelopment_Planning(), vbPlan);
        todoList_Work = new TodoList_Workspace(document.getProject().getProjectDevelopment_Working(), vbWork);
        todoList_Fix = new TodoList_Workspace(document.getProject().getProjectDevelopment_Fixing(), vbFix);
        todoList_Finish = new TodoList_Workspace(document.getProject().getProjectDevelopment_Finishing(), vbFinish);


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

        tpTodoList.getSelectionModel().select(document.getProject().get_ProjectDevelopmentPos());

        current_TodoList = document.getProject().get_ProjectDevelopmentPos();

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

                document.getProject().set_ProjectDevelopmentPos(current_TodoList);
                document.updateXMLFile();

                change_EditTodoListBtn(1, btnTodoListEdit);
                get_CurrentTodoList().setCurrentType(1);
                get_CurrentTodoList().change_TodoListFunctionality(1);
            }
            }
        );
    }

    /****************************************
     /**** DEVELOPMENT - HASH-TAG METHODS
     ****************************************/

    public void create_HashTagBtns(ArrayList<DocumentDevelopment_Button_Info> hashTagBtns,
                                   ArrayList<String> hashTags, String HashTagSelected) {

        hbHashTags.getChildren().clear();
        ArrayList<Button> btnLIst = new ArrayList<>();
        Button hashtagBtn;

        for(int hashtagInList = 0; hashtagInList < hashTags.size(); hashtagInList++) {
            hashtagBtn = new Button("#" + hashTags.get(hashtagInList));
            hashtagBtn.setMinWidth(Double.MIN_VALUE);

            if(hashTags.get(hashtagInList).equals(HashTagSelected)){
                hashtagBtn.getStyleClass().add("hashTag_Clicked");
            }else {
                hashtagBtn.getStyleClass().add("hashTag_unClicked");
            }
            hashtagBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    create_HashTagBtns(hashTagBtns, hashTags, ((Button)event.getSource()).getText().replaceFirst("#", ""));
                    recreate_DevDocumentation(hashTagBtns, ((Button)event.getSource()).getText().replaceFirst("#", ""));
                }
            });

            btnLIst.add(hashtagBtn);
        }
        hbHashTags.getChildren().addAll(btnLIst);
    }

    public void recreate_DevDocumentation(ArrayList<DocumentDevelopment_Button_Info> hashTagBtns, String hashtag) {

        vbProjectDocumentationDevelopmentBtns.getChildren().clear();

        ArrayList<DocumentDevelopment_Button_Info> newList = new ArrayList<>();
        boolean btnAdded;

        // TODO MAKE SURE TO GET RID OF THE ALL HASHTAG
        if(hashtag.equals("All")) {
            newList = hashTagBtns;
        }else {
            for(int i = 0; i < hashTagBtns.size(); i++) {
                btnAdded = false;
                for(int j = 0; j < hashTagBtns.get(i).getDocumentHashtags().length; j++) {
                    if(hashTagBtns.get(i).getDocumentHashtags()[j].toLowerCase().equals(hashtag.toLowerCase())) {
                        btnAdded = true;
                    }
                }
                if(btnAdded == true) {
                    newList.add(hashTagBtns.get(i));
                }
            }

        }
        vbProjectDocumentationDevelopmentBtns.getChildren().addAll(createAll_DevDocumentContainers(newList));
    }


    private ArrayList<VBox> createAll_DevDocumentContainers(ArrayList<DocumentDevelopment_Button_Info> hashTagBtns){
        ArrayList<VBox> btnList = new ArrayList<>();
        for(int i = 0; i < hashTagBtns.size(); i++) {
            btnList.add(create_DevDocumentContainer(hashTagBtns.get(i)));
        }
        return btnList;
    }

    private VBox create_DevDocumentContainer(DocumentDevelopment_Button_Info btn) {
        VBox container = new VBox();

        Label title = new Label(btn.getDocumentName());
        title.getStyleClass().add("title_sub");
        Label summary = new Label(btn.getDocumentSummary());
        summary.setWrapText(true);
        summary.getStyleClass().add("paragraph");
        BorderPane bpDatesAndBtn = new BorderPane();

        Label dateAdded = new Label(btn.getDocumentDateAdded());
        dateAdded.getStyleClass().add("subText");
        Label dateEdited = new Label(btn.getDocumentDateEdited());
        dateEdited.getStyleClass().add("subText");

        Button btnEdit = new Button("View");
        btnEdit.getStyleClass().add("addBtn");
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Preferences.userRoot().put(Constants_Prefs.FILE_EDITING_FileBeingEdited, btn.getDocumentID());
                new Common_ControllerMethods().changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, FILE_FXML_DocumentViewer, event, WINDOW_TITLE_DocumentViewer, bpAll, false);
            }
        });

        bpDatesAndBtn.setLeft(dateAdded);
        bpDatesAndBtn.setCenter(dateEdited);
        bpDatesAndBtn.setRight(btnEdit);

        VBox.setMargin(title, new Insets(10,10,10,10));
        VBox.setMargin(summary, new Insets(10,10,10,10));
        VBox.setMargin(bpDatesAndBtn, new Insets(10,10,10,10));

        container.getChildren().addAll(title, summary, bpDatesAndBtn);
        container.getStyleClass().add("card");

        return container;
    }

    /****************************************
     /**** TABLE METHODS
     ****************************************/
    /**
     * Sets values for tables
     * @param values
     */
    public void create_table(String[] values, HBox container) {
        TagSet tagSet = new TagSet(container, values);
        tagSet.set_Type(tagSet.TYPE_SET_NORMAL);
        tagSet.create_TagSet();
    }
}
