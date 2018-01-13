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

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

public class MockColorSensor implements NormalizedColorSensor, SwitchableLight {

  public NormalizedRGBA color;
  public Manufacturer manufacturer;
  public String deviceName;
  public int version;
  public boolean light;

  public MockColorSensor() {
    this.resetDeviceConfigurationForOpMode();
  }

  @Override
  public NormalizedRGBA getNormalizedColors() {
    return this.color;
  }

  @Override
  public Manufacturer getManufacturer() {
    return this.manufacturer;
  }

  @Override
  public String getDeviceName() {
    return this.deviceName;
  }

  @Override
  public String getConnectionInfo() {
    return ""; // TODO: Figure this out
  }

  @Override
  public int getVersion() {
    return this.version;
  }

  @Override
  public void resetDeviceConfigurationForOpMode() {
    this.color = new NormalizedRGBA();
    this.color.red = 0;
    this.color.blue = 0;
    this.color.green = 0;
    this.manufacturer = Manufacturer.Other;
    this.deviceName = "mockColorSensor";
    this.version = 0;
    this.light = false;
  }

  @Override
  public void close() {
    // TODO: Find out what close does and how to implement it
  }

  @Override
  public void enableLight(boolean enable) {
    this.light = enable;
  }

  @Override
  public boolean isLightOn() {
    return this.light;
  }
}