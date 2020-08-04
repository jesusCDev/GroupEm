package org.allvens.markupLanguage;

public interface MarkUpLanguage_Syntax {
	
	// Text Styles
	int fontSize_Normal = 14;
	
	int fontSize_TitleOne= 28;
	int fontSize_TitleTwo= 22;
	int fontSize_TitleThree= 16;
	
	String fontFamily_Normal = "Verdana";
	String fontFamily_Code = "Actor";
	
	// Blocked Elements
	
	// Titles
	char markup_Title1 = '#'; // font size 24, bold, all caps
	String markup_Title2 = "##"; // font size 20, bold, all caps
	String markup_Title3 = "###"; // font size 16, bold, all caps

	// Quoting text
	char markup_QuotingText = '>';

	// Quoting Code
	String markup_CodeText = "'''";

	// List (nested list will check out next lines indent) - 3 different icons re-start after used
	char markup_UnorderList = '-';
	String markup_OrderList = "1."; // Numbers will be softed and smaller

	
	// Inline Elements
	
	// Bold Text
	String markup_BoldText = "**";
	String markup_BoldText2 = "__";
	
	// Italized Text
	String markup_ItalicText = "*";
	String markup_ItalicText2 = "_";
	
	// Strikethrough
	String markup_StrikethroughText = "~~";
	
	char symbol_Astrix = '*';
	char symbol_Underscore = '_';
	char symbol_Wave = '~';
}
