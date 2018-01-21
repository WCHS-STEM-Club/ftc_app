package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

import opmodes.LinearOpMode;

/**
 * Created by rachel on 1/20/2018.
 */

//in theory you could register "grip" as an input and adjust there but who really needs that
public class Grab extends Command{
    Robot robot;
    public Grab(Robot robot){
    }

    boolean execute() {
        robot.getServo("claw").converge();
        robot.getServo("claw").setAngle(100);
        return false;
    }
}
