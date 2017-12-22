package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot2017 extends Robot {
    public Robot2017(HardwareMap hwMap) {
        super(
                new MotorGroup(
                        1440,
                        319,
                        hwMap.get(DcMotor.class, "left_drive"),
                        hwMap.get(DcMotor.class, "right_drive")
                )
        );

        DcMotor leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        DcMotor rightDrive = hwMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
    }
}
