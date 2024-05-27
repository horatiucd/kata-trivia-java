package trivia;

import java.util.ArrayList;
import java.util.List;

public class TriviaGame implements IGame {

    private static final int BOARD_SPOTS = 12;
    private static final int MAX_PLAYERS = 4;
    private static final int TO_WIN = 6;

    private final Questions questions = new Questions();
    private final List<Player> players = new ArrayList<>();

    private Player currentPlayer;


    @Override
    public void enrollPlayer(String name) {
        if (players.size() == MAX_PLAYERS) {
            throw new IllegalStateException("The maximum allowed number of players is " + MAX_PLAYERS + ".");
        }
        players.add(new Player(name));
        System.out.println(name + " was added");
        System.out.println("He / She is player number " + players.size());

        if (currentPlayer == null) {
            currentPlayer = players.get(0);
        }
    }

    @Override
    public void playTurn(int die) {
        if (players.isEmpty()) {
            throw new IllegalStateException("No players have enrolled yet.");
        }

        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("He / She has rolled a " + die);

        if (currentPlayer.isInPenaltyBox()) {
            if (!isReleaseDie(die)) {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                return;
            }
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
            currentPlayer.setInPenaltyBox(false);
        }

        currentPlayer.move(die, BOARD_SPOTS);
        question();
    }

    private static boolean isReleaseDie(int die) {
        return die % 2 != 0;
    }

    private void question() {
        Category category = Category.withIndex(currentPlayer.getPosition());
        System.out.println("The category is " + category);
        System.out.println(questions.popQuestion(category));
    }

    @Override
    public boolean onCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
        currentPlayer.collectCoin();

        if (currentPlayer.ownsCoins(TO_WIN)) {
            return false;
        }
        nextPlayer();
        return true;
    }

    @Override
    public boolean onWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);

        nextPlayer();
        return true;
    }

    private void nextPlayer() {
        int index = players.indexOf(currentPlayer) + 1;
        if (index == players.size()) {
            index = 0;
        }
        currentPlayer = players.get(index);
    }
}
