package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

public class DriveForDistance extends Command {
    private float distance;
    private float power;
    private Telemetry telemetry;
    private Robot robot;

    // Distance is in decimeters
    public DriveForDistance(float distance, float power, Telemetry telemetry, Robot robot,
                            LinearOpMode opMode) {
        super(opMode);
        this.distance = distance;
        this.power = power;
        this.telemetry = telemetry;
        this.robot = robot;
    }

    @Override
    boolean execute(AsyncTask commandThread) {
        robot.driveTrain.goForDistance(distance, power);

        while (robot.driveTrain.isBusy() && !commandThread.isCancelled()) {
            Thread.yield();
        }

        robot.driveTrain.brake();

        // Disable run to position
        robot.driveTrain.useEncoders();

        return true;
    }
}
