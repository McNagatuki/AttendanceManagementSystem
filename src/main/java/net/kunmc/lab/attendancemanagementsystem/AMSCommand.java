package net.kunmc.lab.attendancemanagementsystem;

import net.kunmc.lab.commandlib.Command;

public class AMSCommand extends Command {
    public AMSCommand() {
        super("ams");
        addChildren(new AreaCommand(), new AttendCommand());
    }
}
