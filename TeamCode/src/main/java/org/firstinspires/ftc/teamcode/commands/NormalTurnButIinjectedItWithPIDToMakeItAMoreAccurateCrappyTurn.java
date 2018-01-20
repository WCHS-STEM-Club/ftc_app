package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot;

import opmodes.LinearOpMode;

/**
 * Created by kofu on 1/19/2018.
 */

public class NormalTurnButIinjectedItWithPIDToMakeItAMoreAccurateCrappyTurn extends Command{
    float targetturndistance;
    float power;
    Robot robot;
    PIDCalc pid;
    //should be implemented only if you know you can humanly adjust based upon the variability of your robot
    //like drivefortime, but worse because it's more inaccurate
    public NormalTurnButIinjectedItWithPIDToMakeItAMoreAccurateCrappyTurn(float angle, float power, Robot robot, LinearOpMode opmode){
        super(opmode);
        //angle =
        //assuming radius = 7; implying axis of rotation is at the center of n amount of turning wheels
        // because who wouldn't want to maximise bot size? (that's counterintuitive, i know)
        // distance to turn = angle * circle
        //ew, math
        targetturndistance = (float) (angle/360 * (2 * Math.PI * (7 * 1.77)));
        this.power = power;
        this.robot = robot;
        pid = new PIDCalc();
    }

    @Override
    public boolean execute(AsyncTask commandThread) {
        while( (pid.calcError(robot.turnMotors.getMotor(0).getCurrentPosition(), targetturndistance)/2 * 100 *
                robot.turnMotors.X_CLICKS/robot.turnMotors.MM_PER_X_CLICKS)!= 0
        )

        {
            robot.turnMotors.useEncoders();

            //ugly math but its just a mock up ok?
            robot.turnMotors.getMotor(0).setTargetPosition((int) (pid.calcError
                    (robot.turnMotors.getMotor(0).getCurrentPosition(), targetturndistance)/2 * 100 *
                    robot.turnMotors.X_CLICKS/robot.turnMotors.MM_PER_X_CLICKS));
            robot.turnMotors.getMotor(1).setTargetPosition((int) (pid.calcError
                    (robot.turnMotors.getMotor(1).getCurrentPosition(), -targetturndistance)/2 * 100 *
                    robot.turnMotors.X_CLICKS/robot.turnMotors.MM_PER_X_CLICKS));

        }
        return true;
    }
}
