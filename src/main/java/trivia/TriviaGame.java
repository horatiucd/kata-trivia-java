package trivia;

import java.util.ArrayList;
import java.util.List;

public class TriviaGame implements IGame {

    private static final int TO_WIN = 6;
    private static final int TOTAL_NUMBER_OF_POSITIONS = 12;

    private final Questions questions;
    private final List<Player> players;

    private boolean isGettingOutOfPenaltyBox;

    private int currentPlayerIndex;

    public TriviaGame() {
        questions = new Questions();
        players = new ArrayList<>();
    }

    @Override
    public void addPlayer(String name) {
        players.add(new Player(name));
        System.out.println(name + " was added");
        System.out.println("He / She is player number " + players.size());
    }

    @Override
    public void movePlayer(int positions) {
        Player player = currentPlayer();

        System.out.println(player.getName() + " is the current player");
        System.out.println("He / She has rolled a " + positions);

        if (player.isInPenaltyBox()) {
            if (positions % 2 != 0) {
                System.out.println(player.getName() + " is getting out of the penalty box");
                isGettingOutOfPenaltyBox = true;
            } else {
                System.out.println(player.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
        }

        player.move(positions, TOTAL_NUMBER_OF_POSITIONS);
        player.showStatus();
        askQuestion();
    }

    @Override
    public boolean wasCorrectlyAnswered() {
        if (currentPlayer().isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                return correctAnswer();
            } else {
                nextPlayer();
                return true;
            }
        } else {
            return correctAnswer();
        }
    }

    @Override
    public boolean wrongAnswer() {
        Player player = currentPlayer();
        System.out.println("Question was incorrectly answered");
        System.out.println(player.getName() + " was sent to the penalty box");
        player.setInPenaltyBox(true);

        nextPlayer();
        return true;
    }

    private void askQuestion() {
        System.out.println(questions.popQuestion(Category.atIndex(currentPlayer().getPosition())));
    }

    private Player currentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) {
            currentPlayerIndex = 0;
        }
    }

    private boolean correctAnswer() {
        Player player = currentPlayer();

        System.out.println("Answer was correct!!!!");
        player.collectCoin();
        System.out.println(player.getName() + " now has " + player.getCoins() + " Gold Coins.");

        nextPlayer();
        return !player.ownsCoins(TO_WIN);
    }
}
