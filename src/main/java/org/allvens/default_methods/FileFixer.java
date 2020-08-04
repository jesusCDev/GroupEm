package org.allvens.default_methods;

import org.allvens.assets.Project_CompilePaths;
import org.allvens.assets.Constants_Prefs;

import java.io.File;

/**
 * Class used to fix file and directories
 */
public class FileFixer {
    public void savePaths(String prefID, String path){
        Constants_Prefs.PROJECT_Prefs.put(prefID, path);
    }

    public boolean checkFrontOfPathForFrontBackSlash(String path){
        return (path.charAt(0) == Project_CompilePaths.kw_SYMBOL_BackSlash || path.charAt(0) == Project_CompilePaths.kw_SYMBOL_FrontSlash);
    }

    /**
     * Fixes path with correct type of separator
     * @param path
     * @return
     */
    public String fixBackFrontSlash(String path){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < path.length(); i++){
            if((path.charAt(i) == Project_CompilePaths.kw_SYMBOL_FrontSlash) || (path.charAt(i) == Project_CompilePaths.kw_SYMBOL_BackSlash)){
                sb.append(File.separator);
            }else{
                sb.append(path.charAt(i));
            }
        }
        return addingSlashAfterPath(sb.toString());
    }

    /**
     * Fixes ending of path
     * @param path
     * @return
     */
    private String addingSlashAfterPath(String path){
        StringBuilder sb = new StringBuilder();
        sb.append(path);

        if(path.charAt((path.length() - 1)) != File.separatorChar){
            sb.append(File.separator);
        }
        return sb.toString();
    }

    /**
     * Insures that file name for folder has not been used yet
     * @param filePath
     * @return
     */
    public String fixDuplicatesPath(String filePath){
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
}
