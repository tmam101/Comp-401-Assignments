package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

import comp401.sushi.Plate;

public class Belt implements Iterable<Plate> {
	private static Plate[] beltArray;
	private int size;

	public Belt(int size) {
		this.size = size;
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		beltArray = new Plate[size];
	}

	// public Plate[] getBeltArray() {
	// return this.beltArray;
	// }

	public int getSize() {
		return size;
	}

	public Plate getPlateAtPosition(int position) {
		int newPosition = 0;
		if (position >= 0) {
			newPosition = (position % size);
		} else if (position < 0) {
			newPosition = (position % size + size);
		}
		return beltArray[newPosition];
	}

	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (position >= 0) {
			position = position % size;
		} else if (position < 0) {
			position = position % size + size;
		}
		if (plate == null) {
			throw new IllegalArgumentException();
		}
		if (getPlateAtPosition(position) != null) {
			throw new BeltPlateException(position, plate, this);
		}
		beltArray[position] = plate;
	}

	public void clearPlateAtPosition(int position) {
		if (position >= 0) {
			position = position % size;
		} else if (position < 0) {
			position = position % size + size;
		}
		beltArray[position] = null;
	}

	public Plate removePlateAtPosition(int position) {
		if (position >= 0) {
			position = position % size;
		} else if (position < 0) {
			position = position % size + size;
		}
		if (getPlateAtPosition(position) == null) {
			throw new NoSuchElementException();
		}
		Plate removedPlate = beltArray[position];
		clearPlateAtPosition(position);
		return removedPlate;
	}

	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException, BeltPlateException {
		int finalPosition = 0;
		for (int i = 0; i < beltArray.length; i++) {
			if (getPlateAtPosition(position) == null) {
				setPlateAtPosition(plate, position);
				finalPosition = position;
			} else if (position == size - 1) {
				throw new BeltFullException(this);
			} else {
				setPlateNearestToPosition(plate, position + 1);
			}
		}
		return finalPosition;
	}

	public Iterator<Plate> iterator() {
		return new BeltIterator(this, 0);
	}

	public Iterator<Plate> iteratorFromPosition(int position) {
		return new BeltIterator(this, position);
	}

	public void rotate() {

	}

}
