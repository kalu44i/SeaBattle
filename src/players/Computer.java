package players;

import java.util.Random;

import main_classes.Cell;
import main_classes.Field;


public class Computer extends PlayerAbstract {
	
	public Computer(String name, Field field) {
	    super(name, field);
	    // TODO Auto-generated constructor stub
    }
	
	public boolean step() {
		boolean hit = false;
		Random r = new Random();
		Cell cell = field.findCell(0, 0);
		boolean notShutedCell = false;
		
		while (!notShutedCell) {
			int x = r.nextInt(9) + 1;
			int y = r.nextInt(9) + 1;
			cell = field.findCell(x, y);
			if (!cell.getHitted()) {
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
