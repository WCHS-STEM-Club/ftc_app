package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

public class MoveKnocker extends Command {

  private Robot robot;
  private double degree;

  MoveKnocker(Robot robot, double degree) {
    this.robot = robot;
    this.degree = degree;
    }

    @Override
    public boolean execute() {
        robot.getServo("knock").setAngle(degree);
        return true;
    }
}
