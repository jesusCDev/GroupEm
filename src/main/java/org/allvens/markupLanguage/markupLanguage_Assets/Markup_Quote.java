package org.allvens.markupLanguage.markupLanguage_Assets;

import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.allvens.markupLanguage.MarkUpLanguage_Syntax;

public class Markup_Quote implements MarkUpLanguage_Syntax{

	private String value;
	private TextFlow tfQuote;
	
	public Markup_Quote(String value) {
		this.value = value.substring(1);
		createTextFlow();
	}
	
	private void createTextFlow() {
		Markup_RichTextTrasnlator muR = new Markup_RichTextTrasnlator(value);
		tfQuote = muR.getRichTextFlow();

		tfQuote.setTextAlignment(TextAlignment.CENTER);
		tfQuote.setPadding(new Insets(10));
		tfQuote.setStyle("-fx-background-color: #EEEEEE");
	}
	
	public TextFlow getTfQuote() {
		return tfQuote;
	}
}
