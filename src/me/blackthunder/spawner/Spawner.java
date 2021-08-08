package me.blackthunder.spawner;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Spawner extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("spawner.use")) {
                if (cmd.getName().equalsIgnoreCase("spawner")) {
                    if (args.length == 0) {
                        p.sendMessage(ChatColor.RED + "/spawner <mobtype>");
                        return true;
                    }

                    EntityType type;

                    try {
                        type = EntityType.valueOf(args[0].toUpperCase());
                    } catch (Exception e) {
                        p.sendMessage(ChatColor.RED + "Invalid Mob Type");
                        return true;
                    }

                    Block b = p.getTargetBlock(null, 10);

                    if (b == null) {
                        p.sendMessage(ChatColor.DARK_PURPLE + "You need to be looking at a block");
                    }

                    if(b.getType().equals(Material.SPAWNER)){
                        CreatureSpawner s = (CreatureSpawner) b.getState();

                        s.setSpawnedType(type);
                        p.sendMessage(ChatColor.GREEN + "Spawner changed to a " + type.toString().toLowerCase() + " spawner");

                    } else {
                        p.sendMessage(ChatColor.RED + "You are not looking at a spawner!");
                    }

                }
            }
        }

        return false;
    }

}
