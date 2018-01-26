package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MRGyro extends Gyro {

  /**
   * Constructor
   *
   * @param gyro The gyro sensor to use, alternatively get from HW map using {@link
   * MRGyro#MRGyro(HardwareMap, String)}.
   */
  public MRGyro(ModernRoboticsI2cGyro gyro) {
    this.gyro = gyro;
  }

  /**
   * Constructor
   *
   * @param hwMap Hardware map
   * @param deviceName Name of the device in the config
   */
  public MRGyro(HardwareMap hwMap, String deviceName) {
    this.gyro = hwMap.get(ModernRoboticsI2cGyro.class, deviceName);
  }

  /**
   * Calibrates the gyroscope. While calibrating, do not move the gyro!
   *
   * @return Success of calibration.
   */
  @Override
  public boolean calibrate() {
    ModernRoboticsI2cGyro mrGyro = ((ModernRoboticsI2cGyro) gyro);

    mrGyro.calibrate();
    while (mrGyro.isCalibrating()) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        return false;
      }
    }
    return true;
  }
}
