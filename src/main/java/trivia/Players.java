package trivia;

import java.util.ArrayList;
import java.util.List;

public final class Players {

    private final int maxPlayers;
    private final int coinsToWin;

    private final List<Player> playing;

    private Player currentPlayer;

    public Players(int maxPlayers, int coinsToWin) {
        this.maxPlayers = maxPlayers;
        this.coinsToWin = coinsToWin;

        playing = new ArrayList<>();
    }

    public boolean isEmpty() {
        return playing.isEmpty();
    }

    public int add(Player player) {
        if (playing.size() == maxPlayers) {
            throw new IllegalStateException("At most " + maxPlayers + " can play.");
        }
        playing.add(player);

        if (currentPlayer == null) {
            currentPlayer = player;
        }
        return playing.size();
    }

    public Player currentPlayer() {
        if (currentPlayer == null) {
            throw new IllegalStateException("No players have enrolled yet.");
        }
        return currentPlayer;
    }

    public void nextPlayer() {
        int index = playing.indexOf(currentPlayer) + 1;
        if (index == playing.size()) {
            index = 0;
        }
        currentPlayer = playing.get(index);
    }

    public boolean hasWinner() {
        return currentPlayer.coins() == coinsToWin;
    }
}
