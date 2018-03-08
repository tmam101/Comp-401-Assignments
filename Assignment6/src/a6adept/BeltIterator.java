package a6adept;

import java.util.Iterator;

import comp401.sushi.Plate;

public class BeltIterator implements Iterator<Plate> {

	private Belt belt;
	private int start_position;

	public BeltIterator(Belt belt, int start_position) {
		this.belt = belt;
		this.start_position = start_position;
	}

	@Override
	public boolean hasNext() {
		boolean hasNext = false;
		// Plate[] beltArray = belt.getBeltArray().clone();
		// for (int i = 0; i < beltArray.length; i++) {
		// if(beltArray[i].hasContents())
		// }
		// return false;
		int size = belt.getSize();
		// unsure about this
		for (int i = start_position; i < size + start_position; i++) {
			if (belt.getPlateAtPosition(i) != null) {
				hasNext = true;
			}
		}
		return hasNext;
	}

	@Override
	public Plate next() {
		Plate next;
		belt.getPlateAtPosition()
		return null;
	}

	public void remove() {

	}

}
