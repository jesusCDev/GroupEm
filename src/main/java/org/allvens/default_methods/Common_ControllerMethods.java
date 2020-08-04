package org.allvens.default_methods;

import org.allvens.Main;
import org.allvens.assets.Project_CompilePaths;
import org.allvens.assets.Constants_Prefs;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Common_ControllerMethods implements Constants_Prefs, Project_CompilePaths {


    // todo delete this
    protected void presentUserInformation(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static final int CHANGE_SCREEN_NORMAL = 0;
    public static final int CHANGE_SCREEN_NORMAL_ALWAYS_ON_TOP = 1;
    public static final int CHANGE_SCREEN_DYNAMIC = 2;
    public static final int CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP = 3;

    /****************************************
     /**** Screen Changing Methods
     ****************************************/
    /********** Setting Method **********/
    public  void set_ScreenSize(BorderPane bodyPane){
        if(!PROJECT_Prefs.getBoolean(WINDOW_SIZE_Maximized, false)){
            bodyPane.setPrefWidth(PROJECT_Prefs.getDouble(WINDOW_SIZE_Width, 800.0));
            bodyPane.setPrefHeight(PROJECT_Prefs.getDouble(WINDOW_SIZE_Height, 800.0));
        }
    }

    /********** Screen Checking **********/
    public void screen_checkAlwaysOnTop(String prefsID, MouseEvent event, String fxmlScreen, String windowTitle, BorderPane bpAll){
        if(PROJECT_Prefs.getBoolean(prefsID, false)){
            changeScreen(CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, fxmlScreen, event, windowTitle, bpAll, true);
        }else{
            changeScreen(CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, fxmlScreen, event, windowTitle, bpAll, false);
        }
    }

    public void screen_checkAlwaysOnTop(String prefsID, ActionEvent event, String fxmlScreen, String windowTitle, BorderPane bpAll){
        if(PROJECT_Prefs.getBoolean(prefsID, false)){
            changeScreen(CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, fxmlScreen, event, windowTitle, bpAll, true);
        }else{
            changeScreen(CHANGE_SCREEN_DYNAMIC_ALWAYS_ON_TOP, fxmlScreen, event, windowTitle, bpAll, false);
        }
    }

    /**
     * Saves screen demotions
     * @param bpAll
     */
    private void changeDynamic_SaveWidth(BorderPane bpAll) {
        PROJECT_Prefs.putDouble(WINDOW_SIZE_Width, bpAll.getWidth());
        PROJECT_Prefs.putDouble(WINDOW_SIZE_Height, bpAll.getHeight());
    }

    /********** Screen Changing **********/
    /**
     * Changes Stage to new scene using Action Events
     * @param changeScreenSettings - Type of Screen Changing
     * @param fxmlScreen - Screen Changing
     * @param e - Event
     * @param bpAll - Top Window
     * @param alwaysOnTop - Always On Top Value
     */
    public  void changeScreen(int changeScreenSettings, String fxmlScreen, ActionEvent e, String windowTitle, BorderPane bpAll, boolean alwaysOnTop){

        if(changeScreenSettings > 1){
            changeDynamic_SaveWidth(bpAll);
        }

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle(windowTitle);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(Main.class.getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(Main.class.getResource(FILE_CSS).toExternalForm());

        if(changeScreenSettings == 1 || changeScreenSettings == 3){
            stage.setAlwaysOnTop(alwaysOnTop);
        }

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            PROJECT_Prefs.putBoolean(WINDOW_SIZE_Maximized, true);
        }else{
            PROJECT_Prefs.putBoolean(WINDOW_SIZE_Maximized, false);
        }

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes Stage to new scene using Mouse Events
     * @param changeScreenSettings - Type of Screen Changing
     * @param fxmlScreen - Screen Changing
     * @param e - Event
     * @param bpAll - Top Window
     * @param alwaysOnTop - Always On Top Value
     */
    public void changeScreen(int changeScreenSettings, String fxmlScreen, MouseEvent e, String windowTitle, BorderPane bpAll, boolean alwaysOnTop){

        if(changeScreenSettings > 1){
            changeDynamic_SaveWidth(bpAll);
        }

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle(windowTitle);

        Parent loader = null;
        try {
            loader = FXMLLoader.load(Main.class.getResource(fxmlScreen));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(loader);
        scene.getStylesheets().add(Main.class.getResource(FILE_CSS).toExternalForm());

        if(changeScreenSettings == 1 || changeScreenSettings == 3){
            stage.setAlwaysOnTop(alwaysOnTop);
        }

        if (stage.isFullScreen()) {
            stage.setMaximized(true);
            PROJECT_Prefs.putBoolean(WINDOW_SIZE_Maximized, true);
        }else{
            PROJECT_Prefs.putBoolean(WINDOW_SIZE_Maximized, false);
        }

        stage.setScene(scene);
        stage.show();
    }
}
