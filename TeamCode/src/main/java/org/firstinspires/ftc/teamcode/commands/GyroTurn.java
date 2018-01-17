package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;
import opmodes.LinearOpMode;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensor;

/**
 * Command to drive the robot forward for some distance.
 */
public class GyroTurn extends Command {

  private float angle;
  private float power;
  private Sensor gyro;
  private Robot robot;

  /**
   * Constructor
   *
   * @param angle The angle, in degrees, to turn
   * @param power The motors' power, scale from 0 to 1
   * @param robot Provides access to the motors and sensors
   * @param opMode The LinearOpMode in which the command is being run
   */
  public GyroTurn(float angle, float power, Robot robot, LinearOpMode opMode) {
    super(opMode);
    this.angle = angle;
    this.power = power;
    this.robot = robot;
    this.gyro = robot.getSensor("gyro");
  }

  @Override
  public boolean execute(AsyncTask commandThread) {
    robot.turnMotors[0].setPower(1);
    robot.turnMotors[1].setPower(-1);

    return true;
  }
}
