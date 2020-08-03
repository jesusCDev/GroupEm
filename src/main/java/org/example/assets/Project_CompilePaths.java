package org.example.assets;

import java.util.prefs.Preferences;

/**
 * Values that are used across the project.
 * Values that need to be kept track of.
 * @author jesuscdev
 *
 */
public interface Project_CompilePaths {

    String FILE_PROJECTS = "Home.xml";
    String FOLDER_Home = "GroupEm";
    String FILE_SETTINGS = "Settings.xml";
    String FILE_DOCUMENTATION = "Documentation.xml";
    String FILE_PROJECT_INFO = "Project_WorkSpace(doNotTouchThisDocument).xml";
    String FILE_PROJECT_DOCUMENT_MANAGER = "documentManager.xml";

		// FXML
//        String FILE_FXML_FirstTime = "/default_methods/installer/installer.fxml";
    String FILE_FXML_FirstTime = "installer.fxml";
	String FILE_FXML_Main = "Screen_Home.fxml";
	String FILE_FXML_Settings = "Screen_Settings.fxml";
	String FILE_FXML_CreateNewProject = "Screen_Project_Editor.fxml";
	String FILE_FXML_ProjectView = "Screen_Project_Workspace.fxml";
	String FILE_FXML_DocumentEditor = "Screen_DevelopmentDoc_Editor.fxml";
	String FILE_FXML_DocumentViewer = "Screen_DevelopmentDoc_Viewer.fxml";

		// CSS
	String FILE_CSS = "styles.css";

    // OBJECTS
    Preferences pref = Preferences.userRoot();

    int WINDOW_SIZE_TOAST_Width = 400;
    int WINDOW_SIZE_TOAST_Height = 300;


    String TEXT_TOAST_MESSAGE_Okay = "Okay";
    String TEXT_ERROR_FileChooserCanceled = "Exited out file chooser.";
    String TEXT_ERROR_FileChooserError = "An error has occurred.";

    String WINDOW_TITLE_Main = "Home";
    String WINDOW_TITLE_Settings = "Settings";
    String WINDOW_TITLE_DocumentViewer = "Document Viewer";
    String WINDOW_TITLE_DocumentEditor = "Document Editor";
    String WINDOW_TITLE_CreateNewProject = "Create New Project_WorkSpace";
    String WINDOW_TITLE_ProjectView = "Project_WorkSpace View";
    String WINDOW_TITLE_Error = "Error";

    String sHome_btnText = "Expand Project";
    String sHome_btnDeleteText = "Delete Project_WorkSpace";
    String sFirstTime_FileChooserClickedCancel = "You selected nothing.";
    String sFirstTime_FileChooserErrorOccured = "Something went wrong.";

    // Keywords
    String kw_StatusOngoing = "Ongoing";
    String kw_StatusReturnTo = "Return To";
    String kw_StatusComplete = "Complete";
    String kw_XMLExtention = ".xml";
    char kw_SYMBOL_BackSlash = '/';
    char kw_SYMBOL_FrontSlash = '\\';
    String kw_SYMBOL_Underscore = "_";

    String markupLanguageInfo = "Markup Language \n"
            + "Rich Text Formatting for quick formatting and easy of use. \n"
            + "Symbol Must Be First Char In Line" + "\n"
            + "Title 1 = '#'" + "\n"
            + "Title 2 = '##" + "\n"
            + "Title 3 = '###' " + "\n\n"
            + "Un-order List (unorder list will appear when an dash and indent are present.) = '-' or '	-'" + "\n"
            + "Order List = '1.'" + "\n"
            + "Quote Text = '>'" + "\n"
            + "Code formatted text = ''' " +"\n\n"
            + "Inline Characters------------------------" + "\n"
            + "Bold = '**' and '__'" + "\n"
            + "Italicize = '*' and '_'" + "\n"
            + "Strike Through Text = '~~'";
}
