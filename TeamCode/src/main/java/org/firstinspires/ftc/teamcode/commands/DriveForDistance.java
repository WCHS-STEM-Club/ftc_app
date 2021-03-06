package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;
import opmodes.LinearOpMode;
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
   * @param robot Provides access to the motors
   * @param opMode The LinearOpMode in which the command is being run
   */
  public DriveForDistance(float distance, float power, Robot robot, LinearOpMode opMode) {
    super(opMode);
    this.distance = distance;
    this.power = power;
    this.robot = robot;
  }

  @Override
  public boolean execute(AsyncTask commandThread) {
    robot.forwardMotors.goForDistance(distance, power);

    while (robot.forwardMotors.isBusy() && !commandThread.isCancelled()) {
      Thread.yield();
    }

    robot.forwardMotors.brake();

    // Disable run to position
    robot.forwardMotors.useEncoders();

    return true;
  }
}
