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

  /**
   * Stores the calibration in a String if possible. I recommend using JSON to save the data, but no specific
   * implementation is required.
   * @return The calibration string, or an empty string if the operation is not supported.
   */
  String saveCalibration();


  /**
   * Load back the calibration from {@link Sensor#saveCalibration}. Because calibration will be loaded at the start of
   * matches, it should never result in any error or exception. If there is a failure, this method should always set the
   * calibration to default or the original, then return false. If this operation is not supported, false should also be
   * returned, but it is recommended to also log something so that it is obvious that the operation is not supported.
   * @param calibration The result of the {@link Sensor#saveCalibration} method.
   * @return True if the loading was successful, false if not.
   */
  boolean loadCalibration(String calibration);
}