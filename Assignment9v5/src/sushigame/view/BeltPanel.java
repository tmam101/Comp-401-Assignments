package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401.sushi.IngredientPortion;
import sushigame.model.Belt;

public class BeltPanel extends JPanel {

	private Color color;
	private Belt belt;
	private int beltNum;

	private JLabel nameLabel;
	private PlateLabel plateLabel;
	private JLabel chefLabel;
	private JLabel ageLabel;
	private IngredientPane ingredientPane;

	public BeltPanel(Color color) {
		// this.color = color;
		// this.belt = belt;
		// this.beltNum = beltNum;

		// Belt Panel stats
		setBackground(Color.GRAY);
		setMinimumSize(new Dimension(700, 60));
		setPreferredSize(new Dimension(700, 60));
		setOpaque(true);

		// Set layout and add panels
		setLayout(new GridLayout(1, 5));
		nameLabel = new JLabel("");
		plateLabel = new PlateLabel(Color.BLACK);
		plateLabel.setVisible(false);
		chefLabel = new JLabel("");
		ageLabel = new JLabel("");
		ingredientPane = new IngredientPane();
		ingredientPane.getViewport().setBackground(Color.lightGray);
		add(nameLabel);
		add(plateLabel);
		add(chefLabel);
		add(ageLabel);
		add(ingredientPane);
	}

	public void changePlateLabelColor(Color c) {
		plateLabel.changeColor(c);
	}

	public void changeNameLabelText(String string) {
		nameLabel.setText(string);
	}

	public void changeChefLabelText(String string) {
		chefLabel.setText(string);
	}

	public void changeAgeLabel(String string) {
		ageLabel.setText(string);
	}

	public void setColor(Color d) {
		setBackground(d);
	}

	public void addToIngredientPane(String s) {
		System.out.println("BeltPanel.addToIngredientPane");
		ingredientPane.addToTextArea(s);
	}

	public void setIngredientPane(String s) {
		ingredientPane.setTextArea(s);
	}

	public void setIngredientPane(IngredientPortion[] a) {
		ingredientPane.setTextArea(a);
	}

	public void setPlateVisibility(boolean truth) {
		plateLabel.setIsVisible(truth);
	}
}
