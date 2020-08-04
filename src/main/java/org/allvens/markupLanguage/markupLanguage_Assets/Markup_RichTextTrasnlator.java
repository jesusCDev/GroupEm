package org.allvens.markupLanguage.markupLanguage_Assets;

import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.allvens.markupLanguage.MarkUpLanguage_Syntax;

public class Markup_RichTextTrasnlator implements MarkUpLanguage_Syntax{
	
	private String sentence;
	private TextFlow tfRichText;
	private ArrayList<Text> formatedFragments;
	
	public Markup_RichTextTrasnlator(String sentence) {
		
		this.sentence = sentence;
		
		Stack<String> sSentence = new Stack<>();
		Stack<Markup_RichTextEdit> sEditor = new Stack<>();
		ArrayList<Markup_RichTextEdit> alMarkupEdit = new ArrayList<>();
		
		String singleCharConverter;
		String doubleCharConverter;
		
		boolean formatedValue;
		int nextValue;
		
		for (int i = 0; i < sentence.length(); i++) {
			formatedValue = false;
			singleCharConverter = Character.toString(sentence.charAt(i));

			nextValue = (1 + i);

			if(nextValue < sentence.length()){
				
				if((sentence.charAt(nextValue) == symbol_Astrix) || (sentence.charAt(nextValue) == symbol_Underscore) || (sentence.charAt(nextValue) == symbol_Wave)) {
					
					doubleCharConverter = Character.toString(sentence.charAt(i)) + sentence.charAt(nextValue);
					
					if((doubleCharConverter.equals(markup_BoldText)) || (doubleCharConverter.equals(markup_BoldText2)) || (doubleCharConverter.equals(markup_StrikethroughText))) {

						if(!sSentence.isEmpty()) {
							switch(doubleCharConverter) {
							case markup_BoldText:
								
								if(sSentence.peek().equals(markup_BoldText)) {	
									
									sSentence.pop();
									
									Markup_RichTextEdit mrtE = sEditor.peek();
									mrtE.setEndingValue(i);
									alMarkupEdit.add(mrtE);
									sEditor.pop();
									
								} else {
									
									sSentence.push(markup_BoldText);
									sEditor.push(new Markup_RichTextEdit(markup_BoldText, i));
									
								}
								
								break;
							case markup_BoldText2:
								
								if(sSentence.peek().equals(markup_BoldText2)) {	
									
									sSentence.pop();
									
									Markup_RichTextEdit mrtE = sEditor.peek();
									mrtE.setEndingValue(i);
									alMarkupEdit.add(mrtE);
									sEditor.pop();
									
								} else {
									
									sSentence.push(markup_BoldText2);
									sEditor.push(new Markup_RichTextEdit(markup_BoldText2, i));
									
								}
								
								break;
							default:
								
								if(sSentence.peek().equals(markup_StrikethroughText)) {	
									
									sSentence.pop();
									
									Markup_RichTextEdit mrtE = sEditor.peek();
									mrtE.setEndingValue(i);
									alMarkupEdit.add(mrtE);
									sEditor.pop();
									
								} else {
									
									sSentence.push(markup_StrikethroughText);
									sEditor.push(new Markup_RichTextEdit(markup_StrikethroughText, i));
									
								}
								
								break;
							}
						}else {
							sSentence.push(doubleCharConverter);
							sEditor.push(new Markup_RichTextEdit(doubleCharConverter, i));
						}
						
						formatedValue = true;
						i++;					
					}
				}
			}

			if(formatedValue == false) {
				if((singleCharConverter.equals(markup_ItalicText)) || (singleCharConverter.equals(markup_ItalicText2))){
					if(!sSentence.isEmpty()) {
						switch (singleCharConverter) {
						case markup_ItalicText:
							
							if(sSentence.peek().equals(markup_ItalicText)) {	
								
								sSentence.pop();
								
								Markup_RichTextEdit mrtE = sEditor.peek();
								mrtE.setEndingValue(i);
								alMarkupEdit.add(mrtE);
								sEditor.pop();
								
							} else {
								
								sSentence.push(markup_ItalicText);
								sEditor.push(new Markup_RichTextEdit(markup_ItalicText, i));
								
							}
							
							break;
							
						default:
							
							if(sSentence.peek().equals(markup_ItalicText2)) {	
								
								sSentence.pop();
								
								Markup_RichTextEdit mrtE = sEditor.peek();
								mrtE.setEndingValue(i);
								alMarkupEdit.add(mrtE);
								sEditor.pop();
								
							} else {
								
								sSentence.push(markup_ItalicText2);
								sEditor.push(new Markup_RichTextEdit(markup_ItalicText2, i));
								
							}
							
							break;
						}
					}else {
						sSentence.push(singleCharConverter);
						sEditor.push(new Markup_RichTextEdit(singleCharConverter, i));
					}
				}
			}
		}
		
		if(sSentence.isEmpty()) {
			
			tfRichText = new TextFlow();
			tfRichText.getChildren().addAll(makeFormatedSentence(alMarkupEdit));
			
		}else {
			
			tfRichText = new TextFlow(new Text(sentence));
		}
	}
	
	private ArrayList<Text> makeFormatedSentence(ArrayList<Markup_RichTextEdit> editList) {
		ArrayList<Text> formatedFragments = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		int style = 0;
		int styled = 0;
		
		boolean notChanged;
		
		for(int i = 0; i < sentence.length(); i++) {
			
			notChanged = false;
			
			for(int j = 0; j < editList.size(); j++) {
				if(i == editList.get(j).getStartingValue()) {
					// add styles
					style = editList.get(j).getTextStyleValue();
					notChanged = true;
					if(editList.get(j).getTextStyle().equals(markup_BoldText) || editList.get(j).getTextStyle().equals(markup_BoldText2) || editList.get(j).getTextStyle().equals(markup_StrikethroughText)) {
						i++;
					}
					break;
				}
				if(i == editList.get(j).getEndingValue()) {
					// delete styles
					style = editList.get(j).getTextStyleValue();
					notChanged = true;
					if(editList.get(j).getTextStyle().equals(markup_BoldText) || editList.get(j).getTextStyle().equals(markup_BoldText2) || editList.get(j).getTextStyle().equals(markup_StrikethroughText)) {
						i++;
					}
					break;
				}
			}
			if(notChanged == false) {
				sb.append(sentence.charAt(i));
			}else {
				
				// create text send in the styles
				formatedFragments.add(createTextWithStyle(sb.toString(), styled));
				// reset style does soemthing
				styled = style;
				// clear stirng builder
				sb.delete(0, sb.length());
			}
		}
		
		// CRETATE GEXT FLOW HERE
		formatedFragments.add(new Text(sb.toString()));
		this.formatedFragments = formatedFragments;
		return formatedFragments;
	}
	
	private Text createTextWithStyle(String textBeingEdited, int style){
		Text styledText = new Text(textBeingEdited);
		
		if(style == 0) {
			styledText.setFont(Font.font(fontFamily_Normal, fontSize_Normal));
		}
		if(style == 1) {
			styledText.setFont(Font.font(fontFamily_Normal, FontWeight.BOLD, fontSize_Normal));
		}
		if(style == 2) {
			styledText.setFont(Font.font(fontFamily_Normal, FontPosture.ITALIC, fontSize_Normal));
		}
		if(style == 3) {
			styledText.setStrikethrough(true);
		}
		
		return styledText;
	}
	
	public TextFlow getRichTextFlow() {
		return tfRichText;
	}
	
	public ArrayList<Text> getFormattedText(){
        for(int i = 0; i < formatedFragments.size(); i++){
            formatedFragments.get(i).getStyleClass().add("paragraph");
        }
	    return formatedFragments;
	}
	
}
