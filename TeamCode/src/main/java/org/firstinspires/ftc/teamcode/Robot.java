package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.Command;

public interface Robot {
    public boolean addCommand(Command command);
    public boolean pause();
    public boolean start();
}
