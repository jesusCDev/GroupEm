package org.example.data_manager.project.projec_development_info_button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.assets.Constants_Prefs;
import org.example.assets.Path_Manager;
import org.example.assets.Workspace_Constents;
import org.example.data_manager.XML_Parser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import org.example.assets.Project_CompilePaths;

public class DocumentDevelopment_Button_XML extends XML_Parser implements Project_CompilePaths {

	private ArrayList<DocumentDevelopment_Button_Info> documents;
	private File xmlDocument;
	

	public DocumentDevelopment_Button_XML(boolean newDocument) {
		documents = new ArrayList<>();
		xmlDocument = new File(new Path_Manager().FILE_PROJECT_DOCUMENT_MANAGER);

		if(!newDocument) {
			getXMLInfo();
		}
	}	
	
	
	/********* GET AND SET PROJECT INFO **********/
	
	
	public void updateXMLFile() {
		
		//organizeArrayListAlphabetically();
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
		
		List<Element> projectElements = root.getChildren(DocumentDevelopment_Button_Values.CHILD);
		ArrayList<DocumentDevelopment_Button_Info> btnList = new ArrayList<>();
		
		for(Element pe: projectElements) {
			
			DocumentDevelopment_Button_Info btn = new DocumentDevelopment_Button_Info();
			
			btnList.add(btn);
			
			btn.setDocumentName(getXMLData_WithOneElement(pe, DocumentDevelopment_Button_Values.NAME));
			btn.setDocumentSummary(getXMLData_WithOneElement(pe, DocumentDevelopment_Button_Values.SUMMARY));
			btn.setDocumentID(getXMLData_WithOneElement(pe, DocumentDevelopment_Button_Values.ID));
		
			btn.setDocumentDateAdded(getXMLData_WithOneElement(pe, DocumentDevelopment_Button_Values.DATE_ADDED));
			btn.setDocumentDateEdited(getXMLData_WithOneElement(pe, DocumentDevelopment_Button_Values.DATE_EDITED));
			btn.setDocumentHashtags(getXMLData_WithOneElement_DifferentValues(pe, DocumentDevelopment_Button_Values.HASHTAG));
		}

		documents = btnList;
	}
	
	public Document setXMLInfo() {
		Document doc = new Document();
		Element root = new Element(DocumentDevelopment_Button_Values.ROOT);
		doc.addContent(root);
		
		for(DocumentDevelopment_Button_Info btn: documents) {
			Element custElement = new Element(DocumentDevelopment_Button_Values.CHILD);
			root.addContent(custElement);
			
			addChild_OneElement_OneValue(custElement, DocumentDevelopment_Button_Values.NAME, btn.getDocumentName());
			addChild_OneElement_OneValue(custElement, DocumentDevelopment_Button_Values.SUMMARY, btn.getDocumentSummary());
			addChild_OneElement_OneValue(custElement, DocumentDevelopment_Button_Values.ID, btn.getDocumentID());

			addChild_OneElement_OneValue(custElement, DocumentDevelopment_Button_Values.DATE_ADDED, btn.getDocumentDateAdded());
			addChild_OneElement_OneValue(custElement, DocumentDevelopment_Button_Values.DATE_EDITED, btn.getDocumentDateEdited());
			addChildren_WithSameElement_DifferentValues(custElement, DocumentDevelopment_Button_Values.HASHTAG, btn.getDocumentHashtags());
			
		}
		
		return doc;
	}
	
	
	/********* OTHER PROJECTS ********/

	
	/**
	 * Organaizes array list into alphabetical order
	 */
	private void organizeArrayListAlphabetically() {
		
		// Organizes Array using string values
		ArrayList<String> dateArray = new ArrayList<>();
		
		for(int i = 0; i < documents.size(); i++) {
			dateArray.add(documents.get(i).getDocumentDateEdited());
		}
		Collections.sort(dateArray);
		
		// Sets arrays in order
		ArrayList<DocumentDevelopment_Button_Info> newList = new ArrayList<>();
		
		for(int i = 0; i < dateArray.size(); i++) {
			for(int j = 0; j < documents.size(); j++) {
				if(dateArray.get(i).equals(documents.get(j).getDocumentDateEdited())){
					newList.add(documents.get(j));
					break;
				}
			}
		}
		
		documents = newList;
	}
	
	public void createDocument(String documentName, String documentSummary, String documentId, String documentDateAdded, String documentDateEdited, String[] documentHashtags) {
		documents.add(new DocumentDevelopment_Button_Info(documentName, documentSummary, documentId, documentDateAdded, documentDateEdited, documentHashtags));
		updateXMLFile();
	}
	
	public void createDocument() {
		createXMLDocument(xmlDocument, DocumentDevelopment_Button_Values.ROOT);
	}
	
	public void deleteDocument(DocumentDevelopment_Button_Info project) {
		File document = new File(pref.get(Constants_Prefs.FOLDER_Main,null) + File.separator + pref.get(Constants_Prefs.FOLDER_ProjectBeingWorkedOn, null) + File.separator + Workspace_Constents.GROUP_EM_FILES + File.separator + Workspace_Constents.GROUP_EM_FILES__DOCUMENTATION + File.separator + project.getDocumentID());
		deleteFilesInFolder(document);
		documents.remove(project);
		updateXMLFile();
	}
	
	private void deleteFilesInFolder(File document) {
		if(document.exists() && document.isDirectory()) {
			for(File f: document.listFiles()) {
				if(f.delete()) {
					
				}else if(f.isDirectory()) {
					deleteFilesInFolder(f);
				}
			}
		}
		
		if(document.exists() && document.isDirectory()) {
			document.delete();
		}
	}
	
	/********* GETTER METHODS ************/

	public DocumentDevelopment_Button_Info getDocumentById(String projectId) {
		DocumentDevelopment_Button_Info project = null;
		
		for(int i = 0; i < documents.size(); i++) {
			if(documents.get(i).getDocumentID().equals(projectId)) {
				project = documents.get(i);
			}
		}
		return project;
	}
	
	public ArrayList<DocumentDevelopment_Button_Info> getDocuments() {
		return documents;
	}
}
