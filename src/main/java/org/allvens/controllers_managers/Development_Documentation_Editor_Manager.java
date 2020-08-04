package org.allvens.controllers_managers;

import org.allvens.assets.Constants_Prefs;
import org.allvens.assets.Path_Manager;
import org.allvens.assets.Project_CompilePaths;
import org.allvens.controllers_ui.Development_Documentation_Editor_UI_Manager;
import org.allvens.data_manager.project.projec_development_info_button.DocumentDevelopment_Button_XML;
import org.allvens.data_manager.project.project_development_info_.DocumentDevelopment_Info;
import org.allvens.data_manager.project.project_development_info_.DocumentDevelopment_XML;
import org.allvens.default_methods.Common_ControllerMethods;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.prefs.Preferences;

public class Development_Documentation_Editor_Manager {

    private boolean documentBeingEdited;
    private DocumentDevelopment_XML documentManager;
    private Development_Documentation_Editor_UI_Manager ui_manager;

    // Tracks Button Display
    private int visible = 1;

    public void setUp_UIManager(){
        ui_manager = new Development_Documentation_Editor_UI_Manager();
    }

    public void setUp_EditingDocumentValues(TextField tfTitle, TextField tfHashtags, TextArea taOutline, VBox vbDevelopmentContent){

        documentBeingEdited = Preferences.userRoot().getBoolean(Constants_Prefs.FILE_EDITING_EditingSelectedFile, false);
        Preferences.userRoot().putBoolean(Constants_Prefs.FILE_EDITING_EditingSelectedFile, false);

        if(documentBeingEdited) {
            documentManager = new DocumentDevelopment_XML(false);
            // Set Title
            tfTitle.setText(documentManager.getDocument().getDocumentName());

            // Set Hashtags
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < documentManager.getDocument().getDocumentHashtags().length; i++) {
                sb.append(documentManager.getDocument().getDocumentHashtags()[i]);
                sb.append(",");
            }

            sb.deleteCharAt((sb.length() - 1));
            tfHashtags.setText(sb.toString());

            // Set Outline
            taOutline.setText(documentManager.getDocument().getDocumentOutline());

            // Load Filled Content
            presentContent(vbDevelopmentContent, documentManager.getDocument());
        }
    }

    private void presentContent(VBox vbDevelopmentContent, DocumentDevelopment_Info document){
        ArrayList<Node> textOrImages = new ArrayList<>();
        boolean broke;

        for(int i = 0; i < (document.getImagePaths().length + document.getTextDocumentation().length); i++){
            broke = false;

            for(int j = 0; j < document.getTextDocumentation().length; j++) {
                if (document.getTextDocumentation()[j][0].equals(Integer.toString(i))) {
                    textOrImages.add(ui_manager.createContentField(ui_manager.createTextArea(document.getTextDocumentation()[j][1]), "TextArea"));
                    broke = true;
                    break;
                }
            }

            if(!broke){
                for(int j = 0; j < document.getImagePaths().length; j++) {
                    if(document.getImagePaths()[j][0].equals(Integer.toString(i))){
                        textOrImages.add(ui_manager.createContentField(ui_manager.createImageView(new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES + document.getImagePaths()[j][1]), new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES + document.getImagePaths()[j][1]));
                        break;
                    }
                }
            }
        }

        vbDevelopmentContent.getChildren().addAll(textOrImages);
    }

    public void create_TextSection(VBox container){
        container.getChildren().add(ui_manager.createContentField(ui_manager.createTextArea(""), "TextArea"));
    }

    public void create_ImageSection(VBox container){
        // Open File Explorer
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.Images", "jpg", "jpeg","gif","png"));
        int result = chooser.showOpenDialog(null);

        // Present Results
        if (result == JFileChooser.APPROVE_OPTION) {
            // creating image
            container.getChildren().add(ui_manager.createContentField(ui_manager.createImageView(chooser.getSelectedFile().getAbsolutePath()), chooser.getSelectedFile().getAbsolutePath()));
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, Project_CompilePaths.sFirstTime_FileChooserClickedCancel);
        } else if (result == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(null, Project_CompilePaths.sFirstTime_FileChooserErrorOccured);
        }
    }

    public void close_EditScreen(Button btn){
        visible = 1;
        btn.setText("Edit");
        for(int i = 0; i < ui_manager.get_EditBtns().size(); i++){
            ui_manager.get_EditBtns().get(i).setVisible(false);
        }
    }

    public void showHide_EditScreen(ActionEvent e) {
        Button btn = (Button)e.getSource();
        if(visible == 1){
            btn.setText("Cancel");
        }else{
            btn.setText("Edit");
        }
        for(int i = 0; i < ui_manager.get_EditBtns().size(); i++){
            ui_manager.get_EditBtns().get(i).setVisible((visible == 1));
        }
        visible *= -1;
    }

    private void copyFiles(String pathName){
        File newDestinationDir;

        for (int i = 0; i < ui_manager.get_ContentBeingAdded().size(); i++){
            if(!ui_manager.get_ContentBeingAdded().get(i).equals("TextArea")){
                newDestinationDir = new File(new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES + new File(ui_manager.get_ContentBeingAdded().get(i)).getName());
                if(!((new File(ui_manager.get_ContentBeingAdded().get(i)).getParent() + File.separator).equals(new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES))){
                    try {
                        copyFileUsingStream(new File(ui_manager.get_ContentBeingAdded().get(i)), newDestinationDir);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public void save_Values(TextField tfTitle, TextField tfHashtags, TextArea taOutline, VBox vbDevelopmentContent) {
        // Title And Summary
        String title = tfTitle.getText();
        String summary = taOutline.getText();

        // Hash Tags
        String[] hashTagsArray = tfHashtags.getText().replaceAll("\\s", "").split(",");
        if(hashTagsArray[0].equals("") || hashTagsArray[0].equals(" ") || hashTagsArray[0].isEmpty()) {
            hashTagsArray[0] = "All";
        }

        // Calendar Dates
        Calendar calendar = Calendar.getInstance();
        String dateCreated = calendar.get(Calendar.MONTH) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.YEAR);
        String dateEdited = calendar.get(Calendar.MONTH) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.YEAR);

        // Content
        int textAreaItr = 0;

        for(int i = 0; i < ui_manager.get_ContentBeingAdded().size(); i++){
            if(ui_manager.get_ContentBeingAdded().get(i).equals("TextArea")){
                textAreaItr++;
            }
        }

        String[][] textDocumentation = new String[textAreaItr][2];
        String[][] imagePaths = new String[(ui_manager.get_ContentBeingAdded().size() - textAreaItr)][2];

        int imageIterator = 0;
        int textIterator = 0;

        int iter = 0;

        for(Node child :vbDevelopmentContent.getChildren()){
            if(((StackPane)child).getChildren().size() == 2){

                if(ui_manager.get_ContentBeingAdded().get(iter).equals("TextArea")){
                    textDocumentation[textIterator][0] = Integer.toString(iter);
                    textDocumentation[textIterator][1] = ((TextArea)ui_manager.get_ContentField().get(iter).getChildren().get(0)).getText();
                    textIterator++;
                }else{
                    imagePaths[imageIterator][0] = Integer.toString(iter);
                    imagePaths[imageIterator][1] = new File(ui_manager.get_ContentBeingAdded().get(iter)).getName();
                    imageIterator++;
                }
                iter++;
            }
        }


        if(documentBeingEdited) {
            DocumentDevelopment_Button_XML documentXMLManager = new DocumentDevelopment_Button_XML(false);

            documentManager.getDocument().setDocumentName(title);
            documentManager.getDocument().setDocumentDateEdited(dateEdited);
            documentManager.getDocument().setDocumentHashtags(hashTagsArray);

            documentManager.getDocument().setTextDocumentation(textDocumentation);
            documentManager.getDocument().setImagePaths(imagePaths);

            documentXMLManager.getDocumentById(documentManager.getDocument().getDocumentID()).setDocumentName(title);
            documentXMLManager.getDocumentById(documentManager.getDocument().getDocumentID()).setDocumentSummary(summary);
            documentXMLManager.getDocumentById(documentManager.getDocument().getDocumentID()).setDocumentHashtags(hashTagsArray);
            documentXMLManager.getDocumentById(documentManager.getDocument().getDocumentID()).setDocumentDateEdited(dateEdited);

            documentXMLManager.updateXMLFile();
            documentManager.updateXMLFile();

            copyFiles(documentManager.getDocument().getDocumentID());

        }else {
            DocumentDevelopment_Button_XML documentXMLManager = new DocumentDevelopment_Button_XML(false);
            DocumentDevelopment_XML documentXML = new DocumentDevelopment_XML(true);

            DocumentDevelopment_Info document = new DocumentDevelopment_Info(title, documentXML.getDocumentName(), summary,
                    dateCreated, dateEdited, hashTagsArray, textDocumentation, imagePaths);

            documentXML.createDocument(document);
            documentXMLManager.createDocument(title, summary, document.getDocumentID(), dateCreated, dateEdited, hashTagsArray);
            copyFiles(document.getDocumentID());

        }
    }


    public void goto_NextScene(ActionEvent e, BorderPane bpAll) {
        if(documentBeingEdited){
            new Common_ControllerMethods().changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Common_ControllerMethods.FILE_FXML_DocumentViewer, e, Common_ControllerMethods.WINDOW_TITLE_DocumentViewer, bpAll, false);
        }else {
            new Common_ControllerMethods().changeScreen(Common_ControllerMethods.CHANGE_SCREEN_DYNAMIC, Common_ControllerMethods.FILE_FXML_ProjectView, e, Common_ControllerMethods.WINDOW_TITLE_ProjectView, bpAll, false);
        }
    }
}
