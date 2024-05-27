package trivia;

public class TriviaGame implements IGame {

    private static final int MAX_PLAYERS = 4;
    private static final int COINS_TO_WIN_THE_GAME = 6;
    private static final int BOARD_SPOTS = 12;

    private final Questions questions;
    private final Players players;
    private final PenaltyBox penaltyBox;

    public TriviaGame() {
        questions = new Questions();
        players = new Players(MAX_PLAYERS, COINS_TO_WIN_THE_GAME);
        penaltyBox = new PenaltyBox();
    }

    @Override
    public void enrollPlayer(String name) {
        int no = players.add(new Player(name));
        System.out.println(name + " was added");
        System.out.println("He / She is player number " + no);
    }

    @Override
    public boolean playTurn(int die) {
        final Player currentPlayer = players.currentPlayer();

        System.out.println(currentPlayer.name() + " is the current player");
        System.out.println("He / She has rolled a " + die);

        if (penaltyBox.has(currentPlayer)) {
            if (!penaltyBox.isRelease(die)) {
                System.out.println(currentPlayer.name() + " is not getting out of the penalty box");
                return false;
            }
            System.out.println(currentPlayer.name() + " is getting out of the penalty box");
            penaltyBox.release(currentPlayer);
        }

        currentPlayer.move(die, BOARD_SPOTS);
        System.out.println(currentPlayer.name() + "'s new location is " + currentPlayer.position());

        Category category = Category.getCategory(currentPlayer.position());
        System.out.println("The category is " + category);
        System.out.println(questions.popFor(category));
        return true;
    }

    @Override
    public boolean onCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
        final Player currentPlayer = players.currentPlayer();
        currentPlayer.collectCoin();
        System.out.println(currentPlayer.name() + " now has " + currentPlayer.coins() + " Gold Coins.");

        if (players.hasWinner()) {
            return false;
        }
        players.nextPlayer();
        return true;
    }

    @Override
    public boolean onWrongAnswer() {
        final Player currentPlayer = players.currentPlayer();

        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.name() + " was sent to the penalty box");
        penaltyBox.lock(currentPlayer);

        players.nextPlayer();
        return true;
    }
}
