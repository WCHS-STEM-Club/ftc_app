package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

import opmodes.LinearOpMode;

/**
 * Created by rachel on 1/20/2018.
 */


public class MoveKnocker extends Command {
    Robot robot;
    double degree;

    MoveKnocker(Robot robot, double degree){

        this.robot = robot;
        this.degree = degree;

    }

    @Override
    boolean execute() {
        robot.getServo("knock").setAngle(degree); //assuming that the knocker is the 2nd element because lol what are hashmaps

        return true;
    }
}
