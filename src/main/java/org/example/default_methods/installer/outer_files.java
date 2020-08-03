package org.example.default_methods.installer;

public interface outer_files {

    String DIR_MainFolder = "GroupEm Folder";

    String DIR_LanguagesFolder = "Projects Folder";

    String[] PROJECT_DIRECTORIES = {DIR_LanguagesFolder};

    String FILE_LanguageManager = "Home File - Manages projects.";
    String[] PROJECT_FILES = {FILE_LanguageManager};

    String File_ProjectInfo = "Project_WorkSpace Files - Contain project's information.";
    String[] PROJECT_MultipleFiles = {File_ProjectInfo + "*"};
}
