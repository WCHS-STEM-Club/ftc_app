/*
MIT License

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

package com.nathanvarner.units;

public class Units {
  /*
   * Units of length
   * Base unit: meter
   */
  public static Unit meter = new Unit(null, 1, 1, "m");

  // Metric units
  public static Unit millimeter = new Unit(meter, 1, 1000, "mm");
  public static Unit centimeter = new Unit(meter, 1, 100, "cm");
  public static Unit decimeter =  new Unit(meter, 1, 10, "dm");
  public static Unit decameter =  new Unit(meter, 10, 1, "dam");
  public static Unit hectometer = new Unit(meter, 100, 1, "hm");
  public static Unit kilometer =  new Unit(meter, 1000, 1, "km");

  // Imperial units
  public static Unit inch = new Unit(meter, 1, 39.37008, "in");
  public static Unit foot = new Unit(meter, 1, 3.28084, "ft");
  public static Unit mile = new Unit(meter, 1609.344, 1, "mi");

  /*
   * Units of rotation
   * Base unit: rotations
   */
  public static Unit rotation = new Unit(null, 1, 1, "rot");

  // Standard units
  public static Unit degree = new Unit(rotation, 1, 360, "deg");
  public static Unit radian = new Unit(rotation, 1, 2 * Math.PI, "rad");

  // Encoders
  public static Unit tetrixEncoder = new Unit(rotation, 1, 1440, "Tetrix clx");

  /*
   * Units of temperature
   * Base unit: °C
   */
  public static Unit celsius = new Unit(null, 1, 1, "°C");

  // SI
  public static Unit kelvin = new Unit(celsius, 1, 1, 273.15, "°K");

  // Imperial
  public static Unit fahrenheit = new Unit(celsius, 1, 1.8, 32, "°F");
}
