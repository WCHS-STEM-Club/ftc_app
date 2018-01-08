package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot2017 extends Robot {
    public Robot2017(HardwareMap hwMap) {
        // Get the motors from the hardware map: based on the robot configuration
        DcMotor leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        DcMotor rightDrive = hwMap.get(DcMotor.class, "right_drive");

        // Set motor direction: this is based on how the robot was built
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        setForwardMotors(new MotorGroup(
                1440,
                319,
                leftDrive,
                rightDrive
        ));
        setTurnMotors(new MotorGroup[] {
                new MotorGroup(
                        1440,
                        319,
                        rightDrive
                ),
                new MotorGroup(
                        1440,
                        319,
                        leftDrive
                )
        });
        setStrafeMotors(null); // This line is optional: it disables strafe, disabled is default

    }
}