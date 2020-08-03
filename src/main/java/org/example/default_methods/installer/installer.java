package org.example.default_methods.installer;

import org.example.assets.Project_CompilePaths;
import org.example.controllers_ui.ui_feedback.Toast;
import org.example.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;

public class installer extends Common_ControllerMethods {

    @FXML
    ListView<String> lv_FilesAndDirectories;
    @FXML
    TextField tf_FileLocation;
    private Toast toast;

    /**
     * Sets up default values
     */
    public void initialize(){
        lv_FilesAndDirectories.getItems().add(outer_files.DIR_MainFolder);
        lv_FilesAndDirectories.getItems().addAll(outer_files.PROJECT_DIRECTORIES);
        lv_FilesAndDirectories.getItems().addAll(outer_files.PROJECT_FILES);
        lv_FilesAndDirectories.getItems().addAll(outer_files.PROJECT_MultipleFiles);

        toast = new Toast();
    }

    /********** Onscreen Button Actions **********/

    /**
     * Presents user wtih a file explorer they can navigate to select the prefer location to install the app
     * @param e
     */
    @FXML
    public void btn_FileSystem_DirectoryFinder(ActionEvent e){
        // Open File Explorer
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(null);

        // Present Results
        if (result == JFileChooser.APPROVE_OPTION) {
            tf_FileLocation.setText(chooser.getSelectedFile().getAbsolutePath());
        }
        else if (result == JFileChooser.CANCEL_OPTION) {
            toast.showMessage(Project_CompilePaths.TEXT_ERROR_FileChooserCanceled, Project_CompilePaths.WINDOW_TITLE_Error);
        }
        else if (result == JFileChooser.ERROR_OPTION) {
            toast.showMessage(Project_CompilePaths.TEXT_ERROR_FileChooserError, Project_CompilePaths.WINDOW_TITLE_Error);
        }
    }

    /**
     * Submits user selected path
     * Sets up default values
     * @param e
     */
    @FXML
    public void btn_submitFileDestination(ActionEvent e){
        if(!tf_FileLocation.getText().isEmpty()){
            installer_manager fts = new installer_manager();
            String mainFilePath = fts.fixFilePath(tf_FileLocation.getText());

            if(fts.createFolderForProject(mainFilePath)){
                fts.setMainPathValue(mainFilePath);
                fts.setFirstTimePref(false);
                fts.setup_HomeFxmlFile(mainFilePath);
                changeScreen(Common_ControllerMethods.CHANGE_SCREEN_NORMAL, Project_CompilePaths.FILE_FXML_Main, e, Project_CompilePaths.WINDOW_TITLE_Main,null, false);
            }else{
                // TODO TELL USER THE PLACE THEY PICKED DIDN'T DO ANYTHING
            }
        }
    }
}
