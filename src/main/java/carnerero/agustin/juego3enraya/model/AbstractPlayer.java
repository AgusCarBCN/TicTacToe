package carnerero.agustin.juego3enraya.model;

import javax.swing.ImageIcon;

public abstract class AbstractPlayer {
	private int idPlayer;
	private int partidasGanadas;
	private int partidasPerdidas;
	private boolean isWinner;
	private boolean play;

	public AbstractPlayer() {
		super();

	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}

	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public void markCell(GridCell c, Mark mark) {
		
			c.getGridCellLabel().setIcon(new ImageIcon(BoardGrid.class.getResource(mark.getPathMark())));
			c.setMark(mark.getMark());
			c.setVisible(true);
		
	}
}
