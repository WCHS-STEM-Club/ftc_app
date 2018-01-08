package org.firstinspires.ftc.teamcode.sensors;

/**
 * A class that is extended and used for generic sensors. It has two methods,
 * {@link Sensor#getSensorValue()} and {@link Sensor#calibrate()}. The first must be implemented. It
 * is the method that will be called to read the sensor's value. The second may be implemented if
 * desired, but is optional. It will be called to calibrate the sensor. If the sensor needs some
 * input to set itself up, it is recommended to create a constructor. There is no super method to
 * call.
 */
public abstract class Sensor {

  /**
   * This method must be implemented to return the sensor's result. For example, a color sensor
   * should return a color, an ultrasonic sensor should return a distance, and a touch sensor should
   * return a boolean. When calling this method, remember to cast to the result type if needed.
   */
  public abstract Object getSensorValue();

  /**
   * This method can be implemented and calibrates the sensor. For example, a light sensor may read
   * ambient light and a gyro might reset forward. The return is a boolean for if the calibration
   * was a success.
   *
   * @return True if the sensor was successfully calibrated, false if it was not.
   */
  public boolean calibrate() {
    return true; // So that sensors that needn't calibration needn't @Override
  }
}