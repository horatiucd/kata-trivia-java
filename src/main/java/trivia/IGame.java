package trivia;

public interface IGame {

	void enrollPlayer(String name);

	boolean playTurn(int die);

	boolean onCorrectAnswer();

	boolean onWrongAnswer();

}
