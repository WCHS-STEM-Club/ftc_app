package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveForTime implements Command {
    @Override
    public boolean executeCommand(HardwareMap hwMap) {
        // l = left, r = right, b = back, f = front
        DcMotor lb = hwMap.get(DcMotor.class, "lb");
        DcMotor rb = hwMap.get(DcMotor.class, "rb");
        DcMotor lf = hwMap.get(DcMotor.class, "lf");
        DcMotor rf = hwMap.get(DcMotor.class, "rf");


        return false;
    }
}
