package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;
import com.nathanvarner.pid.PID;
import com.nathanvarner.units.Unit;
import com.nathanvarner.units.Units;
import opmodes.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.MrGyro;

/**
 * Command to drive the robot forward for some distance.
 */
public class GyroTurn extends Command {

  private double angle;
  private double power;
  private Unit angleUnit;
  private MrGyro gyro;
  private Robot robot;

  private Telemetry telemetry;

  private final double kp = 0.01;
  private final double ki = 0;
  private final double kd = 0.3;

  /**
   * Constructor
   *
   * @param angle The angle to turn in units
   * @param angleUnit The units that the angle is in
   * @param power The motors' power, scale from 0 to 1
   * @param robot Provides access to the motors and sensors
   * @param telemetry The telemetry used to send back data
   */
  public GyroTurn(double angle, Unit angleUnit, double power, Robot robot, Telemetry telemetry) {
    this.angle = angle;
    this.angleUnit = angleUnit;
    this.power = power;
    this.robot = robot;
    this.gyro = (MrGyro) robot.getSensor("gyro");
    this.telemetry = telemetry;
  }

  @Override
  boolean execute() {
    gyro.calibrate();

    PID pid = new PID(kp, ki, kd, angle);

    robot.forwardMotors.useEncoders();

    double range = 1;
    double max = angle + (range / 2);
    double min = angle - (range / 2);

    ElapsedTime runtime = new ElapsedTime();
    runtime.reset();

    // TODO: Make sure that I really want z here
    float heading = gyro.getOrientation(Units.degree).thirdAngle;

    while (heading < min || heading > max) {
      // TODO: Still make sure I want z...
      heading = gyro.getOrientation(Units.degree).thirdAngle;

      double error = pid.pid(heading);

      telemetry.addData("MrGyro", heading);
      telemetry.addData("Min", min);
      telemetry.addData("Max", max);
      telemetry.addData("Current value", heading);
      telemetry.addData("Target", angle);
      telemetry.addData("Error", error);
      telemetry.update();

      robot.getTurnMotor(0).setPower(error);
      robot.getTurnMotor(1).setPower(-error);
    }

    robot.getTurnMotor(0).brake();
    robot.getTurnMotor(1).brake();

    return true;
  }
}
