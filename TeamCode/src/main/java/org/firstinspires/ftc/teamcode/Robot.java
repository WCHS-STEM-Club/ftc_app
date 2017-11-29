package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.commands.Command;

public interface Robot {
    boolean addCommand(Command command);
    boolean pause();
    boolean start();
}
