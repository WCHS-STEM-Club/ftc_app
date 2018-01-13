/*
* MIT License

Copyright (c) 2018 Nathan Varner

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.firstinspires.ftc.teamcode;

import static org.junit.Assert.fail;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import org.firstinspires.ftc.teamcode.sensors.ColorSensor;
import org.junit.Before;
import org.junit.Test;

public class ColorSensorTest {

  private MockColorSensor colorSensorHardware;
  private ColorSensor colorSensor;

  /**
   * Run a setup before each test.
   */
  @Before
  public void beforeEach() {
    colorSensorHardware = new MockColorSensor();
  }

  /**
   * Ensure that calibration is working properly
   */
  @Test
  public void calibrate() {
    NormalizedRGBA color = colorSensorHardware.color;
    color.red = 0.5f;
    color.green = 0.5f;
    color.blue = 0.5f;

    colorSensor.calibrate();

    color.red = 0.25f;
    color.green = 0.25f;
    color.green = 0.25f;
    color = colorSensor.getSensorValue();

    if (color.red != 0.5f || color.green != 0.5f && color.blue != 0.5f) {
      fail("Did not calibrate properly");
    }
  }
}
