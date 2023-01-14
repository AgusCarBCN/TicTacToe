package carnerero.agustin.juego3enraya.model;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGrid extends JPanel {

	private JLabel boardGrid;
	private GridCell[][] gridCells;
	

	public BoardGrid() {
		// Create Board
		boardGrid = new JLabel("");
		gridCells = new GridCell[3][3];
		boardGrid.setBounds(0, 0, 400, 400);
		boardGrid.setBackground(Color.GREEN);
		boardGrid.setIcon(new ImageIcon(
				BoardGrid.class.getResource("/carnerero/agustin/juego3enraya/resources/tableroBlack.png")));
		// Create grid cells
		gridCells[0][0]=new GridCell(40,40);
		gridCells[0][1] = new GridCell(160, 40);
		gridCells[0][2] = new GridCell(285, 40);
		gridCells[1][0] = new GridCell(40, 160);
		gridCells[1][1] = new GridCell(160, 160);
		gridCells[1][2] = new GridCell(285, 160);
		gridCells[2][0] = new GridCell(40, 285);
		gridCells[2][1] = new GridCell(160, 285);
		gridCells[2][2] = new GridCell(285, 285);	
		

		// Fill Board
		fillBoard(gridCells);

	}

	public JLabel getBoardGrid() {
		return boardGrid;
	}

	private void fillBoard(GridCell[][] c) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				boardGrid.add(c[i][j].getGridCellLabel());
			}
		}
	}

	public void cleanBoard(GridCell[][] grid) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				grid[i][j].setMark(' ');
				grid[i][j].getGridCellLabel().setIcon(null);
			}
		}
	}

	public GridCell[][] getGridCells() {

		return gridCells;
	}

	public boolean isPlenty() {
		int elements = 0;
		boolean isPlenty = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gridCells[i][j].getMark() != ' ') {
					elements++;
				}
			}
		}
		if (elements == 9) {
			isPlenty = true;
		} else {
			isPlenty = false;
		}
		return isPlenty;
	}
	

	public boolean isWinner(GridCell[][] grid, String linea) {
		boolean winner = false;
		if (isWinnerH(grid, linea)) {
			winner = true;
		} else if (isWinnerV(grid, linea)) {
			winner = true;
		} else if (isWinnerD(grid, linea)) {
			winner = true;
		} else if (isWinnerD2(grid, linea)) {
			winner = true;
		}
		return winner;
	}

	

	private boolean isWinnerH(GridCell[][] grid, String linea) {
		boolean winner = false;
		String row = "";
		int i = 0;
		while (!winner && i < 3) {
			for (int j = 0; j < 3; j++) {
				row += grid[i][j].getMark();
			}
			if (row.equalsIgnoreCase(linea)) {
				winner = true;
			} else {
				row = "";
			}
			i++;
		}
		return winner;
	}

	private boolean isWinnerV(GridCell[][] grid, String linea) {
		boolean winner = false;

		String column = "";
		int i = 0;
		while (!winner && i < 3) {
			for (int j = 0; j < 3; j++) {
				column += grid[j][i].getMark();
			}
			if (column.equalsIgnoreCase(linea)) {
				winner = true;
			} else {
				column = "";
			}
			i++;
		}
		return winner;
	}

	private boolean isWinnerD(GridCell[][] grid, String linea) {
		boolean winner = false;

		String diagonal = "";
		for (int i = 0; i < 3; i++) {
			diagonal += grid[i][i].getMark();
		}
		if (diagonal.equalsIgnoreCase(linea)) {
			winner = true;
		}
		return winner;
	}

	private boolean isWinnerD2(GridCell[][] grid, String linea) {
		boolean winner = false;

		String diagonal = "";
		int j = 2;
		for (int i = 0; i < 3; i++) {
			diagonal += grid[j][i].getMark();
			j--;
		}
		if (diagonal.equalsIgnoreCase(linea)) {
			winner = true;
		}
		return winner;
	}

}
