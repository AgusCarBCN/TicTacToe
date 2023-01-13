package carnerero.agustin.juego3enraya.controller;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import carnerero.agustin.juego3enraya.model.APlayer;
import carnerero.agustin.juego3enraya.model.BoardGrid;
import carnerero.agustin.juego3enraya.model.GridCell;
import carnerero.agustin.juego3enraya.model.Machine;
import carnerero.agustin.juego3enraya.model.Mark;
import carnerero.agustin.juego3enraya.model.Player;
import carnerero.agustin.juego3enraya.view.Window3R;

public class ControllerPlayer extends MouseAdapter implements ActionListener {

	private Window3R window3R;
	private Player player1, player2;
	private Machine machine;
	private BoardGrid board;
	private GridCell[][] gridCells;
	private final String LINEA_X = "XXX";
	private final String LINEA_O = "OOO";
	private final String MARK_XY = "/carnerero/agustin/juego3enraya/resources/cruzYellow.png";
	private final String MARK_OY = "/carnerero/agustin/juego3enraya/resources/Oyellow.png";
	private final String MARK_XB = "/carnerero/agustin/juego3enraya/resources/cruz.png";
	private final String MARK_OB = "/carnerero/agustin/juego3enraya/resources/O.png";
	private final String XMOVE = "../Juego3EnRaya/src/main/java/carnerero/agustin/juego3enraya/resources/XMove.wav";
	private final String OMOVE = "../Juego3EnRaya/src/main/java/carnerero/agustin/juego3enraya/resources/OMove.wav";
	private final String GAME_WIN = "../Juego3EnRaya/src/main/java/carnerero/agustin/juego3enraya/resources/win.wav";
	private final String VOICE_WIN = "../Juego3EnRaya/src/main/java/carnerero/agustin/juego3enraya/resources/voz.wav";
	private final String GAME_TIED = "../Juego3EnRaya/src/main/java/carnerero/agustin/juego3enraya/resources/tiedGame.wav";
	private final String INTRO = "../Juego3EnRaya/src/main/java/carnerero/agustin/juego3enraya/resources/exit.wav";
	private String MARK_X;
	private String MARK_O;
	private static ControllerPlayer controller;
	private int score1, score2, empates;
	private boolean fondoOscuro = true;
	private boolean spanish = true;
	private String turnX, turnO, winX, winO, tied, dark, light, playerX, playerO, tiedM, backGroundM, languageM,
			playAgain;
	private Color color1, color2;

	private ControllerPlayer(Window3R window3R, Player player1, Player player2) {

		this.window3R = window3R;
		this.player1 = player1;
		this.player2 = player2;
		this.player1.setPlay(true);
		this.player2.setPlay(false);
		this.player1.setIdPlayer(1);
		this.player2.setIdPlayer(2);
		initController();

	}

	private ControllerPlayer(Window3R window3R, Machine machine, Player player2) {

		this.player2 = player2;
		this.machine = machine;
		this.window3R = window3R;
		player2.setPlay(false);
		machine.setPlay(true);
		this.machine.setIdPlayer(1);
		this.player2.setIdPlayer(2);
		
		initController();

	}

	private ControllerPlayer(Window3R window3R, Player player1, Machine machine) {

		this.player1 = player1;
		this.machine = machine;
		this.window3R = window3R;
		player1.setPlay(true);
		machine.setPlay(false);
		this.player1.setIdPlayer(1);
		this.machine.setIdPlayer(2);
		initController();

	}

	public static ControllerPlayer getInstance(Window3R window3R, Player player1, Player player2) {
		if (controller == null) {
			controller = new ControllerPlayer(window3R, player1, player2);
		}
		return controller;
	}

	public static ControllerPlayer getInstance(Window3R window3R, Machine machine, Player player2) {
		if (controller == null) {
			controller = new ControllerPlayer(window3R, machine, player2);
		}
		return controller;
	}

	public static ControllerPlayer getInstance(Window3R window3R, Player player1, Machine machine) {
		if (controller == null) {
			controller = new ControllerPlayer(window3R, player1, machine);
		}
		return controller;
	}

	private void initController() {

		window3R.getMessage().setText("Turno de jugador(X)");
		board = window3R.getBoard();
		gridCells = board.getGridCells();
		addListener(gridCells);
		window3R.getbackground().addActionListener(this);
		window3R.getLanguage().addActionListener(this);
		window3R.getComputer().addActionListener(this);
		MARK_X = MARK_XY;
		MARK_O = MARK_OY;
		this.score1 = 0;
		this.score2 = 0;
		this.empates = 0;
		turnX = "Turno del jugador(X)";
		turnO = "Turno del jugador(O)";
		winX = "Gana el jugador(X)";
		winO = "Gana el jugador(O)";
		tied = " Partida empatada";
		dark = "Oscuro";
		light = "Claro";
		playerX = "Jugador(X)";
		playerO = "Jugador(O)";
		tiedM = "Empates";
		playAgain = "¿Jugar otra vez?";
		color1 = Color.BLACK;
		color2 = Color.YELLOW;
		window3R.getScore1().setText("0");
		window3R.getScore2().setText("0");
		window3R.getScore3().setText("0");
		window3R.getPlayer1().setText(playerX);
		window3R.getPlayer2().setText(playerO);
		window3R.getMessage().setText(turnX);
		window3R.getPlayer1().setText(playerX);
		window3R.getPlayer2().setText(playerO);
		window3R.getEmpate().setText(tiedM);
		playSound(INTRO);

	}

	private void addListener(GridCell[][] gridCells) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gridCells[i][j].isEmpty()) {
					gridCells[i][j].getGridCellLabel().addMouseListener(this);
				}
			}
		}
	}

	private void removeListener(GridCell[][] gridCells) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gridCells[i][j].getGridCellLabel().removeMouseListener(this);
			}
		}
	}

	private void playAgain(String message, APlayer player1, APlayer player2) {
		UIManager.put("OptionPane.background", color1);
		UIManager.put("Panel.background", color2);
		int res = JOptionPane.showConfirmDialog(window3R, message, "", JOptionPane.YES_NO_OPTION);
		switch (res) {
		case JOptionPane.YES_OPTION:
			board.cleanBoard(gridCells);
			player1.setWinner(false);
			player2.setWinner(false);
			addListener(gridCells);
			break;
		case JOptionPane.NO_OPTION:
			System.exit(res);			
			break;
		}
	}

	public void playSound(String path) {
		File f = new File(path);
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void changeTurn(APlayer player1, APlayer player2,String turn) {		
			player1.setPlay(false);
			player2.setPlay(true);		
			window3R.getMessage().setText(turn);	
	}

	

	@Override
	public void mouseReleased(MouseEvent e) {

		if (player1 != null && player2 != null && player1.isPlay() && !player1.isWinner() && !player2.isWinner()) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (e.getSource() == gridCells[i][j].getGridCellLabel() && gridCells[i][j].isEmpty()) {
						player1.markCell(gridCells[i][j], new Mark(MARK_X, 'X'));
						changeTurn(player1, player2,turnO);
						playSound(XMOVE);
					}
				}
			}
			checkBoard(player1, player2, LINEA_X, turnO, winX,GAME_WIN);

		} else if (player1 != null && player2 != null && player2.isPlay() && !player1.isWinner()
				&& !player2.isWinner()) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (e.getSource() == gridCells[i][j].getGridCellLabel() && gridCells[i][j].isEmpty()) {
						player2.markCell(gridCells[i][j], new Mark(MARK_O, 'O'));
						changeTurn( player2, player1,turnX);
						playSound(OMOVE);
					}
				}
			}
			checkBoard(player2, player1, LINEA_O, turnX, winO,GAME_WIN);

		} else if (player1 == null && player2.isPlay() && !machine.isWinner() && !player2.isWinner()) {

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (e.getSource() == gridCells[i][j].getGridCellLabel() && gridCells[i][j].isEmpty()) {
						player2.markCell(gridCells[i][j], new Mark(MARK_O, 'O'));
						changeTurn(player2, machine,turnX);
						playSound(OMOVE);
					}
				}
			}
			checkBoard(player2, machine, LINEA_O, turnX, winO,GAME_WIN);

		} else if (player2 == null && player1.isPlay() && !player1.isWinner() && !machine.isWinner())

		{
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (e.getSource() == gridCells[i][j].getGridCellLabel() && gridCells[i][j].isEmpty()) {
						player1.markCell(gridCells[i][j], new Mark(MARK_X, 'X'));
						changeTurn(player1, machine,turnO);
						playSound(XMOVE);
					}
				}
			}
			checkBoard(player1, machine, LINEA_X, turnO, winX,GAME_WIN);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == window3R.getbackground() && !fondoOscuro) {
			MARK_X = MARK_XY;
			MARK_O = MARK_OY;
			languageM = window3R.getLanguage().getText();
			window3R.getPlayer1().setForeground(Color.YELLOW);
			window3R.getPlayer2().setForeground(Color.YELLOW);
			window3R.getEmpate().setForeground(Color.YELLOW);
			window3R.getScore1().setForeground(Color.YELLOW);
			window3R.getScore2().setForeground(Color.YELLOW);
			window3R.getScore3().setForeground(Color.YELLOW);
			window3R.getMessage().setForeground(Color.YELLOW);
			window3R.getPanel3R().setBackground(Color.BLACK);
			window3R.getComputer().setBackground(Color.BLACK);
			window3R.getComputer().setForeground(Color.YELLOW);
			color1 = Color.BLACK;
			color2 = Color.YELLOW;

			board.getBoardGrid().setIcon(new ImageIcon(
					BoardGrid.class.getResource("/carnerero/agustin/juego3enraya/resources/tableroBlack.png")));
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (gridCells[i][j].getMark() == 'X') {
						gridCells[i][j].getGridCellLabel().setIcon(new ImageIcon(BoardGrid.class
								.getResource("/carnerero/agustin/juego3enraya/resources/cruzYellow.png")));
					} else if (gridCells[i][j].getMark() == 'O') {
						gridCells[i][j].getGridCellLabel().setIcon(new ImageIcon(
								BoardGrid.class.getResource("/carnerero/agustin/juego3enraya/resources/Oyellow.png")));
					}
				}

			}
			window3R.getbackground().setForeground(Color.YELLOW);
			window3R.getbackground().setBackground(Color.BLACK);
			window3R.getLanguage().setForeground(Color.YELLOW);
			window3R.getLanguage().setBackground(Color.BLACK);
			window3R.getbackground().setText(light);
			window3R.getLanguage().setText(languageM);
			fondoOscuro = true;
		} else if (e.getSource() == window3R.getbackground() && fondoOscuro) {
			MARK_X = MARK_XB;
			MARK_O = MARK_OB;
			languageM = window3R.getLanguage().getText();
			window3R.getPlayer1().setForeground(Color.BLACK);
			window3R.getPlayer2().setForeground(Color.BLACK);
			window3R.getEmpate().setForeground(Color.BLACK);
			window3R.getScore1().setForeground(Color.BLACK);
			window3R.getScore2().setForeground(Color.BLACK);
			window3R.getScore3().setForeground(Color.BLACK);
			window3R.getPanel3R().setBackground(Color.WHITE);
			window3R.getMessage().setForeground(Color.BLACK);
			color1 = Color.WHITE;
			color2 = Color.WHITE;
			board.getBoardGrid().setIcon(new ImageIcon(
					BoardGrid.class.getResource("/carnerero/agustin/juego3enraya/resources/tablero.jpg")));
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (gridCells[i][j].getMark() == 'X') {
						gridCells[i][j].getGridCellLabel().setIcon(new ImageIcon(
								BoardGrid.class.getResource("/carnerero/agustin/juego3enraya/resources/cruz.png")));
					} else if (gridCells[i][j].getMark() == 'O') {
						gridCells[i][j].getGridCellLabel().setIcon(new ImageIcon(
								BoardGrid.class.getResource("/carnerero/agustin/juego3enraya/resources/O.png")));
					}
				}
			}
			window3R.getComputer().setBackground(Color.WHITE);
			window3R.getComputer().setForeground(Color.BLACK);
			window3R.getbackground().setForeground(Color.BLACK);
			window3R.getbackground().setBackground(Color.WHITE);
			window3R.getLanguage().setForeground(Color.BLACK);
			window3R.getLanguage().setBackground(Color.WHITE);
			window3R.getbackground().setText(dark);
			window3R.getLanguage().setText(languageM);
			fondoOscuro = false;

		} else if (e.getSource() == window3R.getLanguage() && spanish) {

			turnX = "Player(X)'s turn";
			turnO = "Player(O)'s turn ";
			winX = "Player(X) wins";
			winO = "Player(O) wins";
			tied = "    Tied game";
			dark = "Dark";
			light = "Light";
			playerX = "Player(X)";
			playerO = "Player(O)";
			tiedM = " Draws";
			playAgain = "Play again?";

			backGroundM = window3R.getbackground().getText();
			window3R.getLanguage().setText("Español");
			window3R.getPlayer1().setText(playerX);
			window3R.getPlayer2().setText(playerO);
			window3R.getEmpate().setText(tiedM);
			window3R.getMessage().setText(turnX);
			window3R.getMessage().setText(turnO);

			if (backGroundM.equals("Oscuro")) {
				window3R.getbackground().setText(dark);
			} else if (backGroundM.equals("Claro")) {
				window3R.getbackground().setText(light);
			}
			spanish = false;

		} else if (e.getSource() == window3R.getLanguage() && !spanish) {
			turnX = "Turno del jugador(X)";
			turnO = "Turno del jugador(O)";
			winX = "Gana el jugador(X)";
			winO = "Gana el jugador(O)";
			tied = " Partida empatada";
			dark = "Oscuro";
			light = "Claro";
			playerX = "Jugador(X)";
			playerO = "Jugador(O)";
			tiedM = "Empates";
			playAgain = "¿Jugar otra vez?";

			backGroundM = window3R.getbackground().getText();
			window3R.getLanguage().setText("English");
			window3R.getPlayer1().setText(playerX);
			window3R.getPlayer2().setText(playerO);
			window3R.getEmpate().setText(tiedM);
			window3R.getMessage().setText(turnX);
			window3R.getMessage().setText(turnO);

			if (backGroundM.equals("Dark")) {
				window3R.getbackground().setText(dark);
			} else if (backGroundM.equals("Light")) {
				window3R.getbackground().setText(light);
			}
			spanish = true;

		} else if (e.getSource() == window3R.getComputer() && player1 == null && machine.isPlay() && !player2.isWinner()
				&& !machine.isWinner()) {
			machine.markCell(machine.machinePlaysMax(board), new Mark(MARK_X, 'X'));
			playSound(XMOVE);
			changeTurn(machine,player2,turnO);
			checkBoard(machine, player2, LINEA_X, turnO, winX,GAME_WIN);

		} else if (e.getSource() == window3R.getComputer() && player2 == null && machine.isPlay() && !player1.isWinner()
				&& !machine.isWinner()) {
			machine.markCell(machine.machinePlaysMin(board), new Mark(MARK_O, 'O'));		
			playSound(OMOVE);
			changeTurn(machine,player1,turnX);			
			checkBoard(machine, player1, LINEA_O, turnX, winO,GAME_WIN);
		}
	}

	private void checkBoard(APlayer player1, APlayer player2, String mark, String turn, String win,String sound) {
		if (!board.isPlenty()) {
			player1.setWinner(board.isWinner(gridCells, mark));
			if (player1.isWinner()) {
				
				removeListener(gridCells);
				if(player1.getIdPlayer()==1) {
					score1++;
				window3R.getScore1().setText(Integer.toString(score1));
				}else if(player1.getIdPlayer()==2) 
				{	score2++;
					window3R.getScore2().setText(Integer.toString(score2));
				}
				window3R.getMessage().setText(win);
				playSound(sound);
				playAgain(playAgain, player1, player2);
				window3R.getMessage().setText(turn);

			}
		} else if (board.isPlenty()) {
			player1.setWinner(board.isWinner(gridCells, mark));
			if (player1.isWinner()) {				
				removeListener(gridCells);
				if(player1.getIdPlayer()==1) {
					score1++;
					window3R.getScore1().setText(Integer.toString(score1));
					}else if(player1.getIdPlayer()==2) {
						score2++;
						window3R.getScore2().setText(Integer.toString(score2));
					}
				window3R.getMessage().setText(win);
				playSound(sound);
				playAgain(playAgain, player1, player2);
				window3R.getMessage().setText(turn);
			} else {
				empates++;
				window3R.getScore3().setText(Integer.toString(empates));
				window3R.getMessage().setText(tied);
				playSound(GAME_TIED);
				removeListener(gridCells);
				playAgain(playAgain, player1, player2);
				window3R.getMessage().setText(turn);
			}
		}
	}

}
