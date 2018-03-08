package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowFoodSoldComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef o1, Chef o2) {
		return (int) (Math.round(o2.getAmountOfFoodConsumed() * 100.0)
				- Math.round(o1.getAmountOfFoodConsumed() * 100));
	}

}
