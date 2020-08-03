package org.example.markupLanguage.markupLanguage_Assets;

import java.util.ArrayList;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.markupLanguage.MarkUpLanguage_Syntax;

public class Markup_UnorderedListItem implements MarkUpLanguage_Syntax{

	private String value;
	private int indentCounter = 0;
	private Text tValue;
	private String valueWithSymbol;
	
	public Markup_UnorderedListItem(String value) {
		
		// Get find indents
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < value.length(); i++) {
			if(!(value.charAt(i) == '	')) {
				if(!(value.charAt(i) == markup_UnorderList)) {
					sb.append(value.charAt(i));
				}
			}else {
				indentCounter += 1;
				sb.append(value.charAt(i));
			}
		}
		this.value = sb.toString();
		
		getValueWithSymbol();
	}
	
	private void getValueWithSymbol() {
		
		StringBuilder sb = new StringBuilder();
		String nonIndentedValue = value.substring(indentCounter);
		
		for(int i = 0; i < indentCounter; i++) {
			sb.append("\t");
		}
		
		
		int symbol = 0;
		for(int i = 0; i < indentCounter; i++) {
			symbol++;
			if(symbol == 3) {
				symbol = 0;
			}
		}
		
		if(symbol == 0){
			sb.append("○");
		}else if(symbol == 1) {
			sb.append("■");
		}else {
			sb.append("△");
		}
		
		sb.append(nonIndentedValue);
		sb.append("\n");
		
		valueWithSymbol = sb.toString();
	}
	
	public void setIndent(int indent) {
		indentCounter = indent;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public ArrayList<Text> getTItemFormatted(){
		return new Markup_RichTextTrasnlator(valueWithSymbol).getFormattedText();
	}
	
	public Text getTItem() {
		tValue = new Text(valueWithSymbol);
		tValue.setFont(Font.font(fontFamily_Normal, fontSize_Normal));
		return tValue;
	}
	
	public int getIndent() {
		return indentCounter;
	}
	
	public String getValue() {
		return value;
	}
}
