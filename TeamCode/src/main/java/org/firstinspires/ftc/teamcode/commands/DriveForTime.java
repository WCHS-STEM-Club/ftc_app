package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

public class DriveForTime implements Command {
    // TODO: This command needs updating eventually, but is low priority
    private float seconds;
    private float power;
    private DcMotor left;
    private DcMotor right;

    public DriveForTime(float seconds, float power, DcMotor left, DcMotor right) {
        this.seconds = seconds;
        this.power = power;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean executeCommand(Robot robot) {
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
