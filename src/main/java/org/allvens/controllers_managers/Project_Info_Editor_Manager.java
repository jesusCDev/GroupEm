package org.allvens.controllers_managers;

import org.allvens.assets.Constants_Prefs;
import org.allvens.assets.Project_ConstantStrings;
import org.allvens.controllers_ui.Project_Info_Editor_UI_Manager;
import org.allvens.data_manager.project.projec_development_info_button.DocumentDevelopment_Button_XML;
import org.allvens.data_manager.project.project_info_manager.Project_Info;
import org.allvens.data_manager.project.project_info_manager.Project_XML;
import org.allvens.data_manager.projects.Projects_Info;
import org.allvens.data_manager.projects.Projects_XML;
import org.allvens.data_manager.settings.Settings_XML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class Project_Info_Editor_Manager {

    private Project_Info_Editor_UI_Manager ui_manager;
    private Project_XML projectBeingEdited_Document;
    private Projects_XML projects_Document = new Projects_XML();

    private final int MAX_CHARS = 180;
    private boolean fileBeingEdited;
    private String rbCurrentStatus = Project_ConstantStrings.kw_StatusOngoing;

    public void setUp_UIManager() {
        ui_manager = new Project_Info_Editor_UI_Manager();
    }

    public void setUp_Toggles(RadioButton rbStatusOngoing, RadioButton rbStatusReturnTo, RadioButton rbStatusComplete){
        ToggleGroup group = new ToggleGroup();
        rbStatusOngoing.setToggleGroup(group);
        rbStatusReturnTo.setToggleGroup(group);
        rbStatusComplete.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if(group.getSelectedToggle() == rbStatusOngoing) {
                    rbCurrentStatus = Project_ConstantStrings.kw_StatusOngoing;
                }else if(group.getSelectedToggle() == rbStatusReturnTo) {
                    rbCurrentStatus = Project_ConstantStrings.kw_StatusReturnTo;
                }else {
                    rbCurrentStatus = Project_ConstantStrings.kw_StatusComplete;
                }
            }
        });
    }

    public void create_CheckBoxes(HBox hbTech, HBox hbDevices){
        // Crete CheckBox
        Settings_XML settings = new Settings_XML(false);
        ui_manager.setGpTech(ui_manager.createCheckBox(hbTech, settings.getDocument().getTech(), "Tech"));
        ui_manager.setGpDevices(ui_manager.createCheckBox(hbDevices, settings.getDocument().getDevices(), "Product"));
    }

    public void set_TextFieldLimit(TextArea tfProjectPurpose){
        tfProjectPurpose.setTextFormatter(new TextFormatter<Object>(change ->
                change.getControlNewText().length() <= MAX_CHARS ? change : null));

    }

    public void set_DocumentValues(Button btnSubmitInfo, Label lbProjectTitle, TextField tfProjectName, TextField tfProjectTitle,
                                   TextArea tfProjectPurpose, TextArea tfProjectSummary,
                                   RadioButton rbStatusOngoing, RadioButton rbStatusReturnTo, RadioButton rbStatusComplete){

        fileBeingEdited = Preferences.userRoot().getBoolean(Constants_Prefs.FILE_EDITING_EditingSelectedFile, false);

        if(fileBeingEdited) {

            projectBeingEdited_Document = new Project_XML(false);

            btnSubmitInfo.setText("Update Values");
            lbProjectTitle.setText(projectBeingEdited_Document.getProject().getProjectName());

            tfProjectName.setDisable(true);
            tfProjectName.setText(projectBeingEdited_Document.getProject().getProjectName());
            tfProjectTitle.setText(projectBeingEdited_Document.getProject().getProjectTitle());

            tfProjectPurpose.setText(projectBeingEdited_Document.getProject().getProjectPurpose());
            tfProjectSummary.setText(projectBeingEdited_Document.getProject().getProjectSummary());

            //update status
            switch(projectBeingEdited_Document.getProject().getProjectStatus()) {
                case Project_ConstantStrings.kw_StatusOngoing:
                    rbStatusOngoing.setSelected(true);
                    rbCurrentStatus = Project_ConstantStrings.kw_StatusOngoing;
                    break;
                case Project_ConstantStrings.kw_StatusReturnTo:
                    rbStatusReturnTo.setSelected(true);
                    rbCurrentStatus = Project_ConstantStrings.kw_StatusReturnTo;
                    break;
                default:
                    rbStatusComplete.setSelected(true);
                    rbCurrentStatus = Project_ConstantStrings.kw_StatusComplete;
                    break;
            }

            setCheckedCheckBox(ui_manager.getGpDevices(), projectBeingEdited_Document.getProject().getProjectDevices());
            setCheckedCheckBox(ui_manager.getGpTech(), projectBeingEdited_Document.getProject().getProjectTechnologies());
        }
    }

    private String[] setCheckBoxValues(ArrayList<String> list){
        String[] checkedCheckBoxes = new String[list.size()];
        int iter = 0;
        for(String item: list){
            checkedCheckBoxes[iter] = item;
            iter++;
        }
        return checkedCheckBoxes;
    }

    private void setCheckedCheckBox(GridPane gp, String[] list){
        CheckBox cb;
        ArrayList<String> cbList = new ArrayList<>();

        for(int i = 0; i < gp.getChildren().size(); i++){
            cb = ((CheckBox)gp.getChildren().get(i));
            for(int j = 0; j < list.length; j++) {
                if(cb.getText().equals(list[j])) {
                    cb.setSelected(true);
                    if(cb.isSelected()){
                        cbList.add(list[j]);
                    }
                    break;
                }
            }
        }
    }

    public void show_UserExistingProjects() {
        Projects_Info project;
        StringBuilder sb = new StringBuilder();
        sb.append("Existing Projects: " + "\n");

        for(int i = 0; i < projects_Document.getProjects().size(); i++) {
            project = projects_Document.getProjects().get(i);
            sb.append("     -" + project.getProjectName());
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public boolean get_FileBeingEdited() {
        return fileBeingEdited;
    }

    public boolean check_IfProjectAlreadyExist(String projectName){
        for(int i = 0; i < projects_Document.getProjects().size(); i++) {
            if(projects_Document.getProjects().get(i).getProjectName().equals(projectName)) {
                return true;
            }
        }
        return false;
    }

    public boolean edit_Project(String projectName, String projectTitle, String projectPurpose, String projectStatus, String projectSummary){
        if(fileBeingEdited){
            for(int i = 0; i < projects_Document.getProjects().size(); i++){
                if(projects_Document.getProjects().get(i).getProjectName().equals(projectName)){
                    projects_Document.getProjects().get(i).setProjectPurpose(projectPurpose);
                    projects_Document.getProjects().get(i).setProjectStatus(projectStatus);
                }
            }
            projects_Document.updateXMLFile();

            projectBeingEdited_Document.getProject().setProjectName(projectName);
            projectBeingEdited_Document.getProject().setProjectTitle(projectTitle);
            projectBeingEdited_Document.getProject().setProjectStatus(projectStatus);
            projectBeingEdited_Document.getProject().setProjectPurpose(projectPurpose);
            projectBeingEdited_Document.getProject().setProjectSummary(projectSummary);

            projectBeingEdited_Document.getProject().setProjectTechnologies(setCheckBoxValues(ui_manager.get_TechnologiessList()));
            projectBeingEdited_Document.getProject().setProjectDevices(setCheckBoxValues(ui_manager.get_DevicesList()));

            projectBeingEdited_Document.updateXMLFile();
            return true;
        }else{
            return false;
        }
    }

    public Project_Info create_NewProject(String projectName, String projectTitle, String projectStatus,
                                  String projectPurpose, String projectSummary){

        Preferences.userRoot().put(Constants_Prefs.PROJECT_SELECTED_SelectedFromMainScreen, projectName);

        Project_XML newProject = new Project_XML(true);
        Project_Info projectInfo = new Project_Info(projectTitle, projectName, projectStatus,
                projectPurpose, projectSummary, setCheckBoxValues(ui_manager.get_TechnologiessList()), setCheckBoxValues(ui_manager.get_DevicesList()));
        DocumentDevelopment_Button_XML document = new DocumentDevelopment_Button_XML(true);

        newProject.createProject(projectInfo);
        projects_Document.createProject(projectInfo);
        document.createDocument();

        return projectInfo;
    }

    public String getRbCurrentStatus() {
        return rbCurrentStatus;
    }
}
