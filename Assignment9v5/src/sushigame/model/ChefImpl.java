package sushigame.model;

import java.util.ArrayList;
import java.util.List;

import comp401.sushi.IngredientPortion;
import comp401.sushi.Plate;

public class ChefImpl implements Chef, BeltObserver {

	private double balance;
	private List<HistoricalPlate> plate_history;
	private String name;
	private ChefsBelt belt;
	private boolean already_placed_this_rotation;
	private int numberOfPlatesConsumed, numberOfPlatesSpoiled;
	private double amountOfFoodConsumed, amountOfFoodSpoiled;

	public ChefImpl(String name, double starting_balance, ChefsBelt belt) {
		this.name = name;
		this.balance = starting_balance;
		this.belt = belt;
		belt.registerBeltObserver(this);
		already_placed_this_rotation = false;
		plate_history = new ArrayList<HistoricalPlate>();
		numberOfPlatesConsumed = 0;
		numberOfPlatesSpoiled = 0;
		amountOfFoodConsumed = 0.0;
		amountOfFoodSpoiled = 0.0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String n) {
		this.name = n;
	}

	@Override
	public HistoricalPlate[] getPlateHistory(int history_length) {
		if (history_length < 1 || (plate_history.size() == 0)) {
			return new HistoricalPlate[0];
		}

		if (history_length > plate_history.size()) {
			history_length = plate_history.size();
		}
		return plate_history.subList(plate_history.size() - history_length, plate_history.size() - 1)
				.toArray(new HistoricalPlate[history_length]);
	}

	@Override
	public HistoricalPlate[] getPlateHistory() {
		return getPlateHistory(plate_history.size());
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void makeAndPlacePlate(Plate plate, int position)
			throws InsufficientBalanceException, BeltFullException, AlreadyPlacedThisRotationException {

		if (already_placed_this_rotation) {
			throw new AlreadyPlacedThisRotationException();
		}

		if (plate.getContents().getCost() > balance) {
			throw new InsufficientBalanceException();
		}
		belt.setPlateNearestToPosition(plate, position);
		balance = balance - plate.getContents().getCost();
		already_placed_this_rotation = true;
	}

	public int getNumberOfPlatesConsumed() {
		return this.numberOfPlatesConsumed;
	}

	public double getAmountOfFoodConsumed() {
		return this.amountOfFoodConsumed;
	}

	public double getAmountOfFoodSpoiled() {
		return this.amountOfFoodSpoiled;
	}

	public int getNumberOfPlatesSpoiled() {
		return this.numberOfPlatesSpoiled;
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.PLATE_CONSUMED) {
			Plate plate = ((PlateEvent) e).getPlate();
			if (plate.getChef() == this) {
				IngredientPortion[] a = plate.getContents().getIngredients();
				for (IngredientPortion p : a) {
					amountOfFoodConsumed += p.getAmount();
				}
				balance += plate.getPrice();
				Customer consumer = belt.getCustomerAtPosition(((PlateEvent) e).getPosition());
				plate_history.add(new HistoricalPlateImpl(plate, consumer));
			}
		} else if (e.getType() == BeltEvent.EventType.PLATE_SPOILED) {
			Plate plate = ((PlateEvent) e).getPlate();
			if (plate.getChef() == this) {
				IngredientPortion[] a = plate.getContents().getIngredients();
				for (IngredientPortion p : a) {
					amountOfFoodSpoiled += p.getAmount();
				}
				plate_history.add(new HistoricalPlateImpl(plate, null));
			}
		} else if (e.getType() == BeltEvent.EventType.ROTATE) {
			already_placed_this_rotation = false;
		}
	}

	@Override
	public boolean alreadyPlacedThisRotation() {
		return already_placed_this_rotation;
	}
}
