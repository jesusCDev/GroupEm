package org.example.data_manager.project.projec_development_info_button;

public class DocumentDevelopment_Button_Info {

	private String documentName;
	private String documentSummary;
	private String documentDateAdded;
	private String documentDateEdited;
	
	private String[] documentHashtags;
	
	// TODO NAEM FILES BY ID AND DO NOT TOUCH TEXT 
	// THIS WAY NO ID IS SAME
	private String documentID;
	
	public DocumentDevelopment_Button_Info() {
		
	}
	
	public DocumentDevelopment_Button_Info(String documentName, String documentSummary, String documentID, String documentDateAdded, String documentDateEdited, String[] documentHashtags) {
		this.documentName = documentName;
		this.documentSummary = documentSummary;
		this.documentDateAdded = documentDateAdded;
		this.documentDateEdited = documentDateEdited;
		this.documentHashtags = documentHashtags;
		this.documentID = documentID;
	}
	
	/*********** GETTERS & SETTERS ***********/
	

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentSummary() {
		return documentSummary;
	}
	
	public String getDocumentID() {
		return documentID;
	}
	
	public String[] getDocumentHashtags() {
		return documentHashtags;
	}

	public void setDocumentSummary(String documentSummary) {
		this.documentSummary = documentSummary;
	}

	public String getDocumentDateEdited() {
		return documentDateEdited;
	}

	public void setDocumentDateEdited(String documentDateEdited) {
		this.documentDateEdited = documentDateEdited;
	}
	
	public String getDocumentDateAdded() {
		return documentDateAdded;
	}

	public void setDocumentDateAdded(String documentDateAdded) {
		this.documentDateAdded = documentDateAdded;
	}

	public void setDocumentHashtags(String[] documentHashtags) {
		this.documentHashtags = documentHashtags;
	}
	
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	
}
