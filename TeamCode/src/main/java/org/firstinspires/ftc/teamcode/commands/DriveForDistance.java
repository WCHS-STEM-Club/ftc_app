package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * Command to drive the robot forward for some distance.
 */
public class DriveForDistance extends Command {

  private float distance;
  private float power;
  private Robot robot;

  /**
   * Constructor
   *
   * @param distance The distance in decimeters (1/10th of a meter) for the robot to drive
   * @param power The motors' power, scale from 0 to 1
   * @param robot Provides access to the motor
   */
  public DriveForDistance(float distance, float power, Robot robot) {
    this.distance = distance;
    this.power = power;
    this.robot = robot;
  }

  @Override
  boolean execute() {
    robot.forwardMotors.goForDistance(distance, power);

    while (robot.forwardMotors.isBusy()) {
      Thread.yield();
    }

    robot.forwardMotors.brake();

    // Disable run to position
    robot.forwardMotors.useEncoders();

    return true;
  }
}
