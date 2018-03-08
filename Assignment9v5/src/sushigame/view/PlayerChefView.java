package sushigame.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401.sushi.AvocadoPortion;
import comp401.sushi.CrabPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Plate;
import comp401.sushi.RicePortion;
import comp401.sushi.Roll;
import comp401.sushi.SalmonPortion;
import comp401.sushi.Sashimi;
import comp401.sushi.Sashimi.SashimiType;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.ShrimpPortion;
import comp401.sushi.Sushi;
import comp401.sushi.TunaPortion;

public class PlayerChefView extends JPanel implements ActionListener, ChangeListener {

	private List<ChefViewListener> listeners;
	private Sushi currentSushi;
	private int belt_size;
	private JComboBox sushiTypeBox, spotOnBeltBox, plateColorBox, ingredientBox;
	private String currentSushiType, currentPlateColor, currentIngredient;
	private String[] sushiTypeArray, ingredientArray;
	private Plate.Color[] plateColorArray;
	private JButton makeSushiButton;
	private JLabel sashimiOrNigiri, rollText, seaweedText, eelText, crabText, avocadoText, salmonText, shrimpText,
			tunaText, goldPlatePriceText;
	private double riceAmount, seaweedAmount, eelAmount, crabAmount, avocadoAmount, salmonAmount, shrimpAmount,
			tunaAmount, goldPlatePrice;
	private Hashtable labelTabel, goldPlatePriceTable;
	private JSlider seaweedSlider, riceSlider, eelSlider, crabSlider, avocadoSlider, salmonSlider, shrimpSlider,
			tunaSlider, goldPlatePriceSlider;
	private int currentSpotOnBelt, numberOfIngredientSlidersMoved;
	private IngredientPortion[] rollPortionArray;

	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Slider helper stuff
		labelTabel = new Hashtable();
		labelTabel.put(new Integer(0), new JLabel("0.0"));
		labelTabel.put(new Integer(5), new JLabel("0.5"));
		labelTabel.put(new Integer(10), new JLabel("1.0"));
		labelTabel.put(new Integer(15), new JLabel("1.5"));
		int min = 0;
		int max = 15;
		int init = 0;

		// Initialize values
		currentSushiType = "Nigiri";
		currentSpotOnBelt = 0;
		currentPlateColor = "blue";
		currentIngredient = "eel";
		currentSushi = new Nigiri(Nigiri.NigiriType.EEL);
		goldPlatePrice = 5.0;

		// Spot on belt Text
		JLabel spotOnBeltText = new JLabel("Spot On Belt");
		add(spotOnBeltText);

		// Spot on belt Box
		String[] spotOnBeltArray = new String[belt_size];
		for (int i = 0; i < belt_size; i++) {
			spotOnBeltArray[i] = String.valueOf(i + 1);
		}
		spotOnBeltBox = new JComboBox(spotOnBeltArray);
		spotOnBeltBox.addActionListener(this);
		add(spotOnBeltBox);

		// Plate color Text
		JLabel plateColorText = new JLabel("Plate Color");
		add(plateColorText);

		// Plate Color box
		plateColorArray = new Plate.Color[] { Plate.Color.BLUE, Plate.Color.GOLD, Plate.Color.GREEN, Plate.Color.RED };
		plateColorBox = new JComboBox(plateColorArray);
		plateColorBox.addActionListener(this);
		add(plateColorBox);

		// Gold Plate Price Text
		goldPlatePriceText = new JLabel("Gold Plate Price ($)");
		add(goldPlatePriceText);

		// Gold Plate Price Slider
		goldPlatePriceTable = new Hashtable();
		goldPlatePriceTable.put(new Integer(50), new JLabel("5.0"));
		goldPlatePriceTable.put(new Integer(60), new JLabel("6.0"));
		goldPlatePriceTable.put(new Integer(70), new JLabel("7.0"));
		goldPlatePriceTable.put(new Integer(80), new JLabel("8.0"));
		goldPlatePriceTable.put(new Integer(90), new JLabel("9.0"));
		goldPlatePriceTable.put(new Integer(100), new JLabel("10.0"));
		goldPlatePriceSlider = new JSlider(JSlider.HORIZONTAL, 50, 100, 50);
		goldPlatePriceSlider.setMajorTickSpacing(5);
		goldPlatePriceSlider.setMinorTickSpacing(1);
		goldPlatePriceSlider.setPaintTicks(true);
		goldPlatePriceSlider.setPaintLabels(true);
		goldPlatePriceSlider.setLabelTable(goldPlatePriceTable);
		goldPlatePriceSlider.addChangeListener(this);
		goldPlatePriceSlider.setName("gold plate price slider");
		add(goldPlatePriceSlider);

		// Sushi Type Text
		JLabel sushiTypeText = new JLabel("Sushi Type");
		add(sushiTypeText);

		// Sushi Type Box
		sushiTypeArray = new String[] { "Nigiri", "Sashimi", "Roll" };
		sushiTypeBox = new JComboBox(sushiTypeArray);
		sushiTypeBox.addActionListener(this);
		add(sushiTypeBox);

		// Sashimi Or Nigiri Ingredients Text
		sashimiOrNigiri = new JLabel("Sashimi or Nigiri Ingredient");
		add(sashimiOrNigiri);

		// Ingredient Box
		ingredientArray = new String[] { "eel", "crab", "salmon", "shrimp", "tuna" };
		ingredientBox = new JComboBox(ingredientArray);
		ingredientBox.addActionListener(this);
		add(ingredientBox);

		// Roll Ingredients Text
		rollText = new JLabel("Roll Ingredients");
		add(rollText);

		// Rice Text
		JLabel riceText = new JLabel("Rice Amount (oz)");
		add(riceText);

		// Rice Slider
		riceSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		riceSlider.setMajorTickSpacing(5);
		riceSlider.setMinorTickSpacing(1);
		riceSlider.setPaintTicks(true);
		riceSlider.setPaintLabels(true);
		riceSlider.setLabelTable(labelTabel);
		riceSlider.addChangeListener(this);
		riceSlider.setName("rice slider");
		add(riceSlider);

		// Seaweed Text
		seaweedText = new JLabel("Seaweed Amount (oz)");
		add(seaweedText);

		// Seaweed Slider
		seaweedSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		seaweedSlider.setMajorTickSpacing(5);
		seaweedSlider.setMinorTickSpacing(1);
		seaweedSlider.setPaintTicks(true);
		seaweedSlider.setPaintLabels(true);
		seaweedSlider.setLabelTable(labelTabel);
		seaweedSlider.addChangeListener(this);
		seaweedSlider.setName("seaweed slider");
		add(seaweedSlider);

		// Eel Text
		eelText = new JLabel("Eel Amount (oz)");
		add(eelText);

		// Eel Slider
		eelSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		eelSlider.setMajorTickSpacing(5);
		eelSlider.setMinorTickSpacing(1);
		eelSlider.setPaintTicks(true);
		eelSlider.setPaintLabels(true);
		eelSlider.setLabelTable(labelTabel);
		eelSlider.addChangeListener(this);
		eelSlider.setName("eel slider");
		add(eelSlider);

		// Crab Text
		crabText = new JLabel("Crab Amount (oz)");
		add(crabText);

		// Crab Slider
		crabSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		crabSlider.setMajorTickSpacing(5);
		crabSlider.setMinorTickSpacing(1);
		crabSlider.setPaintTicks(true);
		crabSlider.setPaintLabels(true);
		crabSlider.setLabelTable(labelTabel);
		crabSlider.addChangeListener(this);
		crabSlider.setName("crab slider");
		add(crabSlider);

		// Avocado Text
		avocadoText = new JLabel("Avocado Amount (oz)");
		add(avocadoText);

		// Avocado Slider
		avocadoSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		avocadoSlider.setMajorTickSpacing(5);
		avocadoSlider.setMinorTickSpacing(1);
		avocadoSlider.setPaintTicks(true);
		avocadoSlider.setPaintLabels(true);
		avocadoSlider.setLabelTable(labelTabel);
		avocadoSlider.addChangeListener(this);
		avocadoSlider.setName("avocado slider");
		add(avocadoSlider);

		// Salmon Text
		salmonText = new JLabel("Salmon Amount (oz)");
		add(salmonText);

		// Salmon Slider
		salmonSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		salmonSlider.setMajorTickSpacing(5);
		salmonSlider.setMinorTickSpacing(1);
		salmonSlider.setPaintTicks(true);
		salmonSlider.setPaintLabels(true);
		salmonSlider.setLabelTable(labelTabel);
		salmonSlider.addChangeListener(this);
		salmonSlider.setName("salmon slider");
		add(salmonSlider);

		// Shrimp Text
		shrimpText = new JLabel("Shrimp Amount (oz)");
		add(shrimpText);

		// Shrimp Slider
		shrimpSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		shrimpSlider.setMajorTickSpacing(5);
		shrimpSlider.setMinorTickSpacing(1);
		shrimpSlider.setPaintTicks(true);
		shrimpSlider.setPaintLabels(true);
		shrimpSlider.setLabelTable(labelTabel);
		shrimpSlider.addChangeListener(this);
		shrimpSlider.setName("shrimp slider");
		add(shrimpSlider);

		// Tuna Text
		tunaText = new JLabel("Tuna Amount (oz)");
		add(tunaText);

		// Tuna Slider
		tunaSlider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		tunaSlider.setMajorTickSpacing(5);
		tunaSlider.setMinorTickSpacing(1);
		tunaSlider.setPaintTicks(true);
		tunaSlider.setPaintLabels(true);
		tunaSlider.setLabelTable(labelTabel);
		tunaSlider.addChangeListener(this);
		tunaSlider.setName("tuna slider");
		add(tunaSlider);

		// Make Sushi Button
		makeSushiButton = new JButton("Make Sushi");
		makeSushiButton.addActionListener(this);
		makeSushiButton.setActionCommand("make sushi");
		add(makeSushiButton);

	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox box = (JComboBox) e.getSource();

			// Get ingredient
			for (int i = 0; i < ingredientArray.length; i++) {
				if (box.getSelectedItem().equals(ingredientArray[i])) {
					this.currentIngredient = ingredientArray[i];
				}
			}
			// Get current spot on belt
			for (int i = 0; i < belt_size; i++) {
				if (box.getSelectedItem().equals(String.valueOf(i + 1))) {
					currentSpotOnBelt = i;
				}
			}

			// Get current plate color
			for (Plate.Color c : plateColorArray) {
				if (box.getSelectedItem().equals(c)) {
					this.currentPlateColor = c.toString().toLowerCase();
				}
			}

			// Get current sushi type
			for (String s : sushiTypeArray) {
				if (box.getSelectedItem().equals(s)) {
					this.currentSushiType = s;
				}
			}

			// Determine the sushi
			// maybe this should happen in the beginning too
			if (this.currentSushiType.equals("Nigiri")) {
				switch (this.currentIngredient) {
				case "eel":
					currentSushi = new Nigiri(NigiriType.EEL);
					break;
				case "crab":
					currentSushi = new Nigiri(NigiriType.CRAB);
					break;
				case "salmon":
					currentSushi = new Nigiri(NigiriType.SALMON);
					break;
				case "shrimp":
					currentSushi = new Nigiri(NigiriType.SHRIMP);
					break;
				case "tuna":
					currentSushi = new Nigiri(NigiriType.TUNA);
					break;
				}
			} else if (this.currentSushiType.equals("Sashimi")) {
				switch (this.currentIngredient) {
				case "eel":
					currentSushi = new Sashimi(SashimiType.EEL);
					break;
				case "crab":
					currentSushi = new Sashimi(SashimiType.CRAB);
					break;
				case "salmon":
					currentSushi = new Sashimi(SashimiType.SALMON);
					break;
				case "shrimp":
					currentSushi = new Sashimi(SashimiType.SHRIMP);
					break;
				case "tuna":
					currentSushi = new Sashimi(SashimiType.TUNA);
					break;
				}
			} else if (this.currentSushiType.equals("Roll")) {
				determineRoll();
			}
		} else if (e.getSource() instanceof JButton) {
			determineRoll();
			switch (e.getActionCommand()) {
			case "make sushi":
				// need to check if this stuff is null or something
				switch (this.currentPlateColor) {
				case "red":
					if (currentSushi instanceof Roll) {
						IngredientPortion[] test = currentSushi.getIngredients();
						if (test.length != 0) {
							makeRedPlateRequest(currentSushi, currentSpotOnBelt);
						}
					} else {
						makeRedPlateRequest(currentSushi, currentSpotOnBelt);
					}
					break;
				case "gold":
					if (currentSushi instanceof Roll) {
						IngredientPortion[] test = currentSushi.getIngredients();
						if (test.length != 0) {
							makeGoldPlateRequest(currentSushi, currentSpotOnBelt, this.goldPlatePrice);
						}
					} else {
						makeGoldPlateRequest(currentSushi, currentSpotOnBelt, this.goldPlatePrice);
					}
					break;
				case "blue":
					if (currentSushi instanceof Roll) {
						IngredientPortion[] test = currentSushi.getIngredients();
						if (test.length != 0) {
							makeBluePlateRequest(currentSushi, currentSpotOnBelt);
						}
					} else {
						makeBluePlateRequest(currentSushi, currentSpotOnBelt);
					}
					break;
				case "green":
					if (currentSushi instanceof Roll) {
						IngredientPortion[] test = currentSushi.getIngredients();
						if (test.length != 0) {
							makeGreenPlateRequest(currentSushi, currentSpotOnBelt);
						}
					} else {
						makeGreenPlateRequest(currentSushi, currentSpotOnBelt);
					}
					break;
				}
				break;
			}
		}
	}

	@Override
	// For sliders
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();
		switch (slider.getName()) {
		case "seaweed slider":
			seaweedAmount = (double) slider.getValue() / 10;
			System.out.println("seaweed amount " + seaweedAmount);
			break;
		case "rice slider":
			riceAmount = (double) slider.getValue() / 10;
			System.out.println("rice amount " + riceAmount);
			break;
		case "eel slider":
			eelAmount = (double) slider.getValue() / 10;
			break;
		case "crab slider":
			crabAmount = (double) slider.getValue() / 10;
			break;
		case "avocado slider":
			avocadoAmount = (double) slider.getValue() / 10;
			break;
		case "salmon slider":
			salmonAmount = (double) slider.getValue() / 10;
			break;
		case "shrimp slider":
			shrimpAmount = (double) slider.getValue() / 10;
			break;
		case "tuna slider":
			tunaAmount = (double) slider.getValue() / 10;
			break;
		case "gold plate price slider":
			goldPlatePrice = (double) slider.getValue() / 10;
			break;
		}
		double[] sliderValueArray = new double[] { riceAmount, seaweedAmount, eelAmount, crabAmount, avocadoAmount,
				salmonAmount, shrimpAmount, tunaAmount };
		numberOfIngredientSlidersMoved = 0;
		for (double d : sliderValueArray) {
			if (d != 0.0) {
				numberOfIngredientSlidersMoved++;
			}
		}
		// try to have slider movements change info about roll array
		int rollIteration = 0;
		System.out.println("number of ingredient sliders moved " + numberOfIngredientSlidersMoved);
		System.out.println("roll iteration " + rollIteration);
		if (numberOfIngredientSlidersMoved != 0) {
			rollPortionArray = new IngredientPortion[this.numberOfIngredientSlidersMoved + 1];
			System.out.println("roll portion array length is " + rollPortionArray.length);
			if (riceAmount != 0) {
				System.out.println("rice is not 0");
				rollPortionArray[rollIteration] = new RicePortion(riceAmount);
				rollIteration++;
				System.out.println("roll iteration rice" + rollIteration);
			}
			if (seaweedAmount != 0) {
				rollPortionArray[rollIteration] = new SeaweedPortion(seaweedAmount);
				rollIteration++;
				System.out.println("seaweed iteration" + rollIteration);
			}
			if (eelAmount != 0) {
				rollPortionArray[rollIteration] = new EelPortion(eelAmount);
				rollIteration++;
			}
			if (crabAmount != 0) {
				rollPortionArray[rollIteration] = new CrabPortion(crabAmount);
				rollIteration++;
			}
			if (avocadoAmount != 0) {
				rollPortionArray[rollIteration] = new AvocadoPortion(avocadoAmount);
				rollIteration++;
			}
			if (salmonAmount != 0) {
				rollPortionArray[rollIteration] = new SalmonPortion(salmonAmount);
				rollIteration++;
			}
			if (shrimpAmount != 0) {
				rollPortionArray[rollIteration] = new ShrimpPortion(shrimpAmount);
				rollIteration++;
			}
			if (tunaAmount != 0) {
				rollPortionArray[rollIteration] = new TunaPortion(tunaAmount);
				rollIteration++;
			}
		}
	}
	// determineRoll();

	// I think all thats left is to deal with gold plates
	// No theres also a problem with having to select sashimi, nigiri, or
	// roll
	// also we are placing the belts one spot too forward due to issue with
	// starting with zero
	// it only places a roll after a slider is moved. we have to make the
	// roll combo box determine the roll.

	// Does it make a roll after moving a slider even if sashimi is still
	// selected?
	// Yes. fix!

	// Still having some sort of issue with placing plates at the right spot
	// on the belt.

	// Try to make it so that empty rolls cannot be placed. IM already
	// trying this on the red section of make sushi.

	public void determineRoll() {
		if (numberOfIngredientSlidersMoved != 0) {
			int x = 0;
			rollPortionArray = new IngredientPortion[this.numberOfIngredientSlidersMoved];
			if (riceAmount != 0) {
				System.out.println("rice is not 0");
				System.out.println("roll portion array length " + rollPortionArray.length);
				rollPortionArray[x] = new RicePortion(riceAmount);
			}
			if (seaweedAmount != 0) {
				rollPortionArray[x] = new SeaweedPortion(seaweedAmount);
				x++;
			}
			if (eelAmount != 0) {
				rollPortionArray[x] = new EelPortion(eelAmount);
				x++;
			}
			if (crabAmount != 0) {
				rollPortionArray[x] = new CrabPortion(crabAmount);
				x++;
			}
			if (avocadoAmount != 0) {
				rollPortionArray[x] = new AvocadoPortion(avocadoAmount);
				x++;
			}
			if (salmonAmount != 0) {
				rollPortionArray[x] = new SalmonPortion(salmonAmount);
				x++;
			}
			if (shrimpAmount != 0) {
				rollPortionArray[x] = new ShrimpPortion(shrimpAmount);
				x++;
			}
			if (tunaAmount != 0) {
				rollPortionArray[x] = new TunaPortion(tunaAmount);
				x++;
			}
			if (this.currentSushiType.equals("Roll")) {
				this.currentSushi = new Roll("player custom roll", rollPortionArray);
			}
		}
	}
}
