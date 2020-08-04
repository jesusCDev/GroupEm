package org.allvens.assets;

import java.util.prefs.Preferences;

public interface Constants_Prefs {

    Preferences PROJECT_Prefs = Preferences.userRoot();

    String PROJECT_KEY = "Key-a15DsF8wf(d*";

    String PROJECT_FirstTime = "PROJECT_FirstTimeUsingApp_" + PROJECT_KEY;
    // this will present the project that is being worked on
    String PROJECT_SELECTED_SelectedFromMainScreen = "PROJECT_SELECTED_HomeScreenProject_" + PROJECT_KEY;
    // this represents the id of the documents
    String FOLDER_ProjectBeingWorkedOn = "Folder_ProjectBeingWorkedOn_" + PROJECT_KEY;

    String FILE_EDITING_EditingSelectedFile = "FILE_EDITING_EditSelectedFile_" + PROJECT_KEY;
    String FILE_EDITING_FileBeingEdited = "FILE_EDITING_FileBeingEdited" + PROJECT_KEY;

    // Window Sizes
    String WINDOW_SIZE_Width = "WINDOW_SIZE_Width_" + PROJECT_KEY;
    String WINDOW_SIZE_Height = "WINDOW_SIZE_Height_" + PROJECT_KEY;
    String WINDOW_SIZE_Maximized = "WINDOW_SIZE_Maximized_" + PROJECT_KEY;

    String FOLDER_Main = "Folder_Main_" + PROJECT_KEY;

}