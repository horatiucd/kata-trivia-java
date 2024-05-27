package trivia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Players {

    private final int maxPlayers;
    private final int coinsToWin;

    private final Map<Player, Integer> standings;

    private Player currentPlayer;

    public Players(int maxPlayers, int coinsToWin) {
        this.maxPlayers = maxPlayers;
        this.coinsToWin = coinsToWin;

        standings = new LinkedHashMap<>();
    }

    public boolean isEmpty() {
        return standings.isEmpty();
    }

    public int add(Player player) {
        if (standings.size() == maxPlayers) {
            throw new IllegalStateException("At most " + maxPlayers + " can play.");
        }
        standings.put(player, 0);

        if (currentPlayer == null) {
            currentPlayer = player;
        }
        return standings.size();
    }

    public Player currentPlayer() {
        if (currentPlayer == null) {
            throw new IllegalStateException("No players have enrolled yet.");
        }
        return currentPlayer;
    }

    public void nextPlayer() {
        List<Player> players = new ArrayList<>(standings.keySet());
        int index = players.indexOf(currentPlayer) + 1;
        if (index == players.size()) {
            index = 0;
        }
        currentPlayer = players.get(index);
    }

    public boolean hasWinner() {
        return currentPlayer.coins() == coinsToWin;
    }
}
