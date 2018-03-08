package a8jedi;

import java.util.NoSuchElementException;
import java.util.Observable;

import comp401.sushi.Plate;

// The Belt is observable, in this case it is observed by a plate counter and a profit counter.  
public class Belt extends Observable {

	// The belt now accepts Decorated Plates rather than Plates. This adds
	// functionality to track number of rotations around the belt. The Decorated
	// Plates can be unwrapped when necessary.
	private DecoratedPlate belt[];

	public Belt(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Belt size must be greater than zero.");
		}
		belt = new DecoratedPlate[size];
	}

	public int getSize() {
		return belt.length;
	}

	public Plate getPlateAtPosition(int position) {
		position = normalizePosition(position);
		if (belt[position] == null) {
			return null;
		}
		if (belt[position].getPlate() == null) {
			return null;
		}
		// Unwrap plate here to return a plate rather than a decorated plate
		return belt[position].getPlate();
	}

	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		position = normalizePosition(position);
		if (plate == null) {
			throw new IllegalArgumentException("Plate is null");
		}
		if (belt[position] != null) {
			throw new BeltPlateException(position, plate, this);
		}
		DecoratedPlateImpl dPlate = new DecoratedPlateImpl(plate, 0);
		belt[position] = dPlate;
		// Notify observers that a plate has been placed.
		setChanged();
		notifyObservers(new PlateEvent(PlateEvent.EventType.PLATE_PLACED, plate, position));
	}

	public void clearPlateAtPosition(int position) {
		position = normalizePosition(position);
		if (belt[position] != null) {
			Plate temp = belt[position].getPlate();
			belt[position] = null;
			setChanged();
			notifyObservers(new PlateEvent(PlateEvent.EventType.PLATE_REMOVED, temp, position));
		}
	}

	public Plate removePlateAtPosition(int position) {
		Plate plate = getPlateAtPosition(position);
		if (plate == null) {
			throw new NoSuchElementException();
		}
		clearPlateAtPosition(position);
		return plate;

	}

	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {
		for (int i = 0; i < getSize(); i++) {
			try {
				setPlateAtPosition(plate, position);
				return normalizePosition(position);
			} catch (BeltPlateException e) {
				position += 1;
			}
		}
		throw new BeltFullException(this);
	}

	private int normalizePosition(int position) {
		int normalized_position = position % getSize();
		if (position < 0) {
			normalized_position += getSize();
		}
		return normalized_position;
	}

	public void rotate() {
		DecoratedPlate last_plate = belt[getSize() - 1];
		for (int i = getSize() - 1; i > 0; i--) {
			belt[i] = belt[i - 1];
			if (belt[i] != null) {
				belt[i].incrementRotationCount();
			}
		}
		belt[0] = last_plate;
		if (last_plate != null) {
			last_plate.incrementRotationCount();
		}
		// Spoilage check at the end of every rotation
		for (int i = 0; i < getSize(); i++) {
			if (belt[i] != null) {
				// Check to make sure the plate has contents.
				if (belt[i].hasContents()) {
					// Shellfish check
					if (belt[i].getContents().getHasShellfish()) {
						if (belt[i].getRotationCount() >= getSize()) {
							setChanged();
							notifyObservers(new PlateEvent(PlateEvent.EventType.PLATE_SPOILED, belt[i].getPlate(), i));
						}
					}
					// Vegetarian sushi
					else if (belt[i].getContents().getIsVegetarian() == true) {
						if (belt[i].getRotationCount() >= (getSize() * 3)) {
							setChanged();
							notifyObservers(new PlateEvent(PlateEvent.EventType.PLATE_SPOILED, belt[i].getPlate(), i));
						}
					}
					// Does not contain shellfish but is vegetarian
					else if (!belt[i].getContents().getHasShellfish()) {
						if (!belt[i].getContents().getIsVegetarian()) {
							if (belt[i].getRotationCount() >= (getSize() * 2)) {
								setChanged();
								notifyObservers(
										new PlateEvent(PlateEvent.EventType.PLATE_SPOILED, belt[i].getPlate(), i));
							}
						}
					}
				}
			}
		}
	}

	// Utilize the decorated plates' new functionality to get the age of the
	// plate
	public int getAgeOfPlateAtPosition(int position) {
		position = normalizePosition(position);
		if (belt[position] == null) {
			return -1;
		} else {
			return belt[position].getRotationCount();
		}
	}
}
