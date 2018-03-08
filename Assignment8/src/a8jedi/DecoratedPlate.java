package a8jedi;

import comp401.sushi.Plate;

public interface DecoratedPlate extends Plate {

	int getRotationCount();

	void incrementRotationCount();

	Plate getPlate();

}
