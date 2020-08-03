package org.example.assets;

public interface Workspace_Constents {

    // General Folder
    String BUILD = "Build";
    // Sub Folder
    String BUILD__BETA = "Beta";
    String BUILD__FINAL = "Final";
    String BUILD__TESTING = "Testing";
    String[] BUILD_SUB_FOLDERS = {BUILD__BETA, BUILD__FINAL, BUILD__TESTING};


    // General Folder
    String DESIGN = "Design";
    // Sub Folder
    String DESIGN__FINAL_ASSETS = "Final_Assets";
    String DESIGN__FINAL_ASSETS__ICONS = "Icons";
    String DESIGN__FINAL_ASSETS__IMAGES = "Images";
    String DESIGN__FINAL_ASSETS__SVG = "SVG";
    String[] DESIGN__FINAL_ASSETS__SUB_FOLDERS = {DESIGN__FINAL_ASSETS__ICONS, DESIGN__FINAL_ASSETS__IMAGES, DESIGN__FINAL_ASSETS__SVG};
    String DESIGN__FONTS = "Fonts";
    String DESIGN__IDEAS = "Ideas";
    String DESIGN__SOUND = "Sound";
    String DESIGN__WORKING_ON_ASSETS = "Working_On_Assets";
    String DESIGN__WORKING_ON_ASSETS__ICONS = "Icons";
    String DESIGN__WORKING_ON_ASSETS__IMAGES = "Images";
    String DESIGN__WORKING_ON_ASSETS__SVG = "SVG";
    String[] DESIGN__WORKING_ON_ASSETS___SUB_FOLDERS = {DESIGN__WORKING_ON_ASSETS__ICONS, DESIGN__WORKING_ON_ASSETS__IMAGES, DESIGN__WORKING_ON_ASSETS__SVG};
    String[] DESIGN_SUB_FOLDERS = {DESIGN__FINAL_ASSETS, DESIGN__FONTS, DESIGN__IDEAS, DESIGN__SOUND, DESIGN__WORKING_ON_ASSETS};


    // General Folder
    String RESEARCH = "Research";
    // Sub Folder
    String RESEARCH__DEVELOPMENT = "Development";
    String RESEARCH__IDEAS = "Ideas";
    String RESEARCH__LOGS = "Logs";
    String[] RESEARCH_SUB_FOLDERS = {RESEARCH__DEVELOPMENT, RESEARCH__IDEAS, RESEARCH__LOGS};


    // General Folder
    String TESTING = "Testing";


    // General Folder
    String OPEN_SOURCE = "Open_Source";


    // General Folder
    String GROUP_EM_FILES = "Group_Em_Files";
    // Sub Folder
    String GROUP_EM_FILES__DOCUMENTATION = "Documentation";
    String GROUP_EM_FILES__DOCUMENTATION__IMAGES = "Images";
    String[] GROUP_EM_FILES__SUB_FOLDERS = {GROUP_EM_FILES__DOCUMENTATION};


    String[] GENERAL_SUB_FOLDERS = {BUILD, DESIGN, RESEARCH, TESTING, OPEN_SOURCE};
}
