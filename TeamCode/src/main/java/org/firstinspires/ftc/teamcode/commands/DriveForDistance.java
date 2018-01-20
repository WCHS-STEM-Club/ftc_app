package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Gyro;

/**
 * Command to drive the robot forward for some distance.
 */
public class DriveForDistance extends Command {

  private float distance;
  private float power;
  private Robot robot;
  private Gyro gyro;
  private Telemetry telemetry;

  private final double kp = 0.001;
  private final double ki = 0;
  private final double kd = 0.03;

  /**
   * Constructor
   *
   * @param distance The distance in decimeters (1/10th of a meter) for the robot to drive
   * @param power The motors' power, scale from 0 to 1
   * @param robot Provides access to the motor
   */
  public DriveForDistance(float distance, float power, Telemetry telemetry, Robot robot) {
    this.distance = distance;
    this.power = power;
    this.robot = robot;
    this.gyro = (Gyro) robot.getSensor("gyro");
    this.telemetry = telemetry;
  }

  @Override
  boolean execute() {
    gyro.calibrate();

    PidController pid = new PidController();

    robot.forwardMotors.goForDistance(distance, power);

    while (robot.forwardMotors.isBusy()) {
      double error = pid.calcPid((double) gyro.getSensorValue().secondAngle, 0, kp, ki, kd);

      robot.getTurnMotor(0).setPower((float) (power - error));
      robot.getTurnMotor(1).setPower((float) (power + error));

      telemetry.addData("Power", power);
      telemetry.addData("Error", error);
      telemetry.addData("Gyro", (double) gyro.getSensorValue().secondAngle);

      Thread.yield();
    }

    robot.forwardMotors.brake();

    // Disable run to position
    robot.forwardMotors.useEncoders();

    return true;
  }
}
