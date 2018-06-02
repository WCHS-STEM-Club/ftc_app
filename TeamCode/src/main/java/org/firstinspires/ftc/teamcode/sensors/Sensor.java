package org.firstinspires.ftc.teamcode.sensors;

/**
 * A class that is extended and used for generic sensors. The only function, {@link Sensor#calibrate()} may be
 * implemented if desired, but is optional. It will be called to calibrate the sensor. If the sensor needs some input to
 * set itself up, it is recommended to create a constructor. There is no super method to call.
 */
public interface Sensor {
  /**
   * This method can be implemented and calibrates the sensor. For example, a light sensor may read
   * ambient light and a gyro might reset forward. The return is a boolean for if the calibration
   * was a success.
   *
   * @return True if the sensor was successfully calibrated, false if it was not.
   */
  boolean calibrate();
}