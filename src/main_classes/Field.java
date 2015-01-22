package main_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Nick Veremeichyk
 * 
 */
public class Field {
	private List<Cell> cells;
	private List<Ship> ships;

	// constructor
	public Field() {
		cells = new ArrayList<Cell>(100);
		ships = new ArrayList<Ship>(10);

		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				cells.add(new Cell(i, j));
			}
		}

		loadShips();
	}

	public ArrayList<Cell> getCells() {
		return (ArrayList<Cell>) cells;
	}

	/***************************************************
	 * Random generation of the head of a Ship
	 * 
	 * @return Cell which achieves a head of the new ship
	 ***************************************************/
	private Cell randomHead() {
		Random r = new Random();
		Cell head = cells.get(0);
		boolean freeHead = false;
		while (!freeHead) {
			int x = r.nextInt(9) + 1;
			int y = r.nextInt(9) + 1;
			for (int i = 0; i < cells.size(); i++) {
				if (cells.get(i).getX() == x && cells.get(i).getY() == y
				        && !cells.get(i).gethasShip()
				        && !shipNearCell(cells.get(i))
				        && !cells.get(i).getNeighborShipCell()) {
					head = cells.get(i);
					freeHead = true;
				}
			}
		}
		return head;
	}

	/****************************************************
	 * 
	 * @param cell
	 * @return
	 ****************************************************/
	private boolean shipNearCell(Cell cell) {
		int x = cell.getX();
		int y = cell.getY();
		ArrayList<Cell> cellsNear = new ArrayList<Cell>(8);
		for (int j = 0; j < cells.size(); j++) {
			if (cells.get(j).getX() == x - 1 && cells.get(j).getY() == y - 1) {
				cellsNear.add(cells.get(j));
			} else if (cells.get(j).getX() == x && cells.get(j).getY() == y - 1) {
				cellsNear.add(cells.get(j));
			} else if (cells.get(j).getX() == x + 1
			        && cells.get(j).getY() == y - 1) {
				cellsNear.add(cells.get(j));
			} else if (cells.get(j).getX() == x + 1 && cells.get(j).getY() == y) {
				cellsNear.add(cells.get(j));
			} else if (cells.get(j).getX() == x + 1
			        && cells.get(j).getY() == y + 1) {
				cellsNear.add(cells.get(j));
			} else if (cells.get(j).getX() == x && cells.get(j).getY() == y + 1) {
				cellsNear.add(cells.get(j));
			} else if (cells.get(j).getX() == x - 1
			        && cells.get(j).getY() == y + 1) {
			} else if (cells.get(j).getX() == x - 1 && cells.get(j).getY() == y) {
				cellsNear.add(cells.get(j));
			}
		}
		for (Cell c : cellsNear) {
			if (c.gethasShip()) {
				return true;
			}
		}
		return false;
	}

	/**************************************************
	 * find a Cell with appropriate coordinate
	 * 
	 * @param x
	 * @param y
	 * @return
	 **************************************************/
	public Cell findCell(int x, int y) {
		Cell findedCell = cells.get(0);
		for (int i = 0; i < cells.size(); i++) {
			if (cells.get(i).getX() == x && cells.get(i).getY() == y)
				findedCell = cells.get(i);
		}
		return findedCell;
	}

	/*************************************************
	 * the method check the next Cell if it is free for a new Ship
	 * 
	 * @param head
	 *            a cell which is parent for the next cell
	 * @param direction
	 *            (from 1 to 4)
	 * @return true if a next Cell is free, or false if not
	 *************************************************/

	private boolean checkNextCell(Cell head, int direction) {
		boolean freeDirection = false;

		if (direction == 1
		        && !findCell(head.getX() + 1, head.getY()).gethasShip()
		        && !findCell(head.getX() + 1, head.getY())
		                .getNeighborShipCell() && head.getX() < 10) {
			freeDirection = true;
		} else if (direction == 2
		        && !findCell(head.getX(), head.getY() + 1).gethasShip()
		        && !findCell(head.getX(), head.getY() + 1)
		                .getNeighborShipCell() && head.getY() < 10) {
			freeDirection = true;
		} else if (direction == 3
		        && !findCell(head.getX() - 1, head.getY()).gethasShip()
		        && !findCell(head.getX() - 1, head.getY())
		                .getNeighborShipCell() && head.getX() > 1) {
			freeDirection = true;
		} else if (direction == 4
		        && !findCell(head.getX(), head.getY() - 1).gethasShip()
		        && !findCell(head.getX(), head.getY() - 1)
		                .getNeighborShipCell() && head.getY() > 1) {
			freeDirection = true;
		}
		return freeDirection;
	}

	private Ship loadShip(int size) {
		Ship ship = createShip(size);
		cellStatusChange(ship);
		return ship;
	}

	/***********************************************
	 * 
	 * @param size
	 *            of a new Ship
	 * @return ship
	 ***********************************************/
	private Ship createShip(int size) {
		boolean isCells = false;
		ArrayList<Cell> shipCells;
		while (!isCells) {
			Cell head = randomHead();
			// if ship has size 1
			if (size == 1) {
				shipCells = new ArrayList<Cell>(1);
				shipCells.add(head);
				isCells = true;

				return new Ship(shipCells);
			}
			if (size == 2) {
				shipCells = new ArrayList<Cell>(2);
				shipCells.add(head);

				for (int direction = 1; direction <= 4; direction++) {
					if (direction == 1 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX() + 1, head.getY()));
						// freeDirection = true;
						return new Ship(shipCells);
					} else if (direction == 2 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX(), head.getY() + 1));
						// freeDirection = true;
						return new Ship(shipCells);
					} else if (direction == 3 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX() - 1, head.getY()));
						// freeDirection = true;
						return new Ship(shipCells);
					} else if (direction == 4 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX(), head.getY() - 1));
						// freeDirection = true;
						return new Ship(shipCells);
					}
				}

			} else if (size == 3) {
				shipCells = new ArrayList<Cell>(3);
				shipCells.add(head);
				// якщо напрямок вправо і наступна клітинка доступна для
				// вставки ідемо сюди
				for (int direction = 1; direction <= 4; direction++) {
					if (direction == 1 && checkNextCell(head, direction)) {
						// перевіряємо чи наступна 3тя клітинка вільна, якщо
						// так, то додаємо обидві клітинки
						if (checkNextCell(
						        findCell(head.getX() + 1, head.getY()),
						        direction)) {
							shipCells
							        .add(findCell(head.getX() + 1, head.getY()));
							shipCells
							        .add(findCell(head.getX() + 2, head.getY()));
							// freeDirection = true;
							return new Ship(shipCells);
						}
					} else if (direction == 2 && checkNextCell(head, direction)) {

						if (checkNextCell(
						        findCell(head.getX(), head.getY() + 1),
						        direction)) {
							shipCells
							        .add(findCell(head.getX(), head.getY() + 1));
							shipCells
							        .add(findCell(head.getX(), head.getY() + 2));
							return new Ship(shipCells);
						}
					} else if (direction == 3 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX() - 1, head.getY()));
						if (checkNextCell(
						        findCell(head.getX() - 1, head.getY()),
						        direction)) {
							shipCells
							        .add(findCell(head.getX() - 1, head.getY()));
							shipCells
							        .add(findCell(head.getX() - 2, head.getY()));
							return new Ship(shipCells);
						}
					} else if (direction == 4 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX(), head.getY() - 1));
						if (checkNextCell(
						        findCell(head.getX(), head.getY() - 1),
						        direction)) {
							shipCells
							        .add(findCell(head.getX(), head.getY() - 1));
							shipCells
							        .add(findCell(head.getX(), head.getY() - 2));
							return new Ship(shipCells);
						}
					}
				}

			} else if (size == 4) {
				shipCells = new ArrayList<Cell>(4);
				shipCells.add(head);
				// якщо напрямок вправо і наступна клітинка доступна для
				// вставки ідемо сюди
				for (int direction = 1; direction <= 4; direction++) {
					if (direction == 1 && checkNextCell(head, direction)) {
						// перевіряємо чи наступна 3тя клітинка вільна, якщо
						// так, то додаємо обидві клітинки
						if (checkNextCell(
						        findCell(head.getX() + 1, head.getY()),
						        direction)) {
							if (checkNextCell(
							        findCell(head.getX() + 2, head.getY()),
							        direction)) {
								shipCells.add(findCell(head.getX() + 1,
								        head.getY()));
								shipCells.add(findCell(head.getX() + 2,
								        head.getY()));
								shipCells.add(findCell(head.getX() + 3,
								        head.getY()));
								// freeDirection = true;
								return new Ship(shipCells);
							}
						}
					} else if (direction == 2 && checkNextCell(head, direction)) {

						if (checkNextCell(
						        findCell(head.getX(), head.getY() + 1),
						        direction)) {
							if (checkNextCell(
							        findCell(head.getX(), head.getY() + 2),
							        direction)) {
								shipCells.add(findCell(head.getX(),
								        head.getY() + 1));
								shipCells.add(findCell(head.getX(),
								        head.getY() + 2));
								shipCells.add(findCell(head.getX(),
								        head.getY() + 3));
								return new Ship(shipCells);
							}
						}
					} else if (direction == 3 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX() - 1, head.getY()));
						if (checkNextCell(
						        findCell(head.getX() - 1, head.getY()),
						        direction)) {
							if (checkNextCell(
							        findCell(head.getX() - 2, head.getY()),
							        direction)) {
								shipCells.add(findCell(head.getX() - 1,
								        head.getY()));
								shipCells.add(findCell(head.getX() - 2,
								        head.getY()));
								shipCells.add(findCell(head.getX() - 3,
								        head.getY()));
								return new Ship(shipCells);
							}
						}
					} else if (direction == 4 && checkNextCell(head, direction)) {
						shipCells.add(findCell(head.getX(), head.getY() - 1));
						if (checkNextCell(
						        findCell(head.getX(), head.getY() - 1),
						        direction)) {
							if (checkNextCell(
							        findCell(head.getX(), head.getY() - 2),
							        direction)) {
								shipCells.add(findCell(head.getX(),
								        head.getY() - 1));
								shipCells.add(findCell(head.getX(),
								        head.getY() - 2));
								shipCells.add(findCell(head.getX(),
								        head.getY() - 3));
								return new Ship(shipCells);
							}
						}
					}
				}
			}
		}
		return null;
	}



	/*********************************************
	 * The method change "isNeihbourShipCell" of a Parameter to True, that is
	 * mean the neighbor cell is a ship
	 * 
	 * @param cell
	 *********************************************/
	private void cellStatusChange(Ship ship) {
		
		for (int i = 0; i < ship.getSizeOfShip(); i++) {
			Cell cellOfShip = ship.getCellsOfShip().get(i);
			cellOfShip.setHasShip();
			int x = cellOfShip.getX();
			int y = cellOfShip.getY();
			for (int j = 0; j < cells.size(); j++) {


				for (int xX = -1; xX <= 1; xX++) {
					for (int yY = -1; yY <= 1; yY++)
					if (cells.get(j).getX() == x + xX
					        && cells.get(j).getY() == y + yY)
						cells.get(j).setNeighborShipCell();
				}

//				if (cells.get(j).getX() == x - 1
//				        && cells.get(j).getY() == y - 1) {
//					cells.get(j).setNeighborShipCell();
//				} else if (cells.get(j).getX() == x
//				        && cells.get(j).getY() == y - 1) {
//					cells.get(j).setNeighborShipCell();
//				} else if (cells.get(j).getX() == x + 1
//				        && cells.get(j).getY() == y - 1) {
//					cells.get(j).setNeighborShipCell();
//				} else if (cells.get(j).getX() == x + 1
//				        && cells.get(j).getY() == y) {
//					cells.get(j).setNeighborShipCell();
//				} else if (cells.get(j).getX() == x + 1
//				        && cells.get(j).getY() == y + 1) {
//					cells.get(j).setNeighborShipCell();
//				} else if (cells.get(j).getX() == x
//				        && cells.get(j).getY() == y + 1) {
//					cells.get(j).setNeighborShipCell();
//				} else if (cells.get(j).getX() == x - 1
//				        && cells.get(j).getY() == y + 1) {
//					cells.get(j).setNeighborShipCell();
//				} else if (cells.get(j).getX() == x - 1
//				        && cells.get(j).getY() == y) {
//					cells.get(j).setNeighborShipCell();
//				}
			}
			// }

		}
	}

	/****************************************************
	 * Load all ships to the field (in ArrayList ships)
	 ****************************************************/
	void loadShips() {

		ships.add(loadShip(4));
		ships.add(loadShip(3));
		ships.add(loadShip(3));
		ships.add(loadShip(2));
		ships.add(loadShip(2));
		ships.add(loadShip(2));
		ships.add(loadShip(1));
		ships.add(loadShip(1));
		ships.add(loadShip(1));
		ships.add(loadShip(1));

	}

	public void drawField(boolean myField) {
		if (myField)
			System.out.println("My Field:");
		else
			System.out.println("Computer Field:");

		int count = 0;
		System.out.println("  x:1 2 3 4 5 6 7 8 9 10");
		System.out.print("y   | | | | | | | | | |");

		for (int i = 0; i < cells.size(); i++) {
			count++;
			if (count % 10 == 1) {
				System.out.println();
				System.out.print(i / 10 + " - ");
			}
			if (myField) {
				if (cells.get(i).gethasShip() && !cells.get(i).getHitted()) {
					System.out.print("* ");
				} else if (cells.get(i).getHitted()
				        && !cells.get(i).gethasShip()) {
					System.out.print("# ");
				} else if (cells.get(i).getHitted()
				        && cells.get(i).gethasShip()) {
					System.out.print("x ");

				} else {
					System.out.print("0 ");
				}
			} else {
				if (cells.get(i).getHitted() && !cells.get(i).gethasShip()) {
					System.out.print("# ");
				} else if (cells.get(i).getHitted()
				        && cells.get(i).gethasShip()) {
					System.out.print("x ");
				} else {
					System.out.print("0 ");
				}
			}
		}
		System.out.println();

	}

	/********************************************
	 * change status of the ships if some of them are dead
	 ********************************************/
	// public void changeShipsStatus() {
	// for (Ship s : ships) {
	// s.liveStatus();
	// }
	// }

	/********************************************
	 * change status of the ships if some of them are dead
	 * 
	 * @return if as one ship is live it returns true
	 ********************************************/
	public boolean areLiveShips() {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).isLive()) {
				return true;
			}
		}
		return false;
	}

	public int livedShips() {
		int livedShips = 10;
		for (int i = 0; i < ships.size(); i++) {
			if (!ships.get(i).isLive()) {
				livedShips--;
			}
		}
		return livedShips;

	}

	public boolean hasDeadAfterStep() {
		for (int i = 0; i < ships.size(); i++) {

		}
		return false;
	}

	void hitCellsAroundShip() {
		for (int i = 0; i < ships.size(); i++) {
			if (!ships.get(i).isLive()) {
				for (int j = 0; j < ships.get(i).getSizeOfShip(); j++) {
					int x = ships.get(i).getCellsOfShip().get(j).getX();
					int y = ships.get(i).getCellsOfShip().get(j).getY();
					for (int c = 0; c < cells.size(); c++) {
						if (cells.get(c).getX() == x - 1
						        && cells.get(c).getY() == y - 1) {
							cells.get(c).hitTheCell();
						} else if (cells.get(c).getX() == x
						        && cells.get(c).getY() == y - 1) {
							cells.get(c).hitTheCell();
						} else if (cells.get(c).getX() == x + 1
						        && cells.get(c).getY() == y - 1) {
							cells.get(c).hitTheCell();
						} else if (cells.get(c).getX() == x + 1
						        && cells.get(c).getY() == y) {
							cells.get(c).hitTheCell();
						} else if (cells.get(c).getX() == x + 1
						        && cells.get(c).getY() == y + 1) {
							cells.get(c).hitTheCell();
						} else if (cells.get(c).getX() == x
						        && cells.get(c).getY() == y + 1) {
							cells.get(c).hitTheCell();
						} else if (cells.get(c).getX() == x - 1
						        && cells.get(c).getY() == y + 1) {
							cells.get(c).hitTheCell();
						} else if (cells.get(c).getX() == x - 1
						        && cells.get(c).getY() == y) {
							cells.get(c).hitTheCell();
						}
					}
				}
			}
		}
	}

	/**************************************************
	 * Tested methods
	 **************************************************/
	public ArrayList<Ship> getShips() {
		return (ArrayList<Ship>) ships;
	}

	public void showAllCells() {
		for (Cell c : cells) {
			System.out.print(c.getX() + "," + c.getY() + " ");
		}
	}

}
