package carnerero.agustin.juego3enraya.model;

public class Machine extends APlayer {

	public Machine(BoardGrid board) {
		super();
		super.setPartidasGanadas(0);
		super.setPartidasPerdidas(0);
		super.setPlay(false);
		super.setWinner(false);

	}

	public GridCell machinePlaysMax(BoardGrid board) {

		GridCell[][] gridCells = board.getGridCells();
		int bestScore = Integer.MIN_VALUE;
		int beta=Integer.MAX_VALUE;
		int alfa=Integer.MIN_VALUE;
		int score = 0;
		GridCell bestMove = null;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gridCells[i][j].isEmpty()) {
					gridCells[i][j].setMark('X');
					score = miniMax(board, false,alfa,beta);
					gridCells[i][j].setMark(' ');
					if (score > bestScore) {
						bestScore = score;
						bestMove = gridCells[i][j];
					}
					if(score>alfa) {
						alfa=score;
					}if(beta<=alfa) {
						break;
					}
				}
			}
		}

		return bestMove;
	}

	public GridCell machinePlaysMin(BoardGrid board) {

		GridCell[][] gridCells = board.getGridCells();
		int bestScore = Integer.MAX_VALUE;	
		int beta=Integer.MAX_VALUE;
		int alfa=Integer.MIN_VALUE;
		int score = 0;
		GridCell bestMove = null;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gridCells[i][j].isEmpty()) {
					gridCells[i][j].setMark('O');
					score = miniMax(board, true,alfa,beta);
					gridCells[i][j].setMark(' ');
					if (score < bestScore) {
						bestScore = score;
						bestMove = gridCells[i][j];
					}if(score<beta) {
						beta=score;
					}if(beta<=alfa) {
						break;
					}
				}
			}
		}
		return bestMove;
	}

	private int miniMax(BoardGrid board, boolean isMaximazing,int alfa,int beta) {

		GridCell[][] gridCells = board.getGridCells();
		final String LINEA_X = "XXX";
		final String LINEA_O = "OOO";
		int score;
		int bestScore;
		
		if (board.isWinner(gridCells, LINEA_X)) {
			return 1;
		} else if (board.isWinner(gridCells, LINEA_O)) {
			return -1;
		} else if (!board.isWinner(gridCells, LINEA_X) && !board.isWinner(gridCells, LINEA_O) && board.isPlenty()) {
			return 0;
		}
		if (isMaximazing) {
			bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (gridCells[i][j].isEmpty()) {
						gridCells[i][j].setMark('X');
						score = miniMax(board, false,alfa,beta);
						gridCells[i][j].setMark(' ');
						if (score > bestScore) {
							bestScore = score;
						}
						if(score>alfa) {
							alfa=score;
						}
						if(beta<=alfa) {
							break;
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
						score = miniMax(board, true,alfa,beta);
						gridCells[i][j].setMark(' ');
						if (score < bestScore) {
							bestScore = score;
						}
						if(score<beta) {
							beta=score;
						}
						if(beta<=alfa) {
							break;
						}
					}
				}
			}

		}
		return bestScore;
	}	

}