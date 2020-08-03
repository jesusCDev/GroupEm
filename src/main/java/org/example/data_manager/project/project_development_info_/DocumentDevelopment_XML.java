package org.example.data_manager.project.project_development_info_;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.prefs.Preferences;

import org.example.assets.Constants_Prefs;
import org.example.assets.Path_Manager;
import org.example.data_manager.XML_Parser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import org.example.assets.Project_CompilePaths;

public class DocumentDevelopment_XML extends XML_Parser implements Project_CompilePaths {

	private DocumentDevelopment_Info document;
	private File xmlDocument;
	private String fileName;
	
	
	/**
	 * Creates xml file or
	 * Gets and Sets data for xml
	 * @param creatingANewDocument - tells class if a new document is being created or not
	 */
	public DocumentDevelopment_XML(boolean creatingANewDocument) {
		if(!creatingANewDocument) {
		    fileName = Preferences.userRoot().get(Constants_Prefs.FILE_EDITING_FileBeingEdited, "");
			xmlDocument = new File(new Path_Manager().FILE_PROJECT__GROUP_EM__DOCUMENTATION__DOC__INFO);
			document = new DocumentDevelopment_Info();
			getXMLInfo();
		}else {
            fileName = generateDocumentId();
            Preferences.userRoot().put(Constants_Prefs.FILE_EDITING_FileBeingEdited, fileName);
			xmlDocument = new File(new Path_Manager().FILE_PROJECT__GROUP_EM__DOCUMENTATION__DOC__INFO);
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
		Element el = root.getChild(DocumentDevelopment_Values.CHILD);

		document.setDocumentName(getXMLData_WithOneElement(el, DocumentDevelopment_Values.NAME));
		document.setDocumentID(getXMLData_WithOneElement(el, DocumentDevelopment_Values.ID));
		document.setDocumentOutline(getXMLData_WithOneElement(el, DocumentDevelopment_Values.OUTLINE));
		
		document.setDocumentDateCreated(getXMLData_WithOneElement(el, DocumentDevelopment_Values.DATE_CREATED));
		document.setDocumentDateEdited(getXMLData_WithOneElement(el, DocumentDevelopment_Values.DATE_EDITED));
		
		document.setDocumentHashtags(getXMLData_WithOneElement_DifferentValues(el, DocumentDevelopment_Values.HASHTAGS));

		document.setTextDocumentation(getXMLData_WithOneElement_DifferentValues_SameAttribute_DifferentAttributeValues(el, DocumentDevelopment_Values.TEXT, DocumentDevelopment_Values.POSITION));
		document.setImagePaths(getXMLData_WithOneElement_DifferentValues_SameAttribute_DifferentAttributeValues(el, DocumentDevelopment_Values.IMAGE, DocumentDevelopment_Values.POSITION));
	}

	public Document setXMLInfo() {
		
		Document doc = new Document();
		Element root = new Element(DocumentDevelopment_Values.ROOT);
		doc.addContent(root);
		Element custElement = new Element(DocumentDevelopment_Values.CHILD);
		root.addContent(custElement);
		
		addChild_OneElement_OneValue(custElement, DocumentDevelopment_Values.NAME, document.getDocumentName());
		addChild_OneElement_OneValue(custElement, DocumentDevelopment_Values.ID, document.getDocumentID());
		addChild_OneElement_OneValue(custElement, DocumentDevelopment_Values.OUTLINE, document.getDocumentOutline());

		addChild_OneElement_OneValue(custElement, DocumentDevelopment_Values.DATE_CREATED, document.getDocumentDateCreated());
		addChild_OneElement_OneValue(custElement, DocumentDevelopment_Values.DATE_EDITED, document.getDocumentDateEdited());

		addChildren_WithSameElement_DifferentValues(custElement, DocumentDevelopment_Values.HASHTAGS, document.getDocumentHashtags());

        addChildren_OneElement_SameAttribute_DifferentAttributeValues(custElement, DocumentDevelopment_Values.POSITION, DocumentDevelopment_Values.TEXT, document.getTextDocumentation());
        addChildren_OneElement_SameAttribute_DifferentAttributeValues(custElement, DocumentDevelopment_Values.POSITION, DocumentDevelopment_Values.IMAGE,document.getImagePaths());
		return doc;
	}


	/********* GETTER AND SETTER METHODS ************/
	
	
	/**
	 * Returns document with values from xml file
	 * @return document with saved values
	 */
	public DocumentDevelopment_Info getDocument() {
		return document;
	}
	
	public String getDocumentName() {
		return fileName;
	}
	
	
	/********* OTHER METHODS ************/
	
	
	public void createDocument(DocumentDevelopment_Info document) {
		this.document = document;
		document.setDocumentID(fileName);
		createFolders();
		createXMLDocument(xmlDocument, DocumentDevelopment_Values.ROOT);
		updateXMLFile();
	}
	
	private void createFolders() {
		File developmentFolder = new File(new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC);
		developmentFolder.mkdir();
        File imagesFolder = new File(new Path_Manager().DIR_PROJECT__GROUP_EM__DOCUMENTATION__DOC__IMAGES);
        imagesFolder.mkdir();
	}
	
	public String generateDocumentId() {
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		for(int i = 0; i < 9; i++) {
			sb.append(ran.nextInt(999));
		}
		return sb.toString();
	}
}
