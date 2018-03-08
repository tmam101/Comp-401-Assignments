package sushigame.view;

import java.awt.Color;

import javax.swing.JPanel;

import comp401.sushi.IngredientPortion;
import sushigame.model.Belt;

public class PlateViewWidget extends JPanel {
	private Belt belt;

	public PlateViewWidget(Belt b) {
		this.belt = b;
	}

	public BeltPanel createBeltPanel(int beltNumber) {
		return new BeltPanel(Color.BLACK);
	}

	public void changePlateColor(BeltPanel p, Color color) {
		p.changePlateLabelColor(color);
	}

	public void changeNameLabelText(BeltPanel p, String text) {
		p.changeNameLabelText(text);
	}

	public void changeChefLabelText(BeltPanel p, String text) {
		p.changeChefLabelText(text);
	}

	public void changeAgeLabel(BeltPanel p, String text) {
		p.changeAgeLabel(text);
	}

	public void setPlateVisibility(BeltPanel p, boolean truth) {
		p.setPlateVisibility(truth);
	}

	public Belt getBelt() {
		return belt;
	}

	public void changeBeltPanelBackground(BeltPanel p, Color d) {
		p.setColor(d);
	}

	public void renderBeltPanelNull(BeltPanel p) {
		p.setPlateVisibility(false);
		p.changeAgeLabel("");
		p.changeChefLabelText("");
		p.changeNameLabelText("");
		p.changePlateLabelColor(Color.BLACK);
		p.setColor(Color.gray);
		p.setIngredientPane(" ");
	}

	public void addToIngredientPane(BeltPanel p, String string) {
		System.out.println("PlateViewWidget.addToIngredientPane");
		p.addToIngredientPane(string);
	}

	public void setIngredientPane(BeltPanel p, String string) {
		p.setIngredientPane(string);
	}

	public void setIngredientPane(BeltPanel p, IngredientPortion[] a) {
		p.setIngredientPane(a);
	}

}
