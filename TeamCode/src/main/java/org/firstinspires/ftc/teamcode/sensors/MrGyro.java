package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Sensor that detects orientation.
 */
public class MrGyro implements Sensor {

  public enum ReturnType {
    HEADING,
    ANGULAR_VELOCITY
  }

  private ReturnType returnType;
  private ModernRoboticsI2cGyro gyro;

  public MrGyro(ModernRoboticsI2cGyro gyro, ReturnType returnType) {
    this.gyro = gyro;
    this.returnType = returnType;
  }

  public MrGyro(HardwareMap hwMap, String deviceName, ReturnType returnType) {
    this.gyro = hwMap.get(ModernRoboticsI2cGyro.class, deviceName);
    this.returnType = returnType;
  }

  /**
   * Get the gyroscope's readings, based on the return type in the constructor
   *
   * @return Return value
   */
  @Override
  public Number getSensorValue() {
    switch (returnType) {
      case HEADING:
        return (float) gyro.getHeading();
      case ANGULAR_VELOCITY:
        return gyro.getAngularVelocity(AngleUnit.DEGREES).zRotationRate;
    }

    return 0.0f;
  }

  @Override
  public boolean calibrate() {
    gyro.calibrate();
    while (gyro.isCalibrating()) {
      Thread.yield();
    }
    return !gyro.isCalibrating(); // False if it didn't work right
  }
}