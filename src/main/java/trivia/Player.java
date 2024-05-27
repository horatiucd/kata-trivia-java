package trivia;

import java.util.Objects;

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

        System.out.println(name + "'s new location is " + position);
        System.out.println("The category is " + Category.atIndex(position));
    }

    public void collectCoin() {
        coins++;

        System.out.println(name + " now has " + coins + " Gold Coins.");
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean ownsCoins(int coins) {
        return this.coins == coins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
