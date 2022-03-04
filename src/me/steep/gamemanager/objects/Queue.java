package me.steep.gamemanager.objects;

import me.steep.gamemanager.Game;
import me.steep.gamemanager.enums.GameStatus;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Queue {

    private final Set<Player> playersInQueue = new HashSet<>();

    public void startQueueing() {

    }

    public void stopQueueing() {

    }

    public Set<Player> getPlayersInQueue() {
        return this.playersInQueue;
    }

}
