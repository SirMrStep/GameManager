package me.steep.gamemanager.handlers;

import com.avaje.ebean.validation.NotNull;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("all") // remove when debugging
public class WorldManager {

    /**
     * This method is unnecessary but is here for the sake of only using this class
     * @param creator The WorldCreator object used to create the world
     */
    @NotNull
    public World createWorld(String name) {
        WorldCreator creator = new WorldCreator(name);
        return creator.createWorld();
    }

    /**
     * @param name Name of the world to be created
     * @param world The world to copy from
     * @return The created world
     */
    @NotNull
    public World createWorldCopy(String name, World world) {
        WorldCreator c = new WorldCreator(name);
        c.copy(world);
        return c.createWorld();
    }

    /**
     * @param world The world to remove
     * @param teleportPlayersTo The location to teleport players who were inside the world being removed.
     * @return Whether the file was successfully deleted or not.
     */
    public boolean removeWorld(World world, Location teleportPlayersTo) {

        if(world.getPlayers().size() > 0) world.getPlayers().forEach(p -> p.teleport(teleportPlayersTo));

        Bukkit.unloadWorld(world.getName(), false);

        File path = world.getWorldFolder();

        try {
            FileUtils.deleteDirectory(world.getWorldFolder());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path.exists();

    }

    public boolean isGameWorld(File world) {
        return world.getName().startsWith("gm_game");
    }

    public File[] getGameWorlds() {
        return Bukkit.getWorldContainer().listFiles(f -> f.isDirectory() && this.isGameWorld(f));
    }

}
