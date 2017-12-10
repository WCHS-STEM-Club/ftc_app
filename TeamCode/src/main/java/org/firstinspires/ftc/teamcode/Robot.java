package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.Command;

public abstract class Robot {
    // Millimeters/x encoder clicks
    public final int X_CLICKS;
    public final double MM_PER_X_CLICKS;

    public Robot() {
        this.X_CLICKS = 0;
        this.MM_PER_X_CLICKS = 0;
    }

    public Robot(int X_CLICKS, double MM_PER_X_CLICKS) {
        this.X_CLICKS = X_CLICKS;
        this.MM_PER_X_CLICKS = MM_PER_X_CLICKS;
    }

    abstract public boolean addCommand(Command command);
    abstract public boolean stop();
    abstract public boolean start();

    abstract public HardwareMap getHwMap();
}
