package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Gyro;

/**
 * Command to drive the robot forward for some amount of time. Before use, the gyro should have been
 * calibrated. Calibration does not have to be immediately before use.
 */
public class DriveForTime extends Command {

  private float seconds;
  private float power;
  private Robot robot;
  private Gyro gyro;

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
    this.gyro = (Gyro) robot.getSensor("gyro");
  }

  @Override
  boolean execute() {
    // This is like calibration in that it will be used to reset the gyro to 0
    double gyroStart = gyro.getSensorValue().secondAngle;

    double kp = 0;
    double ki = 0;
    double kd = 0;

    PidController pid = new PidController();

    ElapsedTime runtime = new ElapsedTime();
    runtime.reset();

    robot.forwardMotors.setPower(power);

    // Run while we still have time to wait
    while (runtime.milliseconds() / 1000 < this.seconds) {
      double gyroResult = gyro.getSensorValue().secondAngle - gyroStart;
      double correction = pid.calcPid(gyroResult, 0, kp, ki, kd);

      float leftPower = (float) Range.clip(power + correction, -1.0, 1.0);
      float rightPower = (float) Range.clip(power - correction, -1.0, 1.0);

      robot.getTurnMotor(0).setPower(leftPower);
      robot.getTurnMotor(1).setPower(rightPower);

      // Loops within commands should use yield so that the command doesn't take all the
      // processing power
      Thread.yield();
    }

    robot.forwardMotors.brake();

    // Return false if the command was unsuccessful, true if it was not.
    return true;
  }
}
