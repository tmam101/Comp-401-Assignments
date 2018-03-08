package sushigame.view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import comp401.sushi.IngredientPortion;

public class IngredientPane extends JScrollPane {
	private JTextArea textArea;

	public IngredientPane() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		add(textArea);
	}

	public void setTextArea(String text) {
		// textArea = null;
		textArea.setText(text);
		this.getViewport().add(textArea);
	}

	public void setTextArea(IngredientPortion[] a) {
		textArea.setText("");
		for (IngredientPortion p : a) {
			if (p != null) {
				this.addToTextArea(p.getName() + ":");
				this.addToTextArea(String.valueOf(p.getAmount()) + " ounces");
			}
		}
	}

	public void addToTextArea(String text) {
		textArea.append(text + "\n");
		// this.getViewport().add(textArea);
	}

}
