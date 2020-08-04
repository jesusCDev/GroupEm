package org.allvens.markupLanguage.markupLanguage_Assets;

import org.allvens.markupLanguage.MarkUpLanguage_Syntax;

public class Markup_RichTextEdit implements MarkUpLanguage_Syntax{
	
	private int editStyleValue;
	private String editStyle;
	private int startingValue;
	private int endingValue;

	public Markup_RichTextEdit(String editStyle, int startingValue) {
		
		
		this.editStyle = editStyle;
		setTextStyle();
		
		this.startingValue = startingValue;
	}
	
	public void setEndingValue(int endingValue) {
		this.endingValue = endingValue;
	}
	
	private void setTextStyle() {
	
		if(editStyle.equals(markup_BoldText) || editStyle.equals(markup_BoldText2)) {
			this.editStyleValue = 1;
		} else if(editStyle.equals(markup_ItalicText) || editStyle.equals(markup_ItalicText2)) {
			this.editStyleValue = 2;
		}else if(editStyle.equals(markup_StrikethroughText)){
			this.editStyleValue = 3;
		}else {
			this.editStyleValue = 0;
		}
	}
	
	public int getStartingValue() {
		return startingValue;
	}
	
	public int getEndingValue() {
		return endingValue;
	}
	
	public String getTextStyle() {
		return editStyle;
	}
	public int getTextStyleValue() {
		return editStyleValue;
	}
}
