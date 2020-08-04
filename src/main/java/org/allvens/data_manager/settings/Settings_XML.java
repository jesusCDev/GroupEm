package org.allvens.data_manager.settings;

import org.allvens.assets.Path_Manager;
import org.allvens.assets.Project_CompilePaths;
import org.allvens.data_manager.XML_Parser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Settings_XML extends XML_Parser implements Project_CompilePaths {

    private Settings_Info settingsDocument;
    private File xmlDocument;

    public Settings_XML(boolean creatingNewGroup){
        xmlDocument = new File(new Path_Manager().FILE_SETTINGS);
        settingsDocument = new Settings_Info();

        if(!creatingNewGroup){
            getXMLInfo();
        }
    }

    @Override
    public void updateXMLFile() {
        Document doc = setXMLInfo();
        XMLOutputter outputter = new XMLOutputter();

        try {
            FileWriter writer = new FileWriter(xmlDocument);
            outputter.output(doc, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getXMLInfo() {
        Document doc = getDoc(xmlDocument);
        Element root = doc.getRootElement();
        Element el = root.getChild(Settings_Values.CHILD);

        settingsDocument.setTechs(getXMLData_WithOneElement_DifferentValues(el, Settings_Values.TECH));
        settingsDocument.setDevices(getXMLData_WithOneElement_DifferentValues(el, Settings_Values.PRODUCTS));

        settingsDocument.setTodoList_Starting(getXMLData_WithOneElement_DifferentValues(el, Settings_Values.sProjectView_TodoListTitleStarting));
        settingsDocument.setTodoList_Planning(getXMLData_WithOneElement_DifferentValues(el, Settings_Values.sProjectView_TodoListTitlePlanning));
        settingsDocument.setTodoList_Working(getXMLData_WithOneElement_DifferentValues(el, Settings_Values.sProjectView_TodoListTitleWorking));
        settingsDocument.setTodoList_Fixing(getXMLData_WithOneElement_DifferentValues(el, Settings_Values.sProjectView_TodoListTitleFixing));
        settingsDocument.setTodoList_Finished(getXMLData_WithOneElement_DifferentValues(el, Settings_Values.sProjectView_TodoListTitleFinished));
    }

    @Override
    public Document setXMLInfo() {
        Document doc = new Document();
        Element root = new Element(Settings_Values.ROOT);
        doc.addContent(root);
        Element custElement = new Element(Settings_Values.CHILD);
        root.addContent(custElement);

        addChildren_WithSameElement_DifferentValues(custElement, Settings_Values.PRODUCTS, settingsDocument.getDevices());
        addChildren_WithSameElement_DifferentValues(custElement, Settings_Values.TECH, settingsDocument.getTech());

        addChildren_WithSameElement_DifferentValues(custElement, Settings_Values.sProjectView_TodoListTitleStarting, settingsDocument.getTodoList_Starting());
        addChildren_WithSameElement_DifferentValues(custElement, Settings_Values.sProjectView_TodoListTitlePlanning, settingsDocument.getTodoList_Planning());
        addChildren_WithSameElement_DifferentValues(custElement, Settings_Values.sProjectView_TodoListTitleWorking, settingsDocument.getTodoList_Working());
        addChildren_WithSameElement_DifferentValues(custElement, Settings_Values.sProjectView_TodoListTitleFixing, settingsDocument.getTodoList_Fixing());
        addChildren_WithSameElement_DifferentValues(custElement, Settings_Values.sProjectView_TodoListTitleFinished, settingsDocument.getTodoList_Finished());

        return doc;
    }

    public void reset_DefaultValues(){
        settingsDocument.setDevices(Settings_Values.projectStartingProducts);
        settingsDocument.setTechs(Settings_Values.projectStartingTechnologies);

        settingsDocument.setTodoList_Starting(Settings_Values.todolist_Starting);
        settingsDocument.setTodoList_Planning(Settings_Values.todolist_Planning);
        settingsDocument.setTodoList_Working(Settings_Values.todolist_Working);
        settingsDocument.setTodoList_Fixing(Settings_Values.todolist_Fixing);
        settingsDocument.setTodoList_Finished(Settings_Values.todolist_Finishing);

        updateXMLFile();
    }

    public void createSettingsXMLDocument(){
        settingsDocument.setDevices(Settings_Values.projectStartingProducts);
        settingsDocument.setTechs(Settings_Values.projectStartingTechnologies);

        settingsDocument.setTodoList_Starting(Settings_Values.todolist_Starting);
        settingsDocument.setTodoList_Planning(Settings_Values.todolist_Planning);
        settingsDocument.setTodoList_Working(Settings_Values.todolist_Working);
        settingsDocument.setTodoList_Fixing(Settings_Values.todolist_Fixing);
        settingsDocument.setTodoList_Finished(Settings_Values.todolist_Finishing);

        createXMLDocument(xmlDocument, Settings_Values.ROOT);
        updateXMLFile();
    }

    public Settings_Info getDocument(){
        return settingsDocument;
    }
}
