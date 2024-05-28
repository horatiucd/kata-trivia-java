package trivia;

import java.util.Objects;

public final class Player {

    private final String name;

    private int position;
    private int coins;

    public Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public int position() {
        return position;
    }

    public void move(int tiles, int maxTiles) {
        position += tiles;
        if (position > maxTiles - 1) {
            position -= maxTiles;
        }
    }

    public int coins() {
        return coins;
    }

    public void collectCoin() {
        coins++;
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
