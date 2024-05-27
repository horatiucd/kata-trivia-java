package trivia;

public class Player {

    private final String name;

    private int position;
    private int coins;
    private boolean inJail;

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

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }
}
