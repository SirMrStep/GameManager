package me.steep.gamemanager;

import com.avaje.ebean.validation.NotNull;
import me.steep.gamemanager.commands.GameCommand;
import me.steep.gamemanager.enums.GameStatus;
import me.steep.gamemanager.handlers.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("all")
public class GameManager extends JavaPlugin {

    private static GameManager instance;
    private static WorldManager wm = new WorldManager();
    private final Set<Game> games = new HashSet<>();

    public Set<Game> getGames() {

        return this.games;

    }

    public void removeGame(Game g) {

        this.games.remove(g);

    }

    @NotNull
    public Game addGame() {

        World worldToCopy = Bukkit.getWorld(GameManager.getGameManager().getConfig().getString("world-to-copy"));
        File[] worlds = wm.getGameWorlds();

        World gameworld = null;

        if(!this.games.isEmpty()) {
            this.games.forEach(g -> {

                if(g.getGameStatus() == GameStatus.ACTIVE || g.getGameStatus() == GameStatus.QUEUEING)

            });
        }

        if(gameworld == null) {
            WorldCreator c = new WorldCreator("gm_game" + worlds.length);
            gameworld = c.copy(worldToCopy).createWorld();
        }

        Game g = new Game(gameworld);

        this.games.add(g);

        return g;

    }

    @Override
    public void onEnable() {
        initialize();
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    public static GameManager getGameManager() {
        return instance;
    }

    private void initialize() {

        this.registerListeners();
        this.registerCommands();
        this.assignVariables();
        this.loadIdleWorlds();

    }

    private void registerListeners() {

        PluginManager pm = Bukkit.getPluginManager();

    }

    private void registerCommands() {

        getCommand("game").setExecutor(new GameCommand());

    }

    private void assignVariables() {

    }

    private void loadIdleWorlds() {

        int amount = this.getConfig().getInt("idle-game-worlds");

        File[] worlds = Bukkit.getWorldContainer().listFiles(f -> f.isDirectory() && wm.isGameWorld(f));

        if(worlds.length >= amount) return;

        amount -= worlds.length;

        for(int i = 0; i < amount; i++) {

            if(i == 0) {
                wm.createWorld("gm_game" + worlds.length);
                continue;
            }

            wm.createWorld("gm_game" + (worlds.length + i));

        }

    }


}
