package com.onarandombox.MultiverseCore.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.command.BaseCommand;

public class SpawnCommand extends BaseCommand {
    
    public SpawnCommand(MultiverseCore plugin) {
        super(plugin);
        this.name = "Spawn";
        this.description = "Transports the player to the that player's current world Spawn Point.";
        this.usage = "/mvspawn" + ChatColor.GOLD + " [PLAYER]";
        this.minArgs = 0;
        this.maxArgs = 1;
        this.identifiers.add("mvspawn");
        this.permission = "multiverse.world.spawn";
        this.requiresOp = true;
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        // TODO: Permissions
        Player commandSender = null;
        if (sender instanceof Player) {
            commandSender = (Player) sender;
        }
        if (args.length == 1) {
            Player target = this.plugin.getServer().getPlayer(args[0]);
            if (target != null) {
                target.sendMessage("Teleporting to this world's spawn...");
                target.teleport(target.getWorld().getSpawnLocation());
                if (commandSender != null) {
                    target.sendMessage("You were teleported by: " + ChatColor.YELLOW + commandSender.getName());
                } else {
                    target.sendMessage("You were teleported by: " + ChatColor.LIGHT_PURPLE + "the console");
                }
            } else {
                sender.sendMessage(args[0] + " is not logged on right now!");
            }
        } else {
            if (commandSender != null) {
                commandSender.sendMessage("Teleporting to this world's spawn...");
                commandSender.teleport(commandSender.getWorld().getSpawnLocation());
            } else {
                sender.sendMessage("From the console, you must provide a PLAYER.");
            }
        }
    }
}
