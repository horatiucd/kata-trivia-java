package trivia;

import java.util.ArrayList;
import java.util.List;

public class TriviaGame implements IGame {

    private static final int TOTAL_NUMBER_OF_POSITIONS = 12;

    private final List<Player> players;
    private final Questions questions;

    private boolean isGettingOutOfPenaltyBox;

    private int currentPlayerIndex;
    // @Transient (JPA) / transient (keyword Java) marcheaza un camp NE-MAPAT IN BAZA
    // temporary field code smell. il mai intalnesti ca campuri @Transient/transient
//    private Player currentPlayer; // = players.get(currentPlayerIndex); = redudanta

    public TriviaGame() {
        questions = new Questions();
        players = new ArrayList<>();
    }

    @Override
    public void addPlayer(String name) {
        players.add(new Player(name));
        System.out.println(name + " was added");
        System.out.println("They are player number " + players.size());
    }

    @Override
    public void roll(int roll) {
        System.out.println(currentPlayer().getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer().getName() + " is getting out of the " +
                        "penalty box");
            } else {
                System.out.println(currentPlayer().getName() + " is not " +
                        "getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
        }

        currentPlayer().rollPosition(roll, TOTAL_NUMBER_OF_POSITIONS);
        showStatusOfCurrentPlayer();
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
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer().getName() + " was sent to the penalty box");
        currentPlayer().setInPenaltyBox(true);

        nextPlayer();
        return true;
    }

    private void showStatusOfCurrentPlayer() {
        System.out.println(currentPlayer().getName()
                + "'s new location is "
                + currentPlayer().getPosition());
        System.out.println("The category is " + Category.atIndex(currentPlayer().getPosition()));
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
        System.out.println("Answer was correct!!!!");
        currentPlayer().addCoin();
        System.out.println(currentPlayer().getName()
                + " now has "
                + currentPlayer().getCoins()
                + " Gold Coins.");

        boolean winner = currentPlayer().getCoins() != 6;
        nextPlayer();
        return winner;
    }
}
