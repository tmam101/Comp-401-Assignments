package a6novice;

import java.util.NoSuchElementException;

import comp401.sushi.Plate;

public class Belt {
	private int size;
	private static Plate[] beltArray;

	public Belt(int size) {
		this.size = size;
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		beltArray = new Plate[size];

	}

	// public Plate[] beltArray = new Plate[size];

	public int getSize() {
		return size;
	}

	public Plate getPlateAtPosition(int position) {
		if (position < 0 || position >= size) {
			throw new IllegalArgumentException();
		}
		return beltArray[position];
	}

	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (plate == null) {
			throw new IllegalArgumentException();
		}
		// or is it just size?
		if (position >= size || position < 0) {
			throw new IllegalArgumentException();
		}
		if (getPlateAtPosition(position) != null) {
			throw new BeltPlateException(position, plate, this);
		}
		beltArray[position] = plate;
	}

	public void clearPlateAtPosition(int position) {
		if (position >= size || position < 0) {
			throw new IllegalArgumentException();
		}
		beltArray[position] = null;
	}

	public Plate removePlateAtPosition(int position) {
		if (position >= size || position < 0) {
			throw new IllegalArgumentException();
		}
		if (getPlateAtPosition(position) == null) {
			throw new NoSuchElementException();
		}
		Plate removedPlate = beltArray[position];
		clearPlateAtPosition(position);
		return removedPlate;
	}

}
