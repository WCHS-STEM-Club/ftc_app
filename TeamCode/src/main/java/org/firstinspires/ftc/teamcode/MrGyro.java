package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.sensors.Sensor;

/**
 * Sensor that detects orientation.
 */
public class MrGyro implements Sensor {

  private ModernRoboticsI2cGyro gyro;

  public MrGyro(ModernRoboticsI2cGyro gyro) {
    this.gyro = gyro;
  }

  public MrGyro(HardwareMap hwMap, String deviceName) {
    this.gyro = hwMap.get(ModernRoboticsI2cGyro.class, deviceName);
  }

  /**
   * Get the heading of the gyroscope.
   *
   * @return Heading of the gyro
   */
  @Override
  public Integer getSensorValue() {
    return gyro.getHeading();
  }

  @Override
  public boolean calibrate() {
    gyro.calibrate();
    while (gyro.isCalibrating()) {
    }
    return !gyro.isCalibrating(); // False if it didn't work right
  }
}