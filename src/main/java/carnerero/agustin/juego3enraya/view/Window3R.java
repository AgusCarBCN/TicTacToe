package carnerero.agustin.juego3enraya.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import carnerero.agustin.juego3enraya.model.BoardGrid;

public class Window3R extends JFrame {

	private BoardGrid board;
	private JLabel player1, player2, empate, score1, score2, score3, message;
	private PanelGame panel3R;
	private JButton language,backGround;
	private final String SOL = "/carnerero/agustin/juego3enraya/resources/sun.png";
	private final String ENGLISH = "/carnerero/agustin/juego3enraya/resources/english.png";
	public Window3R() {

		setTitle("Juego tres en raya");
		setResizable(false);
		setBounds(425, 200, 400, 750);
		setBackground(Color.GRAY);
		panel3R = new PanelGame();
		add(panel3R);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public class PanelGame extends JPanel {

		public PanelGame() {
			setLayout(null);
			setBounds(0, 0, 400, 750);
			setBackground(Color.BLACK);
			setForeground(Color.YELLOW);
			Font font16 = new Font("Fira Code", Font.BOLD, 16);			
			Font font22 = new Font("Fira Code", Font.BOLD, 22);
			
			backGround = new JButton();		
			language = new JButton();			
			
			backGround.setBounds(25, 610, 32, 32);
			backGround.setForeground(Color.YELLOW);
			backGround.setBackground(Color.BLACK);
			backGround.setFocusable(false);
			backGround.setBorderPainted(false);			
            backGround.setIcon(new ImageIcon(getClass().getResource(SOL)));			
			
			language.setBounds(300, 610, 32, 32);
			language.setForeground(Color.YELLOW);
			language.setBackground(Color.BLACK);
			language.setFocusable(false);
			language.setBorderPainted(false);	
			language.setIcon(new ImageIcon(getClass().getResource(ENGLISH)));	
			player1 = new JLabel();
			player2 = new JLabel();
			empate = new JLabel();
			message = new JLabel();
			score1 = new JLabel();
			score2 = new JLabel();
			score3 = new JLabel();
			player1.setFont(font16);
			player1.setBounds(25, 420, 100, 100);
			player2.setFont(font16);
			player2.setBounds(270, 420, 100, 100);
			empate.setFont(font16);
			empate.setBounds(155, 420, 100, 100);
			score1.setFont(font22);
			score1.setBounds(55, 485, 60, 30);
			score2.setFont(font22);
			score2.setBounds(305, 485, 60, 30);
			score3.setFont(font22);
			score3.setBounds(185, 485, 60, 30);			
			message.setFont(font16);
			message.setBounds(100, 540, 220, 30);
			player1.setForeground(Color.YELLOW);
			player2.setForeground(Color.YELLOW);
			empate.setForeground(Color.YELLOW);
			message.setForeground(Color.YELLOW);
			score1.setForeground(Color.YELLOW);
			score2.setForeground(Color.YELLOW);
			score3.setForeground(Color.YELLOW);
			board = new BoardGrid();
			add(board.getBoardGrid());
			add(player1);
			add(empate);
			add(player2);
			add(score1);
			add(score2);
			add(score3);
			add(message);
			add(backGround);
			add(language);
			

		}
	}

	public JButton getLanguage() {
		return language;
	}

	public JButton getbackground() {
		return backGround;
	}

	public JLabel getScore1() {
		return score1;
	}

	public JLabel getScore2() {
		return score2;
	}

	public JLabel getScore3() {
		return score3;
	}

	public BoardGrid getBoard() {
		return board;
	}

	public JLabel getMessage() {
		return message;
	}

	public PanelGame getPanel3R() {
		return panel3R;
	}

	public JLabel getPlayer1() {
		return player1;
	}

	public JLabel getPlayer2() {
		return player2;
	}

	public JLabel getEmpate() {
		return empate;
	}

	

}