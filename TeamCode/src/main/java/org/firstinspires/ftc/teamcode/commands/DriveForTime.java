package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Command to drive the robot forward for some amount of time
 */
public class DriveForTime extends Command {

  private float seconds;
  private float power;
  private Robot robot;

  /**
   * Constructor
   *
   * @param seconds The number of seconds to drive for
   * @param power The motor's power, on a scale from 0 to 1
   * @param robot Provides access to the motors
   */
  public DriveForTime(float seconds, float power, Robot robot) {
    this.seconds = seconds;
    this.power = power;
    this.robot = robot;
  }

  @Override
  boolean execute() {
    ElapsedTime runtime = new ElapsedTime();
    runtime.reset();

    robot.forwardMotors.setPower(power);

    // Run while we still have time to wait and the command was not cancelled
    // Any commands that have loops should have isCancelled as a condition in the loop
    while (runtime.milliseconds() / 1000 < this.seconds) {
      // Loops within commands should use yield so that the command doesn't take all the
      // processing power doing nothing
      Thread.yield();
    }

    robot.forwardMotors.brake();

    // Return false if the thread was cancelled, true if it was not.
    // This is because cancellation could cause the thread to end early.
    return true;
  }
}
