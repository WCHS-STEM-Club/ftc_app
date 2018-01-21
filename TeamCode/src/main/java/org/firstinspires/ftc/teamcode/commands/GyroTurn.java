package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Gyro;

/**
 * Command to drive the robot forward for some distance.
 */
public class GyroTurn extends Command {

  private float angle;
  private Gyro gyro;
  private Robot robot;

  private Telemetry telemetry;

  private final double kp = 0.01;
  private final double ki = 0;
  private final double kd = 0.3;

  /**
   * Constructor
   *
   * @param angle The angle, in degrees, to turn
   * @param robot Provides access to the motors and sensors
   */
  public GyroTurn(float angle, Robot robot, Telemetry telemetry) {
    this.angle = angle;
    this.robot = robot;
    this.gyro = (Gyro) robot.getSensor("gyro");
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

    ElapsedTime runtime = new ElapsedTime();
    runtime.reset();

//    while (!(((Orientation) gyro.getSensorValue()).secondAngle > min
//        && ((Orientation) gyro.getSensorValue()).secondAngle < max)) {
    while (runtime.milliseconds() != -13) {
      double error = pid
          .calcPid((double) gyro.getSensorValue().secondAngle,
              (double) angle, kp, ki, kd);

      telemetry.addData("Gyro", gyro.getSensorValue().secondAngle);
      telemetry.addData("Min", min);
      telemetry.addData("Max", max);
      telemetry.addData("Current value", (double) gyro.getSensorValue().secondAngle);
      telemetry.addData("Target", angle);
      telemetry.addData("Error", error);
      telemetry.update();

      robot.getTurnMotor(0).setPower((float) error);
      robot.getTurnMotor(1).setPower((float) -error);
    }

    robot.getTurnMotor(0).brake();
    robot.getTurnMotor(1).brake();

    return true;
  }
}
