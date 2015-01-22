package players;

import java.util.ArrayList;

import main_classes.Cell;
import main_classes.Field;
import main_classes.Ship;

public abstract class PlayerAbstract {
	String name;
	ArrayList<Cell> hittedCells;
	Field field;
	
	public PlayerAbstract(String name, Field field) {
		this.name = name;
		hittedCells = new ArrayList<Cell>(); // achives Cells which is shutted
		                                     // by Player
		this.field = field;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean step() {
		return true;
	}
	
	/**
	 * Гравець після кожного пострілу перевіряє чи є убиті кораблі, якщо так, то
	 * він замальовує сусідні клітвнки як підбиті, щоб орієнтуватися в наступних
	 * потрілах
	 */
	public void isDeadShips() {
		for (int i = 0; i < field.getShips().size(); i++) {
			Ship ship = field.getShips().get(i);
			if (!ship.getLive()) {
				for (int size = 0; size < ship.getSizeOfShip(); size++) {
					Cell cell = ship.getCellsOfShip().get(size);
					int x = cell.getX();
					int y = cell.getY();
					ArrayList<Cell> cells = field.getCells();
					for (int j = 0; j < cells.size(); j++) {
						if (cell.getX() == x - 1
						        && cells.get(j).getY() == y - 1) {
							cells.get(j).hitTheCell();
						} else if (cells.get(j).getX() == x
						        && cells.get(j).getY() == y - 1) {
							cells.get(j).hitTheCell();
						} else if (cells.get(j).getX() == x + 1
						        && cells.get(j).getY() == y - 1) {
							cells.get(j).hitTheCell();
						} else if (cells.get(j).getX() == x + 1
						        && cells.get(j).getY() == y) {
							cells.get(j).hitTheCell();
						} else if (cells.get(j).getX() == x + 1
						        && cells.get(j).getY() == y + 1) {
							cells.get(j).hitTheCell();
						} else if (cells.get(j).getX() == x
						        && cells.get(j).getY() == y + 1) {
							cells.get(j).hitTheCell();
						} else if (cells.get(j).getX() == x - 1
						        && cells.get(j).getY() == y + 1) {
							cells.get(j).hitTheCell();
						} else if (cells.get(j).getX() == x - 1
						        && cells.get(j).getY() == y) {
							cells.get(j).hitTheCell();
						}
					}
				}
			}
		}
	}

}
