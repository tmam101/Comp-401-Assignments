package a7jedi;

import java.util.NoSuchElementException;
import java.util.Observable;

import comp401.sushi.Plate;

//The Belt is observable, in this case it is observed by a plate counter and a profit counter.  
public class Belt extends Observable {

	private Plate[] belt;
	private Customer[] customers;

	public Belt(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Belt size must be greater than zero.");
		}
		customers = new Customer[size];
		belt = new Plate[size];
	}

	public int getSize() {
		return belt.length;
	}

	public Plate getPlateAtPosition(int position) {
		position = normalizePosition(position);
		return belt[position];
	}

	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		position = normalizePosition(position);

		if (plate == null) {
			throw new IllegalArgumentException("Plate is null");
		}

		if (belt[position] != null) {
			throw new BeltPlateException(position, plate, this);
		}
		belt[position] = plate;
		// Notify observers that a plate has been placed.
		setChanged();
		notifyObservers(new PlateEvent(PlateEvent.EventType.PLATE_PLACED, plate, position));
	}

	public void clearPlateAtPosition(int position) {
		position = normalizePosition(position);
		Plate temp = belt[position];
		belt[position] = null;
		// Notify observers that a plate has been removed.
		setChanged();
		notifyObservers(new PlateEvent(PlateEvent.EventType.PLATE_REMOVED, temp, position));
	}

	public Plate removePlateAtPosition(int position) {
		Plate plate = getPlateAtPosition(position);
		if (plate == null) {
			throw new NoSuchElementException();
		}
		clearPlateAtPosition(position);
		return plate;
	}

	public void registerCustomerAtPosition(Customer c, int position) {
		// The value of position should be normalized to act in accordance with
		// the belt
		position = normalizePosition(position);
		// Throw an IllegalArgumentException if the Customer object provided as
		// c is null.
		if (c == null) {
			throw new IllegalArgumentException();
		}
		// Throw a runtime exception if the Customer is already
		// registered at a different position.
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] == c) {
				throw new RuntimeException();
			}
		}
		// Throw a runtime exception if the position is already registered with
		// a different customer object.
		if (customers[position] != null) {
			throw new RuntimeException();
		}
		// Assign the customer to the position.
		else {
			customers[position] = c;
		}
	}

	// Unregister the Customer at the specified position and return a reference
	// to that customer. Return null if there is no customer registered at that
	// position.
	public Customer unregisterCustomerAtPosition(int position) {
		// The value of position should be normalized to act in accordance with
		// the belt
		position = normalizePosition(position);
		if (customers[position] != null) {
			Customer tempCustomer = null;
			tempCustomer = customers[position];
			customers[position] = null;
			return tempCustomer;
		} else {
			return null;
		}
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
		Plate last_plate = belt[getSize() - 1];
		for (int i = getSize() - 1; i > 0; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = last_plate;
		// After the rotation, alert the customers to the change.
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] != null) {
				customers[i].observePlateOnBelt(this, belt[i], i);
			}
		}
	}

}
