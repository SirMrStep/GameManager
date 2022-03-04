package me.steep.gamemanager.commands;

import me.steep.gamemanager.Game;
import me.steep.gamemanager.GameManager;
import me.steep.gamemanager.handlers.WorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {

    GameManager gm = GameManager.getGameManager();
    WorldManager wm = new WorldManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        switch (args.length) {

            case 1:

                if(!args[0].equalsIgnoreCase("join")) {
                    sendHelp(p);
                    return true;
                }

                Game game = gm.addGame();

                break;

            default:

                sendHelp(p);

        }

        return true;
    }

    private void sendHelp(Player p) {
        p.sendMessage("some help message");
    }
}
