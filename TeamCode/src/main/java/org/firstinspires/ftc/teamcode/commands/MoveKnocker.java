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

    MoveKnocker(Robot robot, LinearOpMode  opMode, double degree){
        super(opMode);
        this.robot = robot;
        this.degree = degree;

    }
    public boolean execute(AsyncTask commandThread) {
        robot.servos[1].setAngle(degree); //assuming that the knocker is the 2nd element because lol what are hashmaps
        return false;
    }
}
