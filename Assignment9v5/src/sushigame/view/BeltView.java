package sushigame.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.Plate;
import comp401.sushi.Roll;
import comp401.sushi.Sashimi;
import comp401.sushi.Sushi;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private BeltPanel[] beltPanels;
	PlateViewWidget plateViewWidget;

	String sushiType = "";
	String sushiName = "";
	String chefName = "";
	int plateAge = 0;
	IngredientPortion[] portionArray;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		plateViewWidget = new PlateViewWidget(belt);
		setLayout(new GridLayout(belt.getSize(), 1));
		beltPanels = new BeltPanel[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			BeltPanel panel = plateViewWidget.createBeltPanel(i);
			// no panel has anything on it when constructed.
			add(panel);
			beltPanels[i] = panel;
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		refresh();
	}

	private void refresh() {
		for (int i = 0; i < belt.getSize(); i++) {
			portionArray = null;
			Plate p = belt.getPlateAtPosition(i);
			BeltPanel panel = beltPanels[i];

			if (p == null) {
				plateViewWidget.renderBeltPanelNull(panel);
			} else {
				Plate plate = belt.getPlateAtPosition(i);
				Plate.Color plateColor = plate.getColor();
				Sushi sushi = plate.getContents();

				if (sushi instanceof Nigiri) {
					sushiType = "Nigiri";
					Nigiri nigiri = (Nigiri) sushi;
					sushiName = nigiri.getName();
				} else if (sushi instanceof Sashimi) {
					sushiType = "Sashimi";
					Sashimi sashimi = (Sashimi) sushi;
					sushiName = sashimi.getName();
				} else if (sushi instanceof Roll) {
					sushiType = "Roll";
					Roll roll = (Roll) sushi;
					sushiName = roll.getName();
					if (roll.getIngredients() != null) {
						portionArray = roll.getIngredients();
					}
				}
				chefName = plate.getChef().getName();
				plateAge = belt.getAgeOfPlateAtPosition(i);

				switch (p.getColor()) {
				case RED:
					plateViewWidget.changePlateColor(panel, Color.RED);
					plateViewWidget.changeBeltPanelBackground(panel, Color.PINK);
					updateLabels(panel);
					break;
				case GREEN:
					plateViewWidget.changePlateColor(panel, Color.GREEN);
					plateViewWidget.changeBeltPanelBackground(panel, new Color(0, 255, 180));
					updateLabels(panel);
					break;
				case BLUE:
					plateViewWidget.changePlateColor(panel, Color.BLUE);
					plateViewWidget.changeBeltPanelBackground(panel, Color.CYAN);
					updateLabels(panel);
					break;
				case GOLD:
					plateViewWidget.changePlateColor(panel, Color.ORANGE);
					plateViewWidget.changeBeltPanelBackground(panel, Color.YELLOW);
					updateLabels(panel);
					break;
				}
			}
		}
	}

	public void updateLabels(BeltPanel p) {
		if (portionArray != null) {
			plateViewWidget.setIngredientPane(p, portionArray);
		} else {
			plateViewWidget.setIngredientPane(p, "");
		}
		plateViewWidget.setPlateVisibility(p, true);
		plateViewWidget.changeNameLabelText(p, sushiName);
		plateViewWidget.changeChefLabelText(p, chefName);
		plateViewWidget.changeAgeLabel(p, "Age " + String.valueOf(plateAge));
	}
}
