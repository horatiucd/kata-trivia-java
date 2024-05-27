package trivia;

public class Player {

    private final String name;

    private int position;
    private int coins;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move(int positions, int maxPositions) {
        position += positions;
        if (position > maxPositions - 1) {
            position -= maxPositions;
        }
    }

    public int getCoins() {
        return coins;
    }

    void collectCoin() {
        coins++;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void showStatus() {
        System.out.println(name + "'s new location is " + position);
        System.out.println("The category is " + Category.atIndex(position));
    }

    public boolean ownsCoins(int coins) {
        return this.coins == coins;
    }
}
