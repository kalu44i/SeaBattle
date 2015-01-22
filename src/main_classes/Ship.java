package main_classes;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	private List<Cell> cellsOfShip;
	private boolean isLive;

	public Ship(ArrayList<Cell> cellsOfShip) {
		this.cellsOfShip = cellsOfShip;
	}
	
	/**
	 * Check if the ship is live.
	 * @return
	 */
	public boolean isLive() {
		boolean isLive = false;
		for (int i = 0; i < cellsOfShip.size(); i++) {
			if (!cellsOfShip.get(i).getHitted()) {
				isLive = true;
			}
		}
		return isLive;
	}

	public boolean getLive() {
		return isLive;
	}

	public int getSizeOfShip() {
		return cellsOfShip.size();
	}

	public ArrayList<Cell> getCellsOfShip() {
		return (ArrayList<Cell>) cellsOfShip;

	}

}
