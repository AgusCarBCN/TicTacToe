package carnerero.agustin.juego3enraya;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import carnerero.agustin.juego3enraya.controller.ControllerPlayer;
import carnerero.agustin.juego3enraya.model.Machine;
import carnerero.agustin.juego3enraya.model.Mark;
import carnerero.agustin.juego3enraya.model.Player;
import carnerero.agustin.juego3enraya.view.Window3R;


/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		String[] options = { "Human vs Machine", "Human vs Human" };
		ControllerPlayer controller;
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.YELLOW);
		Player player1 = null;
		Player player2 = null;
		Machine machine = null;
		boolean isPlenty = false;
		final String MARK_XY = "/carnerero/agustin/juego3enraya/resources/cruzYellow.png";
		final String MARK_SOUND = "../Juego3EnRaya/src/main/java/carnerero/agustin/juego3enraya/resources/markInBoard.wav";
		UIManager.put("text", Color.YELLOW);

		Window3R window3R = new Window3R();
		Object opcion = JOptionPane.showInputDialog(window3R, "Opciones de juego", "Escoje una opcion",
				JOptionPane.PLAIN_MESSAGE, null, options, null);
		if (opcion == "Human vs Human") {
			player1 = new Player(1);
			player2 = new Player(2);
			controller = ControllerPlayer.getInstance(window3R, player1, player2);
		} else if (opcion == "Human vs Machine") {
			player2 = new Player(2);
			machine = new Machine(window3R.getBoard());

			controller = ControllerPlayer.getInstance2(window3R, player2, machine);

		}
	}

}