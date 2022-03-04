package me.steep.gamemanager;

import com.avaje.ebean.validation.NotNull;
import me.steep.gamemanager.enums.GameStatus;
import me.steep.gamemanager.objects.Queue;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("all")
public class Game {

    private final Set<Player> players = new HashSet<>();
    private final Queue queue = new Queue();
    private GameStatus status = GameStatus.;
    private final World world;

    public Game(World world) {

        this.world = world;

    }

    public void startQueue() {

        this.status = GameStatus.QUEUEING;
        queue.startQueueing();

    }

    public void startGame() {

        queue.stopQueueing();
        this.status = GameStatus.ACTIVE;

    }

    public void setGameStatus(GameStatus status) {
        this.status = status;
    }

    @NotNull
    public GameStatus getGameStatus() {
        return this.status;
    }





}
