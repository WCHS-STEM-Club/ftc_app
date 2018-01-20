package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

import opmodes.LinearOpMode;

/**
 * Created by rachel on 1/20/2018.
 */

//in theory
public class Release extends Command{
    Robot robot;
    public Release(Robot robot){
        this.robot = robot;
    }

    boolean execute() {
        robot.getServo("claw").converge();
        robot.getServo("claw").setAngle(80);
        return false;
    }
}
