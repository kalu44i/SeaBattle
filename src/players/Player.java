package players;

import java.util.Scanner;

import main_classes.Cell;
import main_classes.Field;

public class Player extends PlayerAbstract {

	public Player(String name, Field field) {
		super(name, field);
		// TODO Auto-generated constructor stub
	}

	/*****************************************
	 * 
	 * @param field
	 * @param x
	 * @param y
	 * @return true if a player hit ship, false if not
	 *****************************************/

	public boolean step() {
		boolean hit = false;
		Cell cell = field.findCell(0, 0);
		
		Scanner in = new Scanner(System.in);
		
		boolean notShutedCell = false;

		while (!notShutedCell) {
			System.out.print("Put X coordinate: ");
			int x = in.nextInt();
			System.out.print("Put Y coordinate: ");
			int y = in.nextInt();

			cell = field.findCell(x, y);
			if (cell.getHitted()) {
				System.out
				        .println("You've already shutted by this Cell. Repeat you step.");
			} else {
				notShutedCell = true;
			}
		}

		if (cell.gethasShip()) {
			hit = true;
		} else {
			hit = false;
		}
		cell.hitTheCell();
		hittedCells.add(cell);
		return hit;
	}
}