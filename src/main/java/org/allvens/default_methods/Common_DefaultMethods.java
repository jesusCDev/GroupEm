package org.allvens.default_methods;

import org.allvens.assets.Constants_Prefs;

/**
 * Resets Project_WorkSpace Data To Default
 */
public class Common_DefaultMethods implements Constants_Prefs {

    /**
     * Resets values at the end of every session
     */
    public void resetValues(){
    }

    /**
     * Resets values if app is hard reset
     */
    public void hardRest(){
    }

    /**
     * Resets pref value
     * @param prefID - pref id
     * @param value - pref value
     */
    private void resetPrefValues(String prefID, String value){
//        pref.put(prefID, value);
    }

    /**
     * Resets pref value
     * @param prefID - pref id
     * @param value - pref boolean value
     */
    private void resetBooleanPrefValue(String prefID, boolean value){
//        pref.putBoolean(prefID, value);
    }
}
