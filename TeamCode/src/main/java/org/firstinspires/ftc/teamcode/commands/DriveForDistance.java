package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;
import com.nathanvarner.pid.PID;
import com.nathanvarner.units.Unit;
import com.nathanvarner.units.Units;
import opmodes.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.MrGyro;

/**
 * Command to drive the robot forward for some distance.
 */
public class DriveForDistance extends Command {

  private double distance;
  private double power;
  private Robot robot;
  private Unit unit;
  private MrGyro gyro;
  private Telemetry telemetry;

  private final double kp = 0;
  private final double ki = 0;
  private final double kd = 0;

  /**
   * Full constructor
   *
   * @param distance The distance in units for the robot to drive
   * @param unit The unit that distance is in
   * @param encoderUnit The unit that the encoder uses, like {@link Units#tetrixEncoder}
   * @param power The motors' power, scale from 0 to 1
   * @param robot Provides access to the motors
   */
  public DriveForDistance(double distance, Unit unit, Unit encoderUnit, double power, Telemetry telemetry, Robot robot) {
    this.distance = distance;
    this.power = power;
    this.robot = robot;
    this.unit = unit;
    this.gyro = (MrGyro) robot.getSensor("gyro");
    this.telemetry = telemetry;
  }

  /**
   * By default uses {@link Units#tetrixEncoder} as the encoder units
   *
   * @param distance The distance in for the robot to drive
   * @param unit The unit that distance is in
   * @param power The motors' power, scale from 0 to 1
   * @param robot Provides access to the motors
   * @param opMode The LinearOpMode in which the command is being run
   */
  public DriveForDistance(double distance, Unit unit, double power, Robot robot, LinearOpMode opMode) {
    this.distance = distance;
    this.power = power;
    this.robot = robot;
    this.unit = unit;
  }

  /**
   * By default uses {@link Units#tetrixEncoder} as the encoder units and {@link Units#decimeter} as the input units
   *
   * @param distance The distance in for the robot to drive in decimeters
   * @param power The motors' power, scale from 0 to 1
   * @param robot Provides access to the motors
   * @param opMode The LinearOpMode in which the command is being run
   */
  public DriveForDistance(double distance, double power, Robot robot, LinearOpMode opMode) {
    this.distance = distance;
    this.power = power;
    this.robot = robot;
    this.unit = Units.decimeter;
  }

  @Override
  boolean execute() {
    gyro.calibrate();

    PID pid = new PID(kp, ki, kd, 0);

    robot.getTurnMotor(1).goForDistance(distance, this.unit, power);
    robot.getTurnMotor(0).goForDistance(distance, this.unit, power);

    while (robot.forwardMotors.isBusy()) {
      int gyroResult = (int) gyro.read();
      double error = pid.pid(gyroResult);

//      robot.getTurnMotor(0).disableEncoders();
//      robot.getTurnMotor(1).disableEncoders();

//      robot.getTurnMotor(0).setPower((float) (power - error));
//      robot.getTurnMotor(1).setPower((float) (power + error));

      robot.getTurnMotor(0).setPower(0.5f);
      robot.getTurnMotor(1).setPower(0.5f);

      telemetry.addData("Power", power);
      telemetry.addData("Error", error);
      telemetry.addData("MrGyro", gyroResult);
      telemetry.update();
    }

    robot.forwardMotors.brake();

    // Disable run to position
    robot.forwardMotors.useEncoders();

    return true;
  }
}
