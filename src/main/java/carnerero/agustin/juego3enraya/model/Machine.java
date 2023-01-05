package carnerero.agustin.juego3enraya.model;

import java.util.Random;



public class Machine extends AbstractPlayer {

	public Machine(BoardGrid board) {
		super();
		super.setPartidasGanadas(0);
		super.setPartidasPerdidas(0);
		super.setPlay(false);
		super.setWinner(false);

	}

	public GridCell machinePlays(BoardGrid board) {

		GridCell[][] gridCells = board.getGridCells();
		int bestScore = Integer.MIN_VALUE;
		int score = 0;
		GridCell bestMove = null;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gridCells[i][j].isEmpty()) {
					gridCells[i][j].setMark('X');
					score = miniMax(board, false);
					gridCells[i][j].setMark(' ');
					if (score > bestScore) {
						bestScore = score;
						bestMove = gridCells[i][j];
					}
				}
			}
		}
		return bestMove;
	}

	private int miniMax(BoardGrid board, boolean isMaximazing) {

		GridCell[][] gridCells = board.getGridCells();
		final String LINEA_X = "XXX";
		final String LINEA_O = "OOO";
		int score;
		int bestScore = Integer.MIN_VALUE;
		// El ordenador juega por defecto con X
		if (board.isWinner(gridCells, LINEA_X)) {
			return 1;
		} else if (board.isWinner(gridCells, LINEA_O)) {
			return -1;
		} else if (!board.isWinner(gridCells, LINEA_X) && !board.isWinner(gridCells, LINEA_O) && board.isPlenty()) {
			return 0;
		}
		if (isMaximazing) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (gridCells[i][j].isEmpty()) {
						gridCells[i][j].setMark('X');
						score = miniMax(board, false);
						gridCells[i][j].setMark(' ');
						if (score > bestScore) {
							bestScore = score;
						}
					}
				}
			}
		} else {
			bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (gridCells[i][j].isEmpty()) {
						gridCells[i][j].setMark('O');
						score = miniMax(board, true);
						gridCells[i][j].setMark(' ');
						if (score < bestScore) {
							bestScore = score;
						}
					}
				}
			}
		}
		return bestScore;
	}

}