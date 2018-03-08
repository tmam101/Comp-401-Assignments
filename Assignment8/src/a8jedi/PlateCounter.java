package a8jedi;

import java.util.Observable;
import java.util.Observer;

import comp401.sushi.Plate;

public class PlateCounter implements Observer {
	private Belt b;
	// These private ints will keep track of the number of each kind of plate on
	// the belt
	private int red_plate_count = 0;
	private int green_plate_count = 0;
	private int blue_plate_count = 0;
	private int gold_plate_count = 0;

	// Constructor
	public PlateCounter(Belt b) {
		this.b = b;
		// Throw an exception if the belt is null.
		if (b == (null)) {
			throw new IllegalArgumentException();
		} else {
			// This is an observer of the belt.
			b.addObserver(this);
		}
		// If the belt already has plates on it, get the plate counts up to
		// date.
		for (int i = 0; i < b.getSize(); i++) {
			if (b.getPlateAtPosition(i) != null) {
				if (b.getPlateAtPosition(i).getColor() == (Plate.Color.RED)) {
					red_plate_count++;
				} else if (b.getPlateAtPosition(i).getColor() == (Plate.Color.GREEN)) {
					green_plate_count++;
				} else if (b.getPlateAtPosition(i).getColor() == (Plate.Color.BLUE)) {
					blue_plate_count++;
				} else if (b.getPlateAtPosition(i).getColor() == (Plate.Color.GOLD)) {
					gold_plate_count++;
				}
			}
		}
	}

	@Override
	// Keep track of the number of plates of each color that are currently on
	// the belt.
	public void update(Observable o, Object arg) {
		PlateEvent event = (PlateEvent) arg;
		// If a plate is placed, find out the color of the plate and increment
		// the number of that color of plate.
		if (event.getType() == PlateEvent.EventType.PLATE_PLACED) {
			switch (event.getPlate().getColor()) {
			case RED:
				red_plate_count++;
				break;
			case GREEN:
				green_plate_count++;
				break;
			case BLUE:
				blue_plate_count++;
				break;
			case GOLD:
				gold_plate_count++;
				break;
			}
		} else {
			// If a plate is removed, decrement the number of that color of
			// plate
			if (event.getType() == PlateEvent.EventType.PLATE_REMOVED) {
				switch (event.getPlate().getColor()) {
				case RED:
					red_plate_count--;
					break;
				case GREEN:
					green_plate_count--;
					break;
				case BLUE:
					blue_plate_count--;
					break;
				case GOLD:
					gold_plate_count--;
					break;
				}
			}
		}
	}

	// Standard getters for the private values of the counts of the plates.
	public int getRedPlateCount() {
		return red_plate_count;
	}

	public int getGreenPlateCount() {
		return green_plate_count;
	}

	public int getBluePlateCount() {
		return blue_plate_count;
	}

	public int getGoldPlateCount() {
		return gold_plate_count;
	}

}
