package trivia;

public interface IGame {

	void addPlayer(String name);

	void roll(int roll);

	boolean wasCorrectlyAnswered();

	boolean wrongAnswer();

}
