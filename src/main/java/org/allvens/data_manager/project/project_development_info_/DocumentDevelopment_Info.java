package org.allvens.data_manager.project.project_development_info_;

public class DocumentDevelopment_Info {

	private String documentName;
	private String documentID;
	private String documentOutline;
	private String documentDateCreated;
	private String documentDateEdited;
	private String[] documentHashtags;
	
	private String[] outline;
	
	// value 1 = Position value 2 = Text
	private String[][] textDocumentation;
	
	// value 1 = Position value 2 = Image Path
	private String[][] imagePaths;
	
	public DocumentDevelopment_Info() {
		
	}
	
	public DocumentDevelopment_Info(String documentName, String documentID, String documentOutline, String documentDateCreated, String documentDateEdited, String[] documentHashtags, String[][] textDocumentation, String[][] imagePaths) {
		this.documentName = documentName;
		this.documentID = documentID;
		this.documentOutline = documentOutline;
		this.documentHashtags = documentHashtags;
		this.documentDateCreated = documentDateCreated;
		this.documentDateEdited = documentDateEdited;

		this.textDocumentation = textDocumentation;
		this.imagePaths = imagePaths;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentOutline() {
		return documentOutline;
	}

	public void setDocumentOutline(String documentOutline) {
		this.documentOutline = documentOutline;
	}

	public String[] getDocumentHashtags() {
		return documentHashtags;
	}

	public void setDocumentHashtags(String[] documentHashtags) {
		this.documentHashtags = documentHashtags;
	}

	public String[] getOutline() {
		return outline;
	}

	public void setOutline(String[] outline) {
		this.outline = outline;
	}

	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

	public String getDocumentDateCreated() {
		return documentDateCreated;
	}

	public void setDocumentDateCreated(String documentDateCreated) {
		this.documentDateCreated = documentDateCreated;
	}

	public String getDocumentDateEdited() {
		return documentDateEdited;
	}

	public void setDocumentDateEdited(String documentDateEdited) {
		this.documentDateEdited = documentDateEdited;
	}

	public String[][] getTextDocumentation() {
		return textDocumentation;
	}

	public void setTextDocumentation(String[][] textDocumentation) {
		this.textDocumentation = textDocumentation;
	}

	public String[][] getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String[][] imagePaths) {
		this.imagePaths = imagePaths;
	}
	
	
}
