package a8jedi;

import java.util.Observable;
import java.util.Observer;

public class ProfitCounter implements Observer {
	// Private double to keep track of the total profit of all of the plates on
	// the belt.
	private double profit = 0;
	private Belt b;

	// Constructor
	public ProfitCounter(Belt b) {
		this.b = b;
		// Throw an exception if the belt is null.
		if (b == (null)) {
			throw new IllegalArgumentException();
		} else {
			// This is an observer of the belt.
			b.addObserver(this);
		}
		// If there are any plates at any position on the belt already, get our
		// profit up to date with the profits from the plates on the belt
		for (int i = 0; i < b.getSize(); i++) {
			if (b.getPlateAtPosition(i) != null) {
				profit = profit + b.getPlateAtPosition(i).getProfit();
			}
		}
	}

	@Override
	// Keep track of the profit of each plate that is on the belt.
	public void update(Observable o, Object arg) {
		PlateEvent event = (PlateEvent) arg;
		// If a plate is placed, find out the profit of the plate and add
		// the profit of that plate to the total profit.
		if (event.getType() == PlateEvent.EventType.PLATE_PLACED) {
			double tempProfit = (event.getPlate().getProfit());
			profit = profit + tempProfit;
			// If a plate is removed, find the profit of that plate and remove
			// it from the total profit.
		} else if (event.getType() == PlateEvent.EventType.PLATE_REMOVED) {
			double tempProfit = (event.getPlate().getProfit());
			profit = profit - tempProfit;
		}
	}

	// Return the total profit of all plates on the belt.
	public double getTotalBeltProfit() {
		return profit;
	}

	// Returns the average profit of the belt. Total profit divided by number of
	// plates on the belt.
	public double getAverageBeltProfit() {
		int plateCount = 0;
		for (int i = 0; i < b.getSize(); i++) {
			if (b.getPlateAtPosition(i) != null) {
				plateCount++;
			}
		}
		double averageTotalProfit = 0.0;
		if (plateCount != 0) {
			averageTotalProfit = profit / plateCount;
		} else {
			averageTotalProfit = 0.0;
		}
		return averageTotalProfit;
	}

}
