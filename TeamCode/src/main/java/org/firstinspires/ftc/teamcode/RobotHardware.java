package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by nathan on 11/6/17.
 */

public class RobotHardware {
    // TODO: No robot, so theoretical. When there is a robot, make sure this matches up.

    private HardwareMap mHardwareMap = null;

    private String[] mMotorNames = {
            "left_back_motor",
            "right_back_motor",
            "left_front_motor",
            "right_front_motor"
    };

    private DcMotor mLeftBackMotor   = null;
    private DcMotor mRightBackMotor  = null;
    private DcMotor mLeftFrontMotor  = null;
    private DcMotor mRightFrontMotor = null;

    public RobotHardware (HardwareMap hardwareMap) {
        this.mHardwareMap = hardwareMap;
    }
}
