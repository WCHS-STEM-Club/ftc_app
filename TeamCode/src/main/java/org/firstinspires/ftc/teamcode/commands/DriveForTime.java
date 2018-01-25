package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.MrGyro;

/**
 * Command to drive the robot forward for some amount of time. Before use, the gyro should have been
 * calibrated. Calibration does not have to be immediately before use.
 */
public class DriveForTime extends Command {

  private float seconds;
  private float power;
  private Robot robot;
  private MrGyro gyro;

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
    this.gyro = (MrGyro) robot.getSensor("gyro");
  }

  @Override
  boolean execute() {
    // This is like calibration in that it will be used to reset the gyro to 0
    int gyroStart = gyro.getSensorValue();

    double kp = 0.01;
    double ki = 0;
    double kd = 0.3;

    PidController pid = new PidController();

    ElapsedTime runtime = new ElapsedTime();
    runtime.reset();

    robot.forwardMotors.setPower(power);

    // Run while we still have time to wait
    while (runtime.milliseconds() / 1000 < this.seconds) {
      double gyroResult = (double) gyro.getSensorValue() - gyroStart;
      while (gyroResult > 180) {
        gyroResult -= 360;
      }
      while (gyroResult <= -180) {
        gyroResult += 360;
      }

      double correction = pid.calcPid(gyroResult, 0, kp, ki, kd);

      float leftPower = (float) (power + correction);
      float rightPower = (float) (power - correction);

      float max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
      if (max > 1) {
        leftPower /= max;
        rightPower /= max;
      }

      robot.getTurnMotor(0).setPower(leftPower);
      robot.getTurnMotor(1).setPower(rightPower);
    }

    robot.forwardMotors.brake();

    // Return false if the command was unsuccessful, true if it was not.
    return true;
  }
}
