package trivia;

public interface IGame {

	void enrollPlayer(String name);

	void playTurn(int die);

	boolean onCorrectAnswer();

	boolean onWrongAnswer();

}
