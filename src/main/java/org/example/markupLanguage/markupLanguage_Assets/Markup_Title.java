package org.example.markupLanguage.markupLanguage_Assets;

import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.example.markupLanguage.MarkUpLanguage_Syntax;

public class Markup_Title implements MarkUpLanguage_Syntax{

	private String value;
	private TextFlow tfTitle;
	
	public Markup_Title(int titleValue, String value) {
		this.value = value.substring((titleValue));
		setTitleAttributes(titleValue);
	}
	
	private void setTitleAttributes(int titleValue) {
		Text textTitle = new Text(value.toUpperCase());
		
		switch(titleValue) {
		case 1:
			// TITILE 1 - Biggest Title
			textTitle.setFont(Font.font(fontFamily_Normal, FontWeight.BOLD, fontSize_TitleOne));
			break;
		case 2:
			// TITLLE 2 - Second Biggest Title
			textTitle.setFont(Font.font(fontFamily_Normal, FontWeight.BOLD, fontSize_TitleTwo));
			break;
		default:
			// TITTLE 3 - Third Biggest Title
			textTitle.setFont(Font.font(fontFamily_Normal, FontWeight.BOLD, fontSize_TitleThree));
			break;
		}
		
		tfTitle = new TextFlow(textTitle);
		tfTitle.setPadding(new Insets(5));
	}
	
	// SETTER METHODS //
	public void setTitleValue(int titleValue) {
		setTitleAttributes(titleValue);
	}
	
	// GETTER METHODS //
	
	public TextFlow getTfTitle() {
		return tfTitle;
	}
}
