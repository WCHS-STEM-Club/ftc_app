package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Modern Robotics gyro, detects heading, orientation, and rotational velocity
 */
public class MrGyro extends Gyro implements Sensor {

  /**
   * Create an MrGyro from an instance of {@link ModernRoboticsI2cGyro}.
   * @param gyro The gyroscope to use for the MrGyro
   */
  public MrGyro(ModernRoboticsI2cGyro gyro) {
    this.m_gyro = gyro;
  }

  /**
   * Create an MrGyro from a hardware map and device name.
   * @param hwMap The hardware map containing the gyroscope.
   * @param deviceName The name of the gyroscope as per the configuration on the robot.
   */
  public MrGyro(HardwareMap hwMap, String deviceName) {
    this.m_gyro = hwMap.get(ModernRoboticsI2cGyro.class, deviceName);
  }

  /**
   * Calibrate the gyroscope so that the current heading is 0. Note that the gyroscope must not be rotating at all or
   * else the measurements will drift significantly.I would recommend not moving the robot at all while the gyro is
   * calibrating.
   * @return True if the calibration was a success, false if it failed
   */
  @Override
  public boolean calibrate() {
    ((ModernRoboticsI2cGyro)m_gyro).calibrate();
    while (((ModernRoboticsI2cGyro)m_gyro).isCalibrating()) {
      // TODO: Work through the code to make sure that this is correct
      Thread.yield();
    }
    return !((ModernRoboticsI2cGyro)m_gyro).isCalibrating(); // False if it didn't work right
  }
}