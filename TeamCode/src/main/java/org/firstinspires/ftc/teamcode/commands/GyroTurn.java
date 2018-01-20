package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensor;

/**
 * Command to drive the robot forward for some distance.
 */
public class GyroTurn extends Command {

  private float angle;
  private Sensor gyro;
  private Robot robot;

  private Telemetry telemetry;

  private double kp = 0.0027939;
  private double ki = 0.0046565;
  private double kd = 0.0046565;

  /**
   * Constructor
   *
   * @param angle The angle, in degrees, to turn
   * @param robot Provides access to the motors and sensors
   */
  public GyroTurn(float angle, Robot robot, Telemetry telemetry) {
    this.angle = angle;
    this.robot = robot;
    this.gyro = robot.getSensor("gyro");
    this.telemetry = telemetry;
  }

  @Override
  boolean execute() {
    gyro.calibrate();

    PidController pid = new PidController();

    robot.forwardMotors.useEncoders();

    float range = 10;
    float max = angle + (range / 2);
    float min = angle - (range / 2);
    while (!(((Orientation) gyro.getSensorValue()).secondAngle > min
        && ((Orientation) gyro.getSensorValue()).secondAngle < max)) {
      double error = pid
          .calcPid((double) ((Orientation) gyro.getSensorValue()).secondAngle,
              (double) angle, kp, ki, kd);

      telemetry.addData("Gyro", ((Orientation) gyro.getSensorValue()).secondAngle);
      telemetry.addData("Min", min);
      telemetry.addData("Max", max);
      telemetry
          .addData("Current value", (double) ((Orientation) gyro.getSensorValue()).secondAngle);
      telemetry.addData("Target", angle);
      telemetry.addData("Error", error);
      telemetry.update();

      robot.getTurnMotor(0).setPower((float) (0.001 * error));
      robot.getTurnMotor(1).setPower((float) (-0.001 * error));
    }
    telemetry.addData("Status", "terminated");
    telemetry.update();

    robot.getTurnMotor(0).brake();
    robot.getTurnMotor(1).brake();

    return true;
  }
}
