package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * Command to drive the robot forward for some amount of time. Before use, the gyro should have been
 * calibrated. Calibration does not have to be immediately before use.
 */
public class DriveForDistEst extends Command {

  private float seconds;
  private float power;
  private Robot robot;

  /**
   * Constructor
   *
   * @param distance The number of decimeters to travel
   * @param speed The robot's speed in decimeters/second at full power
   * @param power The motor's power, on a scale from 0 to 1
   * @param robot Provides access to the motors
   */
  public DriveForDistEst(float distance, float speed, float power, Robot robot) {
    float speedAtPower = speed * power; // Assume that power is linear here
    this.seconds = distance / speedAtPower; // Time from distance and speed
    this.power = -1 * power;
    this.robot = robot;
  }

  @Override
  boolean execute() {
    return new DriveForTime(seconds, power, robot).start();
  }
}
