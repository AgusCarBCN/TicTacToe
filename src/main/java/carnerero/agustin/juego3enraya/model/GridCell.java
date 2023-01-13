package carnerero.agustin.juego3enraya.model;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridCell extends JPanel {

	private final int DIMENSION_WIDTH = 100;
	private final int DIMENSION_HEIGTH = 100;
	private JLabel gridCellLabel;
	private char mark;
	private boolean marked;
	private int posX;
	private int posY;

	public GridCell(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		mark = ' ';
		gridCellLabel = new JLabel("");
		gridCellLabel.setBackground(Color.WHITE);
		gridCellLabel.setBounds(this.posX, this.posY, DIMENSION_WIDTH, DIMENSION_HEIGTH);
	}

	public boolean isEmpty() {
		if (mark == ' ') {
			this.marked = true;
		} else {
			this.marked = false;
		}
		return this.marked;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public char getMark() {
		return mark;
	}

	public JLabel getGridCellLabel() {
		return gridCellLabel;
	}

	public void setMark(char mark) {
		this.mark = mark;
	}

}
