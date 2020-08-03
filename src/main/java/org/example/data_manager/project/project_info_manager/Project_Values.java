package org.example.data_manager.project.project_info_manager;

/**
 * PROJECT VIEW - CURRENT VIEWING PROJECT INFO
 */
public interface Project_Values {

	// XML VALUES
	 String ROOT = "projects";
	 String CHILD = "project";
	 String TITLE = "title";
	 String NAME = "name";
	 String ID = "id";
	 String STATUS = "status";
	 String PURPOSE = "purpose";
	 String SUMMARY = "summary";
	 String TECHNOLOGIES = "technologies";
	 String DEVICES = "devices";
	 String DEVELOPMENT = "development";
	
	 String CHECKBOX = "checkbox";

	 String HASHTAG = "hashtag";

	
	// Todolsit Values
     String sProjectView_TodoListTitleStartingElement = "StartingElement";
     String sProjectView_TodoListTitlePlanningElement = "PlanningElement";
     String sProjectView_TodoListTitleWorkingElement = "WorkingElement";
     String sProjectView_TodoListTitleFixingElement = "FixingElement";
     String sProjectView_TodoListTitleFinishedElement = "FinishedElement";

	// Todolist titles
	 String sProjectView_TodoListTitleStart = "Starting";
	 String sProjectView_TodoListTitlePlan = "Planning";
	 String sProjectView_TodoListTitleWork = "Working";
	 String sProjectView_TodoListTitleFix = "Fixing";
	 String sProjectView_TodoListTitleFinish = "Finished";
	
	// todolist Values
	 String[] todolist_Starting = {
			"Project_WorkSpace Name",
			"Project_WorkSpace Title",
			"Project_WorkSpace Summary",
			"Project_WorkSpace Purpose",
			"Project_WorkSpace Resources",
			"Project_WorkSpace Devices",
			"Draw Out Basic Idea Of App", 
			"Figure Out How Many Screens Will Need",
			"Draw Out Button Functions", 
			"Create Logo Off Project_WorkSpace",
			"Prepare Blog Work For Project_WorkSpace",
			"Map out Screens", 
			"Use mind map to plan out project purpose",
			"Seperate Possible, Ncessary, and Extras Features", 
			"Create Trello Board",
			"Figure out what each screen will have to do in the forground", 
			"Figure out what each screen will have to do in the background"};
	
	 String[] todolist_Planning= {
			"Plan Folder Organization",
			"Plan Data Storages",
			"Plan Data Storage Values",
			"Plan Amount of. Controllers", 
			"Plan Naming Scheme",
			"Comment Code Instructions",
			"Plan Data Storage Naming Scheme",
			"Write out basic structure of certain functions"};
	
	 String[] todolist_Working = {
			"Have main features work",
			"Creates Assets For Proejct",
			"Style Project_WorkSpace",
			"Extras features"};
	
	 String[] todolist_Fixing = {
			"Try To Break Project_WorkSpace",
			"Use App For At least A Week",
			"Check All To-do Comments",
			"Mark Comments as Extra If Needed",
			"Spell Check Variables",
			"Spell Check  Strings",
			"Spell Check Titles",
			"Make Sure Every Button Works",
			"Check Code Consistancy",
			"Shorten Code If Possible"};
	
	 String[] todolist_Finishing = {
			"Connect Project_WorkSpace To All public static Accounts",
			"Upload to Website",
			"Start Connecting Blog"};

}
