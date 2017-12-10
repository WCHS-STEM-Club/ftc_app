package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

public class DriveForDistance implements Command {
    private float distance;
    private float power;
    private Telemetry telemetry;

    // Distance is in decimeters
    public DriveForDistance(float distance, float power, Telemetry telemetry) {
        this.distance = distance;
        this.power = power;
        this.telemetry = telemetry;
    }

    @Override
    public boolean executeCommand(Robot robot) {
        HardwareMap hwMap = robot.getHwMap();
        DcMotor left = hwMap.dcMotor.get("left_drive");
        DcMotor right = hwMap.dcMotor.get("right_drive");

        float mmDist = distance * 100;
        int clicks = (int)(mmDist * (float)(robot.X_CLICKS / robot.MM_PER_X_CLICKS));

        // Brake when the command is done
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset the encoders
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Use encoders
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int targetL = left.getCurrentPosition() + clicks;
        int targetR = right.getCurrentPosition() + clicks;

        left.setTargetPosition(targetL);
        right.setTargetPosition(targetR);

        // Run for a set number of clicks
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left.setPower(power);
        right.setPower(power);

        while (left.isBusy() && right.isBusy()) {
            telemetry.addData("Target encoder clicks", "R: %d, L: %d", targetR, targetL);
            telemetry.addData("Current encoder clicks", "R: %d, L: %d", targetR, targetL);
            telemetry.update();
        }

        left.setPower(0);
        right.setPower(0);

        // Disable run to position
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        return true;
    }
}
