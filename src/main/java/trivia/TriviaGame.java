package trivia;

import java.util.ArrayList;
import java.util.List;

public class TriviaGame implements IGame {

    private static final int BOARD_SPOTS = 12;
    private static final int MAX_PLAYERS = 4;
    private static final int TO_WIN = 6;

    private final Questions questions;
    private final List<Player> players;

    private boolean isGettingOutOfPenaltyBox;

    private int currentPlayerIndex;

    public TriviaGame() {
        questions = new Questions();
        players = new ArrayList<>();
    }

    @Override
    public void enrollPlayer(String name) {
        if (players.size() == MAX_PLAYERS) {
            throw new IllegalStateException("The maximum allowed number of players is " + MAX_PLAYERS + ".");
        }

        players.add(new Player(name));
        System.out.println(name + " was added");
        System.out.println("He / She is player number " + players.size());
    }

    @Override
    public void playTurn(int die) {
        Player player = currentPlayer();

        System.out.println(player.getName() + " is the current player");
        System.out.println("He / She has rolled a " + die);

        if (player.isInPenaltyBox()) {
            if (isNotLucky(die)) {
                System.out.println(player.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
            System.out.println(player.getName() + " is getting out of the penalty box");
            isGettingOutOfPenaltyBox = true;
        }

        player.move(die, BOARD_SPOTS);
        question();
    }

    private static boolean isNotLucky(int die) {
        return die % 2 == 0;
    }

    private void question() {
        System.out.println(questions.popQuestion(Category.atIndex(currentPlayer().getPosition())));
    }

    @Override
    public boolean onCorrectAnswer() {
        if (currentPlayer().isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                return answerWasCorrect();
            } else {
                nextPlayer();
                return true;
            }
        } else {
            return answerWasCorrect();
        }
    }

    private boolean answerWasCorrect() {
        Player player = currentPlayer();

        System.out.println("Answer was correct!!!!");
        player.collectCoin();
        System.out.println(player.getName() + " now has " + player.getCoins() + " Gold Coins.");

        nextPlayer();
        return !player.ownsCoins(TO_WIN);
    }

    @Override
    public boolean onWrongAnswer() {
        Player player = currentPlayer();
        System.out.println("Question was incorrectly answered");
        System.out.println(player.getName() + " was sent to the penalty box");
        player.setInPenaltyBox(true);

        nextPlayer();
        return true;
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
}
