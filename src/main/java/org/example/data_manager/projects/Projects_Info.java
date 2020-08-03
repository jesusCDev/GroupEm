package org.example.data_manager.projects;

/**
 * Info about Menu Button per project
 * @author jesuscdev
 *
 */
public class Projects_Info {
	
	private String projectName;
	private String projectPurpose; // Will only be 250 char long
	private String projectStatus;
	private String projectId;
	
	
	public Projects_Info() {
		
	}
	
	/**
	 * Sets info for manager button ( runs only when project already exist )
	 * @param projectName - Name Of Project_WorkSpace
	 * @param projectStatus - Status of Project_WorkSpace
	 * @param projectPurpose - Summary Of Project_WorkSpace
 	 * @param id - ID of project
	 */
	public Projects_Info(String projectName, String projectStatus, String projectPurpose, String projectId){
		this.projectName = projectName;
		this.projectStatus = projectStatus;
		this.projectPurpose = projectPurpose;
		this.projectId = projectId;
	}	
	
	//** Getters and Setters **//
	
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
		
	public void setProjectPurpose(String projectPurpose) {
		this.projectPurpose = projectPurpose;
	}
	
	public void setProjectID(String projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public String getProjectStatus() {
		return projectStatus;
	}
	
	public String getProjectPurpose() {
		return projectPurpose;
	}
	
	public String getProjectID() {
		return projectId;
	}
}
