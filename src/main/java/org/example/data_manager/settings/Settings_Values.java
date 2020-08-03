package org.example.data_manager.settings;

/**
 * SETTINGS - SETTINGS DEFAULT VALUES
 */
public interface Settings_Values {

    String ROOT = "Settings";
     String CHILD = "Setting";

    String PRODUCTS = "Products";
    String TECH = "Tech";

    // STARTERS
    String[] projectStartingProducts = {"Phone", "Computer", "Web", "Watch"};
    String[] projectStartingTechnologies = {"Kotlin", "Java", "Python", "JavaScript", "HTML", "XML", "CSS", "BootStrap", "Selenium", "C",
            "Android", "DJango", "Git", "jQuery", "sqlLite"};

    String sProjectView_TodoListTitleStarting = "Starting";
    String sProjectView_TodoListTitlePlanning = "Planning";
    String sProjectView_TodoListTitleWorking = "Working";
    String sProjectView_TodoListTitleFixing = "Fixing";
    String sProjectView_TodoListTitleFinished = "Finished";

    // STARTERS
    String[] todolist_Starting = {
            "Title for project",
            "Understand Purpose",
            "Draw out basic idea of app",
            "Search for potential difference",
            "Create Trello Board",
            "Create google calendar goal post"};

    String[] todolist_Planning= {
            "Code modules that can be re-use in project",
            "Pass code modules that can be implemented into project",
            "Wire-framed screens",
            "Screen navigation flow mapped out",
            "List of tech it will use",
            "Separate necessary, possible, and extra features."};

    String[] todolist_Working = {
            "Create logo",
            "Create Icon",
            "Color pallet",
            "Apps main purpose works",
            "Create assets for project",
            "Extra features have been put to the side"};

    String[] todolist_Fixing = {
            "Fix names",
            "Try to break project",
            "Distribute project to people (beta)",
            "Spell check",
            "Add comments (use shelf)",
            "Code is reusable",
            "Animations",
            "All Todo values in code have been figured out.",
            "Won’t crash if files are messed with.",
            "Hard Reset Button"};

    String[] todolist_Finishing = {
            "Project webpage up",
            "Proper Credit has been given to any open-source project",
            "Final build up",
            "If on github, readMe file filled up",
            "Privacy page if needed",
            "Terms of use page if needed",
            "Post app in proper app store"};
}
