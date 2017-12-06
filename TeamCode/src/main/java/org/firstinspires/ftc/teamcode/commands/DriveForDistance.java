package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveForDistance implements Command {
    private float distance;
    private float power;
    private DcMotor left;
    private DcMotor right;

    public DriveForDistance(float distance, float power, DcMotor left, DcMotor right) {
        this.distance = distance;
        this.power = power;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean executeCommand() {
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();

        left.setPower(power);
        right.setPower(power);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        while(runtime.milliseconds() / 1000 < this.seconds);

        left.setPower(0);
        right.setPower(0);

        return true;
    }
}
