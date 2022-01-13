package com;

//mport javax.swing.UIManager;

import com.menu.MyFrame;
import com.players.Player;
import com.players.Computer;

public class MainGame {
	static MyFrame frame;
	static Player mainPlayer;
	static Computer enemyComputer;
	static boolean loopOnMain = true;
	static boolean loopOnLoop = true;
	static int result;
	static String resultString;
	static int scoreSave = 0;
	static int attempsSave = 0;

	public static void main(String[] args) {

		while (loopOnMain) {
			newGame();
			loop();

			frame.dispose();
		}

	}

	public static void newGame() {

		frame = new MyFrame("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		mainPlayer = new Player();
		enemyComputer = new Computer();
		loopOnLoop = true;
	}

	public static void loop() {

		while (loopOnLoop) {

			loopOnLoop = frame.tryAgain();
			frame.setScore(scoreSave);
			frame.setAttempts(attempsSave);

			if (frame.giveUp()) {

				scoreSave = 0;
				attempsSave = 0;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (!frame.getGuessed()) {

				continue;
			}

			mainPlayer.setNumberGuessed(frame.getNumber());
			result = enemyComputer.checkNumber(mainPlayer.getNumberGuessed());

			switch (result) {
			case 1:
				resultString = "Congratulations you got it right";
				frame.lockGuesses();

				break;
			case 2:
				resultString = "Too Low";
				break;
			case 3:
				resultString = "Too High";
				break;
			case 4:
				break;
			}

			System.out.println("REsult " + resultString);
			frame.setResult(resultString);
			enemyComputer.getNumber();
			mainPlayer.setIsNumber(enemyComputer.checkScore(mainPlayer.getNumberGuessed()));
			scoreSave += mainPlayer.getScore();
			attempsSave += mainPlayer.getAttempts();
			frame.setGuessed(false);

			frame.revalidate();
		}

	}

}
