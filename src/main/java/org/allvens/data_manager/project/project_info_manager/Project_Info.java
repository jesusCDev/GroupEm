package org.allvens.data_manager.project.project_info_manager;

/**
 * Holds All Info to Create a Project
 */
public class Project_Info {
	
	private String projectName;
	private String projectTitle;
	private String projectStatus;
	private String projectID;
	private String projectPurpose;
	private String projectSummary;
	
	private String[] projectTechnologies;
	private String[] projectDevices;
	
	private int projectDevelopmentPos = 0;

    private String[] projectDevelopment_StartingElements;
    private String[] projectDevelopment_PlanningElements;
    private String[] projectDevelopment_WorkingElements;
    private String[] projectDevelopment_FixingElements;
    private String[] projectDevelopment_FinishedElements;

	private String[][] projectDevelopment_Starting;
	private String[][] projectDevelopment_Planning;
	private String[][] projectDevelopment_Working;
	private String[][] projectDevelopment_Fixing;
	private String[][] projectDevelopment_Finished;

    public Project_Info(){};
	
	public Project_Info(String projectTitle, String projectName, String projectStatus, String projectPurpose,
                        String projectSummary, String[] projectTechnologies, String[] projectDevices){

		this.projectTitle = projectTitle;
		this.projectName = projectName;
		this.projectStatus = projectStatus;
		this.projectPurpose = projectPurpose;
		this.projectSummary = projectSummary;
		
		this.projectTechnologies = projectTechnologies;
		this.projectDevices = projectDevices;
	}

    /****************************************
     /**** Getter Methods
     ****************************************/

	public String getProjectTitle() {
		return projectTitle;
	}
	public String getProjectName() {
		return projectName;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public String getProjectID() {
		return projectID;
	}
	public String getProjectPurpose() {
		return projectPurpose;
	}
	public String getProjectSummary() {
		return projectSummary;
	}
	public int get_ProjectDevelopmentPos() {
		return projectDevelopmentPos;
	}

    /********** CheckBox Getters **********/

	public String[] getProjectTechnologies() {
		return projectTechnologies;
	}
	public String[] getProjectDevices() {
		return projectDevices;
	}

    /********** To do List Getters **********/

	public String[][] getProjectDevelopment_Starting() {
		return projectDevelopment_Starting;
	}
	public String[][] getProjectDevelopment_Planning() {
		return projectDevelopment_Planning;
	}
	public String[][] getProjectDevelopment_Working() {
		return projectDevelopment_Working;
	}
	public String[][] getProjectDevelopment_Fixing() {
		return projectDevelopment_Fixing;
	}
	public String[][] getProjectDevelopment_Finishing() {
		return projectDevelopment_Finished;
	}


    /****************************************
     /**** Setter Methods
     ****************************************/

    /********** To do List Setters **********/

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public void setProjectPurpose(String projectPurpose) {
		this.projectPurpose = projectPurpose;
	}
	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}
	public void set_ProjectDevelopmentPos(int projectDevelopmentPos) {
		this.projectDevelopmentPos = projectDevelopmentPos;
	}

    /********** Check Box Setters **********/

    public void setProjectTechnologies(String[] projectTechnologies) {
        this.projectTechnologies = projectTechnologies;
    }
    public void setProjectDevices(String[] projectDevices) {
        this.projectDevices = projectDevices;
    }

    /********** To do List Setters **********/

    public void setProjectDevelopment_Values(int developmentKey, String[][] values){
        switch (developmentKey){
            case 0:
                setProjectDevelopment_Starting(values);
                break;
            case 1:
                setProjectDevelopment_Planning(values);
                break;
            case 2:
                setProjectDevelopment_Working(values);
                break;
            case 3:
                setProjectDevelopment_Fixing(values);
                break;
            default:
                setProjectDevelopment_Finish(values);
                break;
        }
    }

    public void setProjectDevelopment_Elements(int developmentKey, String[] elements){
        switch (developmentKey){
            case 0:
                setProjectDevelopment_StartingElements(elements);
                break;
            case 1:
                setProjectDevelopment_PlanningElements(elements);
                break;
            case 2:
                setProjectDevelopment_WorkingElements(elements);
                break;
            case 3:
                setProjectDevelopment_FixingElements(elements);
                break;
            default:
                setProjectDevelopment_FinishedElements(elements);
                break;
        }
    }

	public void setProjectDevelopment_Starting(String[][] projectDevelopment_Starting) {
		this.projectDevelopment_Starting = projectDevelopment_Starting;
	}
	public void setProjectDevelopment_Planning(String[][] projectDevelopment_Planning) {
		this.projectDevelopment_Planning = projectDevelopment_Planning;
	}
	public void setProjectDevelopment_Working(String[][] projectDevelopment_Working) {
		this.projectDevelopment_Working = projectDevelopment_Working;
	}
	public void setProjectDevelopment_Fixing(String[][] projectDevelopment_Fixing) {
		this.projectDevelopment_Fixing = projectDevelopment_Fixing;
	}
	public void setProjectDevelopment_Finish(String[][] projectDevelopment_Finished) {
		this.projectDevelopment_Finished = projectDevelopment_Finished;
	}

    /****************************************
     /**** TO DO List Elements
     ****************************************/

    public String[] getProjectDevelopment_StartingElements() {
        return projectDevelopment_StartingElements;
    }

    public String[] getProjectDevelopment_PlanningElements() { return projectDevelopment_PlanningElements; }

    public String[] getProjectDevelopment_WorkingElements() { return projectDevelopment_WorkingElements; }

    public String[] getProjectDevelopment_FixingElements() { return projectDevelopment_FixingElements; }

    public String[] getProjectDevelopment_FinishedElements() { return projectDevelopment_FinishedElements;}


    public void setProjectDevelopment_StartingElements(String[] projectDevelopment_StartingElements) {
        this.projectDevelopment_StartingElements = projectDevelopment_StartingElements;
    }

    public void setProjectDevelopment_PlanningElements(String[] projectDevelopment_PlanningElements) {
        this.projectDevelopment_PlanningElements = projectDevelopment_PlanningElements;
    }

    public void setProjectDevelopment_WorkingElements(String[] projectDevelopment_WorkingElements) {
        this.projectDevelopment_WorkingElements = projectDevelopment_WorkingElements;
    }

    public void setProjectDevelopment_FixingElements(String[] projectDevelopment_FixingElements) {
        this.projectDevelopment_FixingElements = projectDevelopment_FixingElements;
    }
    public void setProjectDevelopment_FinishedElements(String[] projectDevelopment_FinishedElements) {
        this.projectDevelopment_FinishedElements = projectDevelopment_FinishedElements;
    }
}
