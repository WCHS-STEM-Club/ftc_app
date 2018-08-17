package org.firstinspires.ftc.teamcode.sensors;

import android.util.JsonReader;
import android.util.JsonWriter;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import org.firstinspires.ftc.robotcontroller.internal.Persistence;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Sensor that detects color. Its calibration can be saved, and because it must be calibrated with
 * {@link opmodes.CalibrateColor}, it should not be calibrated right before a match. Instead, it should be calibrated
 * ahead of time using the OpMode and then loaded back later because it is saved to {@link Persistence}.
 */
public class ColorSensor implements Sensor {

  private NormalizedColorSensor m_colorSensor;

  private float m_maxR, m_maxG, m_maxB;

  /**
   * Constructor
   *
   * @param colorSensor The color sensor to use, alternatively get from HW map using {@link
   * ColorSensor#ColorSensor(HardwareMap, String, boolean)}.
   * @param useLight Whether or not to use a light when reading colors, ignored if there is no
   * light
   */
  public ColorSensor(NormalizedColorSensor colorSensor, boolean useLight) {
    this.m_colorSensor = colorSensor;

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
    this.m_colorSensor = hwMap.get(NormalizedColorSensor.class, deviceName);

    // If the sensor supports using a light and the user wants to, turn on the light
    if (m_colorSensor instanceof SwitchableLight && useLight) {
      ((SwitchableLight) m_colorSensor).enableLight(true);
    }
  }

  /**
   * Get the color that the color sensor is currently seeing. Comes normalized based on the results
   * of calibration using {@link ColorSensor#calibrate()}.
   *
   * @return {@link NormalizedRGBA}, contains R(ed) G(reen) B(lue) and A(lpha)/transparency, A
   * should be irrelevant.
   */
  public NormalizedRGBA readColor() {
    NormalizedRGBA color = this.m_colorSensor.getNormalizedColors();
    color.red /= this.m_maxR;
    color.green /= this.m_maxG;
    color.blue /= this.m_maxB;

    return color;
  }

  /**
   * Calibrates the sensor, assuming that the sensor is seeing the brightest white possible under
   * current lighting conditions.
   */
  @Override
  public boolean calibrate() {
    NormalizedRGBA color = this.m_colorSensor.getNormalizedColors();
    this.m_maxR = color.red;
    this.m_maxG = color.green;
    this.m_maxB = color.blue;

    return true;
  }

  /**
   * Saves the color sensor calibration into JSON so that it can be loaded back later, during a match.
   * @return The JSON string needed to restore this calibration.
   */
  @Override
  public String saveCalibration() {
    // TODO: Test save/load calibration on the robot.
    StringWriter result = new StringWriter();
    JsonWriter json = new JsonWriter(result);

    try {
      json.beginObject();
      json.name("maxR").value(m_maxR);
      json.name("maxG").value(m_maxG);
      json.name("maxB").value(m_maxB);
      json.endObject();
      json.close();
    } catch  (IOException e) {
      e.printStackTrace();
    }
    return result.toString();
  }

  @Override
  public boolean loadCalibration(String calibration) {
    StringReader reader = new StringReader(calibration);
    JsonReader json = new JsonReader(reader);

    float maxR, maxG, maxB;
    maxR = maxG = maxB = 1;

    boolean success = true;

    try {
      json.beginObject();
      while (json.hasNext()) {
        String name = json.nextName();
        switch (name) {
          case "maxR":
            maxR = (float)json.nextDouble();
            break;
          case "maxG":
            maxG = (float)json.nextDouble();
            break;
          case "maxB":
            maxB = (float)json.nextDouble();
            break;
        }
      }
      json.close();
    } catch (IOException e) {
      maxR = maxG = maxB = 1;
      e.printStackTrace();
      success = false;
    }

    m_maxR = maxR;
    m_maxG = maxG;
    m_maxB = maxB;

    return success;
  }

}
