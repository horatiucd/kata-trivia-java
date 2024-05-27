package trivia;

import java.util.HashSet;
import java.util.Set;

public final class PenaltyBox {

    private final Set<Player> players = new HashSet<>();

    public void lock(Player player) {
        players.add(player);
    }

    public void release(Player player) {
        players.remove(player);
    }

    public boolean has(Player player) {
        return players.contains(player);
    }

    public boolean isRelease(int die) {
        return die % 2 != 0;
    }
}
