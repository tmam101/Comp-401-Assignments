package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class LowToHighFoodSpoiledComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef o1, Chef o2) {
		return (int) (Math.round(o1.getAmountOfFoodSpoiled() * 100.0) - Math.round(o2.getAmountOfFoodSpoiled() * 100));
	}

}
