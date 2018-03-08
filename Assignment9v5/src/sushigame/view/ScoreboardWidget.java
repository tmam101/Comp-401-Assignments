package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JButton balanceButton, foodSoldButton, foodSpoiledButton;
	private JPanel panel;
	private String actionCommand;

	public ScoreboardWidget(SushiGameModel gm) {
		actionCommand = "balance";
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);

		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);

		display.setText(makeScoreboardHTML());

		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		add(panel, BorderLayout.SOUTH);

		balanceButton = new JButton("Sort By Balance Order");
		balanceButton.setActionCommand("balance");
		balanceButton.addActionListener(this);
		panel.add(balanceButton);

		foodSoldButton = new JButton("Sort By Food Sold (oz)");
		foodSoldButton.setActionCommand("sold");
		foodSoldButton.addActionListener(this);
		panel.add(foodSoldButton);

		foodSpoiledButton = new JButton("Sort By Food Spoiled (oz)");
		foodSpoiledButton.setActionCommand("spoiled");
		foodSpoiledButton.addActionListener(this);
		panel.add(foodSpoiledButton);

	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs = game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length + 1];
		chefs[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefs.length; i++) {
			chefs[i] = opponent_chefs[i - 1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());

		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance() * 100.0) / 100.0 + ") <br>";
		}
		return sb_html;
	}

	public String makeScoreboardFoodSold() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs = game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length + 1];
		chefs[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefs.length; i++) {
			chefs[i] = opponent_chefs[i - 1];
		}
		Arrays.sort(chefs, new HighToLowFoodSoldComparator());

		for (Chef c : chefs) {
			sb_html += c.getName() + " Food Sold: " + c.getAmountOfFoodConsumed() + " <br>";
		}
		return sb_html;
	}

	public String makeScoreboardFoodSpoiled() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs = game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length + 1];
		chefs[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefs.length; i++) {
			chefs[i] = opponent_chefs[i - 1];
		}
		Arrays.sort(chefs, new LowToHighFoodSpoiledComparator());

		for (Chef c : chefs) {
			sb_html += c.getName() + " Food Spoiled: " + c.getAmountOfFoodSpoiled() + " <br>";
		}
		return sb_html;
	}

	public void refresh() {
		switch (actionCommand) {
		case "balance":
			display.setText(makeScoreboardHTML());
			break;
		case "sold":
			display.setText(this.makeScoreboardFoodSold());
			break;
		case "spoiled":
			display.setText(this.makeScoreboardFoodSpoiled());
		}
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionCommand = e.getActionCommand();
		refresh();
		// JButton button = (JButton) e.getSource();
		// switch (button.getActionCommand()) {
		// case "balance":
		//// display.setText(this.makeScoreboardHTML());
		// actionCommand = "balance";
		// break;
		// case "spoiled":
		// actionCommand = "spoiled";
		// display.setText(this.makeScoreboardFoodSpoiled());
		// break;
		// case "sold":
		// display.setText(makeScoreboardFoodSold());
		// actionCommand = "sold";
		// break;
		// default:
		// System.out.println("broke");
		// }
	}

}
