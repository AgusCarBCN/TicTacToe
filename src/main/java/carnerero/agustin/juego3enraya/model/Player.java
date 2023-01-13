package carnerero.agustin.juego3enraya.model;



public class Player extends APlayer {

	public Player(int idPlayer) {
		super();
		super.setIdPlayer(idPlayer);
		super.setPartidasGanadas(0);
		super.setPartidasPerdidas(0);
		super.setPlay(false);
		super.setWinner(false);
	}

}
