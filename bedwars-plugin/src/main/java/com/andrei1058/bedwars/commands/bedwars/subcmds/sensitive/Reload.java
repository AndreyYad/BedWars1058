package com.andrei1058.bedwars.commands.bedwars.subcmds.sensitive;

import com.andrei1058.bedwars.commands.ParentCommand;
import com.andrei1058.bedwars.commands.SubCommand;
import com.andrei1058.bedwars.language.Language;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.arena.Misc;
import com.andrei1058.bedwars.arena.SetupSession;
import com.andrei1058.bedwars.commands.bedwars.MainCommand;
import com.andrei1058.bedwars.configuration.Permissions;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Reload extends SubCommand {

    public Reload(ParentCommand parent, String name) {
        super(parent, name);
        setPriority(11);
        showInList(true);
        setPermission(Permissions.PERMISSION_RELOAD);
        setDisplayInfo(Misc.msgHoverClick("§6 ▪ §7/" + getParent().getName() + " "+getSubCommandName()+"       §8 - §ereload messages",
                "§fReload messages.\n§cNot recommended!", "/"+ getParent().getName() + " "+getSubCommandName(), ClickEvent.Action.RUN_COMMAND));
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (s instanceof Player) {
            if (!MainCommand.isLobbySet((Player) s)) return true;
        } else {
            if (!MainCommand.isLobbySet(null)) return true;
        }
        for (Language l : Language.getLanguages()){
            l.reload();
            s.sendMessage("§6 ▪ §7"+l.getLangName()+" reloaded!");
        }
        return true;
    }

    @Override
    public List<String> getTabComplete() {
        return null;
    }

    @Override
    public boolean canSee(CommandSender s) {
        if (s instanceof Player) {
            Player p = (Player) s;
            if (Arena.isInArena(p)) return false;
            if (SetupSession.isInSetupSession(p.getUniqueId())) return false;
        }
        return hasPermission(s);
    }
}
