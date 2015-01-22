package main_classes;


import java.util.Scanner;

import players.Computer;
import players.Player;
import players.PlayerAbstract;

public class Game {
	
	public void whoIsAWinner(int userShips, int computerShips) {
		if (computerShips == 0 && userShips > 0) {
			System.out.println("Winner is YOU");
		} else if (computerShips > 0 && userShips == 0) {
			System.out.println("Winner is Computer");
		} else if (computerShips == 0 && userShips == 0) {
			System.out.println("No winners");
		}
	}
	

	public static void main(String[] args) {
		Game game = new Game();

		Field myField = new Field();
		Field computerField = new Field();

		PlayerAbstract user = new Player("Nick", computerField);
		PlayerAbstract computer = new Computer("Computer", myField);
		
		myField.drawField(true);
		computerField.drawField(false);

		boolean you = true;
		String message = "";
		
		while (myField.areLiveShips() && computerField.areLiveShips()) {
			myField.drawField(true);
			computerField.drawField(false);
			System.out.println(message);
			if (you) {
				System.out
				        .println("It is your step. Choose the cell for shutting");
				int shipsOnComputerField = computerField.livedShips(); //кораблей перед вистрелом;
				boolean step = user.step();
				if (step && computerField.livedShips() == shipsOnComputerField)
					message = "You hit a ship!";
				else if (step && computerField.livedShips() < shipsOnComputerField) {
					computerField.hitCellsAroundShip();
					message = "You hit a ship And kill it"; }
				else {
					message = "You do not hit! The computer shuts next.";
					you = false;
				}

			} else {
				int shipsOnMyField = myField.livedShips(); // перед вистрелом
				boolean step = computer.step();
				if (step && myField.livedShips() == shipsOnMyField)
					message = "Computer hit your ship! He shut again";
				else if (step && myField.livedShips() < shipsOnMyField) {
					myField.hitCellsAroundShip();
					message = "You hit a ship And kill it";
				}
				else {
					message = "Computer isn't hit!";
					you = true;
				}

			}
			game.whoIsAWinner(myField.livedShips(), computerField.livedShips());

		}
		System.out.println("The game is over!");
	}
}