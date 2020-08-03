package org.example.markupLanguage.markupLanguage_Assets;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.example.markupLanguage.MarkUpLanguage_Syntax;

public class Markup_OrderedListItem implements MarkUpLanguage_Syntax{

	private String number;
	private String value;
	private TextFlow tfOrderedListItem;
	
	public Markup_OrderedListItem(String value) {
		number = value.substring(0, 2);
		this.value = value.substring(2);
		createTextFlow();
	}
	
	private void createTextFlow() {
		Text tNumber = new Text(number);
		tNumber.setFont(Font.font(fontFamily_Normal, FontWeight.BOLD ,fontSize_Normal));

		Markup_RichTextTrasnlator muR = new Markup_RichTextTrasnlator(value);
		
		tfOrderedListItem = new TextFlow(tNumber);
		tfOrderedListItem.getChildren().addAll(muR.getFormattedText());
	}
	
	public TextFlow getTfOrderedListItem() {
		return tfOrderedListItem;
	}
}
