package org.example.assets;

import java.io.File;
import java.util.prefs.Preferences;

public class Path_Manager {

    private  Preferences pref = Preferences.userRoot();

    public String DIR_HOME = pref.get(Constants_Prefs.FOLDER_Main, null);

    public String DIR_PROJECT_WORKSPACE = DIR_HOME + pref.get(Constants_Prefs.PROJECT_SELECTED_SelectedFromMainScreen, "") + File.separator; // Project Path

    public String DIR_PROJECT__GROUP_EM = DIR_PROJECT_WORKSPACE + Workspace_Constents.GROUP_EM_FILES + File.separator; // project group em data
    public String FILE_PROJECT_INFO = DIR_PROJECT__GROUP_EM + Project_CompilePaths.FILE_PROJECT_INFO; // project info
    public String FILE_PROJECT_DOCUMENT_MANAGER = DIR_PROJECT__GROUP_EM + Project_CompilePaths.FILE_PROJECT_DOCUMENT_MANAGER; // project doc manager
    
    public String DIR_PROJECT__GROUP_EM__DOCUMENTATION = DIR_PROJECT__GROUP_EM + Workspace_Constents.GROUP_EM_FILES__DOCUMENTATION + File.separator; // Project Documentation
    public String DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC = DIR_PROJECT__GROUP_EM__DOCUMENTATION + pref.get(Constants_Prefs.FILE_EDITING_FileBeingEdited, "") + File.separator; // Doc module
    public String FILE_PROJECT__GROUP_EM__DOCUMENTATION__DOC__INFO = DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC + Project_CompilePaths.FILE_DOCUMENTATION; // File consisting of documentation info
    public String DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES = DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC + Workspace_Constents.GROUP_EM_FILES__DOCUMENTATION__IMAGES + File.separator;  // Doc module Images Folder


    // TODO MOVE THESE TO GROUPEM
    public String FILE_PROJECTS = DIR_HOME + Project_CompilePaths.FILE_PROJECTS;
    public String FILE_SETTINGS = DIR_HOME + Project_CompilePaths.FILE_SETTINGS;


}
