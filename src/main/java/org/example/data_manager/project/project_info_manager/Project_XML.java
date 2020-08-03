package org.example.data_manager.project.project_info_manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.example.assets.Path_Manager;
import org.example.assets.Workspace_Constents;
import org.example.data_manager.XML_Parser;
import org.example.data_manager.projects.Projects_Values;
import org.example.data_manager.settings.Settings_XML;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.example.assets.Project_CompilePaths;


public class Project_XML extends XML_Parser implements Project_CompilePaths, Project_Values {
	
	private Project_Info project;
	private File xmlDocument;

	/**
	 * Creates xml file or
	 * Gets and Sets data for xml
	 * @param creatingANewProject - tells class if a new project is being created or not
	 */
	public Project_XML(boolean creatingANewProject) {
		xmlDocument = new File( new Path_Manager().FILE_PROJECT_INFO);
		if(!creatingANewProject) {
			project = new Project_Info();
			getXMLInfo();
		}
	}
	
	/********* GET AND SET PROJECT INFO **********/

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
	
	public void getXMLInfo() {
		
		Document doc = getDoc(xmlDocument);
		Element root = doc.getRootElement();
		Element el = root.getChild(Project_Values.CHILD);

		project.setProjectName(getXMLData_WithOneElement(el, Project_Values.NAME));
		project.setProjectTitle(getXMLData_WithOneElement(el, Project_Values.TITLE));
		project.setProjectStatus(getXMLData_WithOneElement(el, Project_Values.STATUS));
		project.setProjectID(getXMLData_WithOneElement(el, Project_Values.ID));
		project.setProjectPurpose(getXMLData_WithOneElement(el, Project_Values.PURPOSE));
		project.setProjectSummary(getXMLData_WithOneElement(el, Project_Values.SUMMARY));

		project.setProjectDevices(getXMLData_WithOneElement_DifferentValues(el, Project_Values.DEVICES));
		project.setProjectTechnologies(getXMLData_WithOneElement_DifferentValues(el, Project_Values.TECHNOLOGIES));

		project.set_ProjectDevelopmentPos(Integer.parseInt(getXMLData_WithOneElement(el, Project_Values.DEVELOPMENT)));

        project.setProjectDevelopment_StartingElements(getXMLData_WithOneElement_DifferentValues(el, Project_Values.sProjectView_TodoListTitleStartingElement));
        project.setProjectDevelopment_PlanningElements(getXMLData_WithOneElement_DifferentValues(el, Project_Values.sProjectView_TodoListTitlePlanningElement));
        project.setProjectDevelopment_WorkingElements(getXMLData_WithOneElement_DifferentValues(el, Project_Values.sProjectView_TodoListTitleWorkingElement));
        project.setProjectDevelopment_FixingElements(getXMLData_WithOneElement_DifferentValues(el, Project_Values.sProjectView_TodoListTitleFixingElement));
        project.setProjectDevelopment_FinishedElements(getXMLData_WithOneElement_DifferentValues(el, Project_Values.sProjectView_TodoListTitleFinishedElement));

		project.setProjectDevelopment_Starting(getXMLData_WithOneElement_DifferentAttributeValues(doc, Project_Values.sProjectView_TodoListTitleStart, project.getProjectDevelopment_StartingElements()));
		project.setProjectDevelopment_Planning(getXMLData_WithOneElement_DifferentAttributeValues(doc, Project_Values.sProjectView_TodoListTitlePlan, project.getProjectDevelopment_PlanningElements()));
		project.setProjectDevelopment_Working(getXMLData_WithOneElement_DifferentAttributeValues(doc, Project_Values.sProjectView_TodoListTitleWork, project.getProjectDevelopment_WorkingElements()));
		project.setProjectDevelopment_Fixing(getXMLData_WithOneElement_DifferentAttributeValues(doc, Project_Values.sProjectView_TodoListTitleFix, project.getProjectDevelopment_FixingElements()));
		project.setProjectDevelopment_Finish(getXMLData_WithOneElement_DifferentAttributeValues(doc, Project_Values.sProjectView_TodoListTitleFinish, project.getProjectDevelopment_FinishedElements()));
	}

	public Document setXMLInfo() {
		
		Document doc = new Document();
		Element root = new Element(Project_Values.ROOT);
		doc.addContent(root);
		Element custElement = new Element(Project_Values.CHILD);
		root.addContent(custElement);
		
		addChild_OneElement_OneValue(custElement, Project_Values.NAME, project.getProjectName());
		addChild_OneElement_OneValue(custElement, Project_Values.TITLE, project.getProjectTitle());
		addChild_OneElement_OneValue(custElement, Project_Values.STATUS, project.getProjectStatus());
		addChild_OneElement_OneValue(custElement, Project_Values.ID, project.getProjectID());
		addChild_OneElement_OneValue(custElement, Project_Values.PURPOSE, project.getProjectPurpose());
		addChild_OneElement_OneValue(custElement, Project_Values.SUMMARY, project.getProjectSummary());

        addChildren_WithSameElement_DifferentValues(custElement, Project_Values.DEVICES,  project.getProjectDevices());
        addChildren_WithSameElement_DifferentValues(custElement, Project_Values.TECHNOLOGIES, project.getProjectTechnologies());

		addChild_OneElement_OneValue(custElement, Project_Values.DEVELOPMENT, Integer.toString(project.get_ProjectDevelopmentPos()));

        addChildren_WithSameElement_DifferentValues(custElement, Project_Values.sProjectView_TodoListTitleStartingElement,  project.getProjectDevelopment_StartingElements());
        addChildren_WithSameElement_DifferentValues(custElement, Project_Values.sProjectView_TodoListTitlePlanningElement, project.getProjectDevelopment_PlanningElements());
        addChildren_WithSameElement_DifferentValues(custElement, Project_Values.sProjectView_TodoListTitleWorkingElement,  project.getProjectDevelopment_WorkingElements());
        addChildren_WithSameElement_DifferentValues(custElement, Project_Values.sProjectView_TodoListTitleFixingElement, project.getProjectDevelopment_FixingElements());
        addChildren_WithSameElement_DifferentValues(custElement, Project_Values.sProjectView_TodoListTitleFinishedElement,  project.getProjectDevelopment_FinishedElements());

		addChildren_OneElement_SameAttribute_DifferentAttributeValues(custElement, Project_Values.sProjectView_TodoListTitleStart, Project_Values.CHECKBOX, project.getProjectDevelopment_Starting());
		addChildren_OneElement_SameAttribute_DifferentAttributeValues(custElement, Project_Values.sProjectView_TodoListTitlePlan, Project_Values.CHECKBOX, project.getProjectDevelopment_Planning());
		addChildren_OneElement_SameAttribute_DifferentAttributeValues(custElement, Project_Values.sProjectView_TodoListTitleWork, Project_Values.CHECKBOX, project.getProjectDevelopment_Working());
		addChildren_OneElement_SameAttribute_DifferentAttributeValues(custElement, Project_Values.sProjectView_TodoListTitleFix, Project_Values.CHECKBOX, project.getProjectDevelopment_Fixing());
		addChildren_OneElement_SameAttribute_DifferentAttributeValues(custElement, Project_Values.sProjectView_TodoListTitleFinish, Project_Values.CHECKBOX, project.getProjectDevelopment_Finishing());
		
		return doc;
	}


	/********* GETTER AND SETTER METHODS ************/
	
	
	/**
	 * Returns project with values from xml file
	 * @return project with saved values
	 */
	public Project_Info getProject() {
		return project;
	}
	
	
	/********* OTHER METHODS ************/
	
	
	public void createProject(Project_Info project) {
		this.project = project;
		createDirectories();
		createXMLDocument(xmlDocument, Projects_Values.ROOT);
		project.setProjectID(generateProjectId());
		addTodoListStartingValues();
		updateXMLFile();
	}
	
	private void addTodoListStartingValues() {
	    Settings_XML settings_xml = new Settings_XML(false);

	    project.setProjectDevelopment_StartingElements(settings_xml.getDocument().getTodoList_Starting());
	    project.setProjectDevelopment_PlanningElements(settings_xml.getDocument().getTodoList_Planning());
	    project.setProjectDevelopment_WorkingElements(settings_xml.getDocument().getTodoList_Working());
	    project.setProjectDevelopment_FixingElements(settings_xml.getDocument().getTodoList_Fixing());
	    project.setProjectDevelopment_FinishedElements(settings_xml.getDocument().getTodoList_Finished());

		project.setProjectDevelopment_Starting(getFalseDoubleArray(Project_Values.sProjectView_TodoListTitleStart, settings_xml.getDocument().getTodoList_Starting()));
		project.setProjectDevelopment_Planning(getFalseDoubleArray(Project_Values.sProjectView_TodoListTitlePlan, settings_xml.getDocument().getTodoList_Planning()));
		project.setProjectDevelopment_Working(getFalseDoubleArray(Project_Values.sProjectView_TodoListTitleWork, settings_xml.getDocument().getTodoList_Working()));
		project.setProjectDevelopment_Fixing(getFalseDoubleArray(Project_Values.sProjectView_TodoListTitleFix, settings_xml.getDocument().getTodoList_Fixing()));
		project.setProjectDevelopment_Finish(getFalseDoubleArray(Project_Values.sProjectView_TodoListTitleFinish, settings_xml.getDocument().getTodoList_Finished()));
	}
	
	private String[][] getFalseDoubleArray(String arrayName, String[] arrayValues){
		String[][] newArray = new String[arrayValues.length][2];
		
		for(int i = 0; i < arrayValues.length; i++) {
			newArray[i][0] = arrayValues[i];
			newArray[i][1] = "false";
		}
		
		return newArray;
	}
	
	private void createDirectories() {
		File f = new File(new Path_Manager().DIR_HOME + project.getProjectName());
		f.mkdir();
		
        create_Folders("", Workspace_Constents.GENERAL_SUB_FOLDERS);

        create_Folders(Workspace_Constents.BUILD, Workspace_Constents.BUILD_SUB_FOLDERS);

        create_Folders(Workspace_Constents.DESIGN, Workspace_Constents.DESIGN_SUB_FOLDERS);
        create_Folders(Workspace_Constents.DESIGN + File.separator + Workspace_Constents.DESIGN__FINAL_ASSETS, Workspace_Constents.DESIGN__FINAL_ASSETS__SUB_FOLDERS);
        create_Folders(Workspace_Constents.DESIGN + File.separator + Workspace_Constents.DESIGN__WORKING_ON_ASSETS, Workspace_Constents.DESIGN__WORKING_ON_ASSETS___SUB_FOLDERS);

        create_Folders(Workspace_Constents.RESEARCH, Workspace_Constents.RESEARCH_SUB_FOLDERS);

        create_Folders(Workspace_Constents.GROUP_EM_FILES, Workspace_Constents.GROUP_EM_FILES__SUB_FOLDERS);
	}
	
	private void create_Folders(String path, String[] array) {
		File f = new File( new Path_Manager().DIR_HOME + project.getProjectName() + File.separator + path);
		f.mkdirs();
		// TODO CAN PROBABLY CHANGE THIS
		
		for(int i = 0; i < array.length; i++) {
			f = new File( new Path_Manager().DIR_HOME + project.getProjectName() + File.separator + path + File.separator + array[i]);
			f.mkdir();
		}
	}
	
	private String generateProjectId() {
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		for(int i = 0; i < 9; i++) {
			sb.append(ran.nextInt(999));
		}
		return sb.toString();
	}
}
