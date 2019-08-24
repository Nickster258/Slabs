package org.stonecipher.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlabTabCompleter implements TabCompleter {

    private static final List<String> availableSlabs = getAvailableSlabs();

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        final List<String> completions = new ArrayList<>();
        if (strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], availableSlabs, completions);
            Collections.sort(completions);
        }
        return completions;
    }

    private static List<String> getAvailableSlabs() {
        List<String> slabName = new ArrayList<>();
        for (Material mat : Material.values()) {
            if (mat.isBlock() && (mat.createBlockData() instanceof org.bukkit.block.data.type.Slab)) {
                slabName.add(mat.toString().toLowerCase());
            }
        }
        return slabName;
    }

}
