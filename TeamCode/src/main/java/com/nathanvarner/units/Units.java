/*
 * Copyright 2018 Nathan Varner, All Rights Reserved
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
  public static Unit radian = new Unit(rotation, 1, 1 / (2 * Math.PI), "rad");

  // Encoders
  public static Unit tetrixEncoder = new Unit(rotation, 1, 1440, "Tetrix enc");
}
