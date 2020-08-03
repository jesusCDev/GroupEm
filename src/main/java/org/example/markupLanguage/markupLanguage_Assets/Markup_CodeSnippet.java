package org.example.markupLanguage.markupLanguage_Assets;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.example.markupLanguage.MarkUpLanguage_Syntax;

public class Markup_CodeSnippet implements MarkUpLanguage_Syntax{

	private ArrayList<String> codeSnippets = new ArrayList<>();
	private TextFlow tfCodeSnippet;
	
	public Markup_CodeSnippet(ArrayList<String> codeSnippets) {
		this.codeSnippets = codeSnippets;
		createTfCodeSnippet();
	}
	
	private void createTfCodeSnippet() {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < codeSnippets.size(); i++) {
			sb.append(codeSnippets.get(i));
			if((1 + i) < codeSnippets.size()) {
				sb.append("\n");
			}
		}
		Text textCodeSnippet = new Text(sb.toString());
		textCodeSnippet.setFont(Font.font(fontFamily_Code, fontSize_Normal));
		textCodeSnippet.setFill(Color.BLACK); 
		
		tfCodeSnippet = new TextFlow(textCodeSnippet);
		//tfCodeSnippet.setTextAlignment(TextAlignment.CENTER);
		tfCodeSnippet.setPadding(new Insets(10));
		tfCodeSnippet.setStyle("-fx-background-color: #E0E0E0;");
	}
	
	public TextFlow getTfCodeSnippet() {
		return tfCodeSnippet;
	}
}
