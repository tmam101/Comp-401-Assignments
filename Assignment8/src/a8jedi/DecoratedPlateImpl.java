package a8jedi;

import comp401.sushi.Plate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.Sushi;

public class DecoratedPlateImpl implements DecoratedPlate {
	private Plate p;
	private int rotationCount;

	// A decorated plate contains a plate and adds functionality to track the
	// number of rotations amount the belt.
	public DecoratedPlateImpl(Plate p, int rotationCount) {
		this.p = p;
		this.rotationCount = rotationCount;
	}

	@Override
	public Sushi getContents() {
		return p.getContents();
	}

	@Override
	public void setContents(Sushi s) throws PlatePriceException {
		p.setContents(s);
	}

	@Override
	public Sushi removeContents() {
		return p.removeContents();
	}

	@Override
	public boolean hasContents() {
		return p.hasContents();
	}

	@Override
	public double getPrice() {
		return p.getPrice();
	}

	@Override
	public Color getColor() {
		return p.getColor();
	}

	@Override
	public double getProfit() {
		return p.getProfit();
	}

	public int getRotationCount() {
		return rotationCount;
	}

	public void incrementRotationCount() {
		rotationCount++;
	}

	public Plate getPlate() {
		return p;
	}

}
