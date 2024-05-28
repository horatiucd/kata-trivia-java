package trivia;

public interface Game {

	void enrollPlayer(String name);

	void playTurn(int die);

	boolean onCorrectAnswer();

	boolean onWrongAnswer();

}
