package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

/**
 * Sensor that detects color.
 */
public class ColorSensor implements Sensor {

  private NormalizedColorSensor colorSensor;

  private float maxR, maxG, maxB = 1;

  /**
   * Constructor
   *
   * @param colorSensor The color sensor to use, alternatively get from HW map using {@link
   * ColorSensor#ColorSensor(HardwareMap, String, boolean)}.
   * @param useLight Whether or not to use a light when reading colors, ignored if there is no
   * light
   */
  public ColorSensor(NormalizedColorSensor colorSensor, boolean useLight) {
    this.colorSensor = colorSensor;

    // If the sensor supports using a light and the user wants to, turn on the light
    if (colorSensor instanceof SwitchableLight && useLight) {
      ((SwitchableLight) colorSensor).enableLight(true);
    }
  }

  /**
   * Constructor
   *
   * @param hwMap Hardware map
   * @param deviceName Name of the device in the config
   * @param useLight Whether or not to use a light when reading colors, ignored if there is no
   * light
   */
  public ColorSensor(HardwareMap hwMap, String deviceName, boolean useLight) {
    this.colorSensor = hwMap.get(NormalizedColorSensor.class, deviceName);

    // If the sensor supports using a light and the user wants to, turn on the light
    if (colorSensor instanceof SwitchableLight && useLight) {
      ((SwitchableLight) colorSensor).enableLight(true);
    }
  }

  /**
   * Get the color that the color sensor is currently seeing. Comes normalized based on the results
   * of calibration using {@link ColorSensor#calibrate()}.
   *
   * @return {@link NormalizedRGBA}, contains R(ed) G(reen) B(lue) and A(lpha)/transparency, A
   * should be irrelevant.
   */
  @Override
  public NormalizedRGBA getSensorValue() {
    NormalizedRGBA color = this.colorSensor.getNormalizedColors();
    color.red /= this.maxR;
    color.green /= this.maxG;
    color.blue /= this.maxB;

    return color;
  }

  /**
   * Calibrates the sensor, assuming that the sensor is seeing the brightest white possible under
   * current lighting conditions.
   */
  @Override
  public boolean calibrate() {
    NormalizedRGBA color = this.colorSensor.getNormalizedColors();
    this.maxR = color.red;
    this.maxG = color.green;
    this.maxB = color.blue;

    return true;
  }
}
