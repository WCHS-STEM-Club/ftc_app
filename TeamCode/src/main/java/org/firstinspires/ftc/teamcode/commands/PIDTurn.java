package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

import opmodes.LinearOpMode;

/**
 * Created by kofu on 1/19/2018.
 */

public class PIDTurn extends Command{
    float targetdegree;
    float power;
    Robot robot;
    PIDCalc pid;

    //degree in relation to current position
    public PIDTurn(float degree, float power, Robot robot, LinearOpMode opmode){
        super(opmode);
        targetdegree = degree;
        this.power = power;
        this.robot = robot;
        pid = new PIDCalc();
    }

    @Override
    public boolean execute(AsyncTask commandThread) {
        //fucking triangles oh you can just angular velocity it nvm
        pid.calcError(robot.turnMotors.getMotor(0).getCurrentPosition(), );
        //convert motor distance to fucking math so then you can input it as distance
        //distance one motor has to travel = half of the triangle
        //distance the other motor has to travel = the other half of the triangle
        //im pretty damn sure you can do it with math
        for() {


            robot.turnMotors[0].goForDistance();

        }
        return true;
    }
}
