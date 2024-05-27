package trivia;

public class Player {

    private final String name;

    private int position;
    private int coins;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public void rollPosition(int roll, int totalNumberOfPositions) {
        this.position += roll;
        if (this.position > totalNumberOfPositions - 1) {
            this.position -= totalNumberOfPositions;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    void addCoin() {
        coins++;
    }
}
