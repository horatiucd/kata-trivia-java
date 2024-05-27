package trivia;

public interface IGame {

	void addPlayer(String name);

	void movePlayer(int positions);

	boolean wasCorrectlyAnswered();

	boolean wrongAnswer();

}
