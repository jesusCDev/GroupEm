package org.example.data_manager.projects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.assets.Path_Manager;
import org.example.data_manager.project.project_info_manager.Project_Info;
import org.example.data_manager.XML_Parser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import org.example.assets.Project_CompilePaths;

public class Projects_XML extends XML_Parser implements Project_CompilePaths {
	
	public ArrayList<Projects_Info> projects;
	private File xmlDocument;
	

	public Projects_XML() {
		projects = new ArrayList<>();
		xmlDocument = new File(new Path_Manager().FILE_PROJECTS);
		getXMLInfo();
	}	
	
	
	/********* GET AND SET PROJECT INFO **********/
	
	
	public void updateXMLFile() {
		
		organizeArrayListAlphabetically();
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
		
		List<Element> projectElements = root.getChildren(Projects_Values.CHILD);
		ArrayList<Projects_Info> data = new ArrayList<>();
		
		for(Element pe: projectElements) {
			
			Projects_Info btn = new Projects_Info();
			data.add(btn);
			
			btn.setProjectName(getXMLData_WithOneElement(pe, Projects_Values.NAME));
			btn.setProjectStatus(getXMLData_WithOneElement(pe, Projects_Values.STATUS));
			btn.setProjectPurpose(getXMLData_WithOneElement(pe, Projects_Values.SUMMARY));
			btn.setProjectID(getXMLData_WithOneElement(pe, Projects_Values.ID));
		}
		
		projects = data;
	}
	
	public Document setXMLInfo() {
		Document doc = new Document();
		Element root = new Element(Projects_Values.ROOT);
		doc.addContent(root);
		
		for(Projects_Info btn: projects) {
			Element custElement = new Element(Projects_Values.CHILD);
			root.addContent(custElement);
			
			addChild_OneElement_OneValue(custElement, Projects_Values.NAME, btn.getProjectName());
			addChild_OneElement_OneValue(custElement, Projects_Values.SUMMARY, btn.getProjectPurpose());
			addChild_OneElement_OneValue(custElement, Projects_Values.STATUS, btn.getProjectStatus());
			addChild_OneElement_OneValue(custElement, Projects_Values.ID, btn.getProjectID());
			
		}
		
		return doc;
	}
	
	
	/********* OTHER PROJECTS ********/

	
	/**
	 * Organaizes array list into alphabetical order
	 */
	private void organizeArrayListAlphabetically() {
		
		// Organizes Array using string values
		ArrayList<String> nameArray = new ArrayList<>();

		for(int i = 0; i < projects.size(); i++) {
			nameArray.add(projects.get(i).getProjectName());
		}
		Collections.sort(nameArray);
		
		// Sets arrays in order
		ArrayList<Projects_Info> newList = new ArrayList<>();
		
		for(int i = 0; i < nameArray.size(); i++) {
			for(int j = 0; j < projects.size(); j++) {
				if(nameArray.get(i).equals(projects.get(j).getProjectName())){
					newList.add(projects.get(j));
					break;
				}
			}
		}
		projects = newList;
	}
	
	public void createProject(Project_Info project) {
		projects.add(new Projects_Info(project.getProjectName(), project.getProjectStatus(), project.getProjectPurpose(), project.getProjectID()));
		updateXMLFile();
	}
	
	public void deleteProject(Projects_Info project) {
		File folder = new File(new Path_Manager().DIR_HOME + project.getProjectName());
		deleteFilesInFolder(folder);
		projects.remove(project);
		updateXMLFile();
	}
	
	private void deleteFilesInFolder(File folder) {
		if(folder.exists() && folder.isDirectory()) {
			for(File f: folder.listFiles()) {
				if(f.delete()) {
					
				}else if(f.isDirectory()) {
					deleteFilesInFolder(f);
				}
			}
		}
		if(folder.exists() && folder.isDirectory()) {
			folder.delete();
		}
	}
	
	/********* GETTER METHODS ************/

	public Projects_Info getProjectById(String projectId) {
		Projects_Info project = null;
		for(int i = 0; i < projects.size(); i++) {
			if(projects.get(i).getProjectID().equals(projectId)) {
				project = projects.get(i);
			}
		}
		return project;
	}
	
	public ArrayList<Projects_Info> getProjects() {
		return projects;
	}
}
