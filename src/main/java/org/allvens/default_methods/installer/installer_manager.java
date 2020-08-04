package org.allvens.default_methods.installer;

import org.allvens.assets.Project_CompilePaths;
import org.allvens.assets.Constants_Prefs;
import org.allvens.data_manager.projects.Projects_Values;
import org.allvens.data_manager.settings.Settings_XML;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class installer_manager implements Constants_Prefs {

    /**
     * Creates main Folder Where Project_WorkSpace And Any Files Related To It Will Remain
     * @param mainFileLocation
     * @return
     */
    public boolean createFolderForProject(String mainFileLocation){
        File f = new File(mainFileLocation);
        return f.mkdir();
    }

    public void setMainPathValue(String mainFileLocation){
        PROJECT_Prefs.put(Constants_Prefs.FOLDER_Main, mainFileLocation);
    }

    public void setFirstTimePref(boolean value){
        PROJECT_Prefs.putBoolean(Constants_Prefs.PROJECT_FirstTime, value);
    }

    /********** main Path Fixer **********/
    /**
     * Creates main Path
     * @param filePath
     * @return
     */
    public String fixFilePath(String filePath){
        return fixDuplicatesPath(fixEndingSymbolOfPath(filePath) + Project_CompilePaths.FOLDER_Home) + File.separator;
    }

    /**
     * Insures that file name for folder has not been used yet
     * @param filePath
     * @return
     */
    private String fixDuplicatesPath(String filePath){
        String newFilePath = filePath;
        String endingExt;
        int iter = 0;

        while(true){
            if(!new File(newFilePath).exists()){
                return newFilePath;
            }
            iter++;
            endingExt = Project_CompilePaths.kw_SYMBOL_Underscore + iter;
            newFilePath = filePath + endingExt;
        }
    }

    private String fixEndingSymbolOfPath(String filePath){
        StringBuilder sb = new StringBuilder();

        if(!Character.toString(filePath.charAt(filePath.length() - 1)).equalsIgnoreCase(File.separator)){
            sb.append(filePath);
            sb.append(File.separator);
            return sb.toString();
        }

        sb.append(filePath);
        return sb.toString();
    }

    /********** Create Defaults **********/
    /**
     * Creates default stack values
     */
    public void setup_HomeFxmlFile(String mainPath){
        try{
            Document doc = new Document();
            Element root = new Element(Projects_Values.ROOT);
            doc.addContent(root);

            XMLOutputter outputter = new XMLOutputter();
            FileWriter writer = new FileWriter(new File(mainPath + Project_CompilePaths.FILE_PROJECTS));
            outputter.output(doc, writer);

            Settings_XML settings = new Settings_XML(true);
            settings.createSettingsXMLDocument();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
