package org.example.markupLanguage;

import java.util.ArrayList;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.example.markupLanguage.markupLanguage_Assets.Markup_CodeSnippet;
import org.example.markupLanguage.markupLanguage_Assets.Markup_OrderedListItem;
import org.example.markupLanguage.markupLanguage_Assets.Markup_Quote;
import org.example.markupLanguage.markupLanguage_Assets.Markup_RichTextTrasnlator;
import org.example.markupLanguage.markupLanguage_Assets.Markup_Title;
import org.example.markupLanguage.markupLanguage_Assets.Markup_UnorderedListItem;

public class MarkUpLanguage_Render implements MarkUpLanguage_Syntax{
	
	private String[] unformatedTextPerLine;
	private ArrayList<TextFlow> textFlowContainer = new ArrayList<>();

	public MarkUpLanguage_Render(String unformatedText) {
		unformatedTextPerLine = unformatedText.split("\r?\n");
		readThroughFile();
	}
	
	private void readThroughFile() {
		
		for(int i = 0; i < unformatedTextPerLine.length; i++) {
			
			int subStringLimit = 3;
			if(unformatedTextPerLine[i].length() < 3) {
				subStringLimit = (unformatedTextPerLine[i].length());
			}
		
			if(unformatedTextPerLine[i].length() > 1) {
				// checks snippet if code is a title
				int title = checkIfSentenceIsATitle(unformatedTextPerLine[i].substring(0, subStringLimit));
				
				if(title > 0) {
					textFlowContainer.add(createTitle(title, unformatedTextPerLine[i]));
				}else {
					
					// Checks snippet if markup is quote
					if(checkIfSentenceIsAQuote(unformatedTextPerLine[i].charAt(0))) {
						textFlowContainer.add(createQuote(unformatedTextPerLine[i]));
					}else {
						
						// Checks if snippet is code markup
						if(checkIfSentenceIsACodeSnippet(unformatedTextPerLine[i])) {
							ArrayList<String> codeSnippets = new ArrayList<>();
							
							for(int j = (i + 1); j < unformatedTextPerLine.length; j++) {
								i++;
								if(!checkIfSentenceIsACodeSnippet(unformatedTextPerLine[j])) {
									codeSnippets.add(unformatedTextPerLine[j]);
								}else {
									break;
								}
							}
							
							textFlowContainer.add(createCodeSnippet(codeSnippets));
						}else {
							
							// Checks if sentence is an unordered list
							if(checkIfSentenceIsAnUnorderList(unformatedTextPerLine[i])) {
								
								ArrayList<Markup_UnorderedListItem> listItems = new ArrayList<>();
								
								for(int j = (i + 0); j < unformatedTextPerLine.length; j++) {
									if(checkIfSentenceIsAnUnorderList(unformatedTextPerLine[j])){
										i++;
										listItems.add(new Markup_UnorderedListItem(unformatedTextPerLine[j]));
									}else {
										i--;
										break;
									}
								}
								
								textFlowContainer.add(createUnorderList(listItems));
								
							}else {
								
								// Checks if sentence is an ordered list
								if(checkIfSentenceIsAnOrderedList(Character.toString(unformatedTextPerLine[i].charAt(0)), unformatedTextPerLine[i].charAt(1))) {
										textFlowContainer.add(new Markup_OrderedListItem(unformatedTextPerLine[i]).getTfOrderedListItem());
								}else {
								
									// Checks sentences for basic formatting
									textFlowContainer.add(new Markup_RichTextTrasnlator(unformatedTextPerLine[i]).getRichTextFlow());
								}
							}
						}
					}
				}
			}else {
				textFlowContainer.add(createUnformattedLine(unformatedTextPerLine[i]));
			}
		}
	}

	
	// CHECKING MARKUP LANGUAGE IN SENTENCE //
	
	
	private boolean checkIfSentenceIsAnOrderedList(String olNumber, char period) {
		try {
			Integer.parseInt(olNumber);
            return period == '.';
        }catch(NumberFormatException e){
				//e.printStackTrace();
				return false;
			}
	}
	
	private boolean checkIfSentenceIsAnUnorderList(String ulSymbol) {
		
		for(int i = 0; i < ulSymbol.length(); i++) {
			if(!(ulSymbol.charAt(i) == '	')) {
                return ulSymbol.charAt(i) == markup_UnorderList;
			}
		}
		
		return false;
	}

	private boolean checkIfSentenceIsACodeSnippet(String sentence) {
        return sentence.equals(markup_CodeText);
    }
	
	private boolean checkIfSentenceIsAQuote(char firstCharacter) {
        return firstCharacter == markup_QuotingText;
    }
	
	private int checkIfSentenceIsATitle(String sentence) {

		int titleSize = 0;
		for(int i = 0; i < sentence.length(); i++) {
			if(sentence.charAt(i) == markup_Title1) {
				titleSize += 1;
			}else {
				break;
			}
		}
		
		return titleSize;
	}
	
	
	// FORMATTING MARKUP LANGUAGE IN SENTENCE //
	
	
	private TextFlow createUnformattedLine(String sentence) {
		Text t = new Text(sentence);
		t.setFont(Font.font(fontFamily_Normal, fontSize_Normal));
		TextFlow tf = new TextFlow(t);
		return tf;
	}
	
	private TextFlow createUnorderList(ArrayList<Markup_UnorderedListItem> listItems){
		TextFlow tf = new TextFlow();
		for(int i = 0; i < listItems.size(); i++) {
			tf.getChildren().addAll(listItems.get(i).getTItemFormatted());
		}
		return tf;
	}
	
	private TextFlow createCodeSnippet(ArrayList<String> codeSnippets) {
		return new Markup_CodeSnippet(codeSnippets).getTfCodeSnippet();
	}
	
	private TextFlow createQuote(String sentence) {
		return new Markup_Quote(sentence).getTfQuote();
	}
	
	private TextFlow createTitle(int titileValue, String sentence) {
		return new Markup_Title(titileValue, sentence).getTfTitle();
	}
	
	
	// GETTER METHODS //
	
	
	public ArrayList<TextFlow> getTextFlowPanes() {
		return textFlowContainer;
	}
}
