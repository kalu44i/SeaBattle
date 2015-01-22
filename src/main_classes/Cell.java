package main_classes;

/**
 * The class describes a Cell of a field;
 * 
 * @author Nick Veremeichyk
 * 
 */
public class Cell {
	private int x; // x horizontal coordinate of a cell
	private int y; // y vertical coordinate of a cell
	private boolean isHitted; // achieves hit status of a cell
	private boolean hasShip; // achieves status about ship in a cell;
	private boolean isNeihbourShipCell; // achieves status about neihbour ship

	/**
	 * the constructor creates a Cell with x and y coordinate and status.
	 * 
	 * @param x
	 * @param y
	 */
	public Cell(int y, int x) {
		this.x = x;
		this.y = y;
		isHitted = false;
		hasShip = false;
		isNeihbourShipCell = false;
	}

	/****************************************
	 * Set true if the cell will be contain a ship
	 ****************************************/
	void setHasShip() {
		hasShip = true;
	}

	/****************************************
	 * Set true if the cell will be neihbour of a cell with a ship
	 ****************************************/
	void setNeighborShipCell() {
		isNeihbourShipCell = true;
	}

	/****************************************
	 * Set x coord
	 ****************************************/
	void setX(int xCoord) {
		x = xCoord;
	}

	/****************************************
	 * Set y coord
	 ****************************************/
	void setY(int yCoord) {
		y = yCoord;
	}

	/****************************************
	 * return x coord
	 ****************************************/
	public int getX() {
		return x;
	}

	/****************************************
	 * return x coord
	 ****************************************/
	public int getY() {
		return y;
	}

	/****************************************
	 * Return true if the cell contains a ship
	 ****************************************/
	public boolean gethasShip() {
		return hasShip;
	}

	/****************************************
	 * Return true if the cell is a neihbour of a cell with a ship
	 ****************************************/
	public boolean getNeighborShipCell() {
		return isNeihbourShipCell;
	}

	/****************************************
	 * change status of the cell to true if player hit it once
	 ****************************************/
	public void hitTheCell() {
		isHitted = true;
	}

	/****************************************
	 * Return true if player hit in the cell once
	 ****************************************/
	public boolean getHitted() {
		return isHitted;
	}
}
