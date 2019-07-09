package org.stonecipher.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.stonecipher.Slabs;

public class Version implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Slabs.sendMessage(commandSender, "Slabs by Chibill - Maintained by Nickster258");
        Slabs.sendMessage(commandSender, "Version: " + Slabs.descriptionFile.getVersion());
        return true;
    }

}
