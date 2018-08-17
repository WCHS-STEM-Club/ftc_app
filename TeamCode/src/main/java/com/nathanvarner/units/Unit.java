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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Unit {
  private Unit m_baseUnit;
  private double m_xBaseUnits;
  private double m_xUnitsPerXBaseUnits;
  private double m_intercept;
  private String m_unitAbbr;

  // Decimal format for numberAsString, only 3 decimals
  private static DecimalFormat decimalFormat = new DecimalFormat("0.###",
      DecimalFormatSymbols.getInstance(Locale.ENGLISH));

  /**
   * Create a new Unit
   * @param baseUnit A base unit must be chosen for each "type" of unit (eg length, rotation). If this is the base
   *                 unit, pass null. Otherwise, some unit must be chosen as the base unit. It is recommended to chose
   *                 the most universal unit. For example, a rotation is a good example of a base unit for rotation.
   *                 In the case that there is no "universal" unit, best practice would be to use the metric base
   *                 unit. For example, a good base unit for temperature is Celsius. Depending on the exact
   *                 application, it may make more sense to use imperial or SI base units instead.
   * @param xBaseUnits This is some number of baseUnits. This could be any number, but it is recommended to choose a
   *                   number that works well with xUnitsPerXBaseUnits. For example, if the base unit is a meter and
   *                   this unit is a decimeter, xBaseUnits could be 1 and xUnitsPerXBaseUnits could be 10 because
   *                   there are 10 decimeters to 1 meter.
   * @param xUnitsPerXBaseUnits This is the number of this unit that equals xBaseUnits base units. For example, if the
   *                            base unit is a meter and this unit is a decimeter, xBaseUnits could be 1 and
   *                            xUnitsPerXBaseUnits could be 10 because there are 10 decimeters to 1 meter.
   * @param unitAbbr This is the abbreviation for the unit. For example, the abbreviation for meters is "m", and the
   *                 abbreviation for seconds is "s".
   */
  public Unit(Unit baseUnit, double xBaseUnits, double xUnitsPerXBaseUnits, String unitAbbr) {
    m_baseUnit = baseUnit;
    m_xBaseUnits = xBaseUnits;
    m_xUnitsPerXBaseUnits = xUnitsPerXBaseUnits;
    m_unitAbbr = unitAbbr;
    m_intercept = 0;
  }

  /**
   * Create a new Unit
   * @param baseUnit A base unit must be chosen for each "type" of unit (eg length, rotation). If this is the base
   *                 unit, pass null. Otherwise, some unit must be chosen as the base unit. It is recommended to chose
   *                 the most universal unit. For example, a rotation is a good example of a base unit for rotation.
   *                 In the case that there is no "universal" unit, best practice would be to use the metric base
   *                 unit. For example, a good base unit for temperature is Celsius. Depending on the exact
   *                 application, it may make more sense to use imperial or SI base units instead.
   * @param xBaseUnits This is some number of baseUnits. This could be any number, but it is recommended to choose a
   *                   number that works well with xUnitsPerXBaseUnits. For example, if the base unit is a meter and
   *                   this unit is a decimeter, xBaseUnits could be 1 and xUnitsPerXBaseUnits could be 10 because
   *                   there are 10 decimeters to 1 meter.
   * @param xUnitsPerXBaseUnits This is the number of this unit that equals xBaseUnits base units. For example, if the
   *                            base unit is a meter and this unit is a decimeter, xBaseUnits could be 1 and
   *                            xUnitsPerXBaseUnits could be 10 because there are 10 decimeters to 1 meter.
   * @param intercept This is added when converting from the base unit and subtracted when converting to the base
   *                  unit. For example, if this unit is Kelvin and the base unit is Celsius, the intercept is 273.15.
   * @param unitAbbr This is the abbreviation for the unit. For example, the abbreviation for meters is "m", and the
   *                 abbreviation for seconds is "s".
   */
  public Unit(Unit baseUnit, double xBaseUnits, double xUnitsPerXBaseUnits, double intercept, String unitAbbr) {
    m_baseUnit = baseUnit;
    m_xBaseUnits = xBaseUnits;
    m_xUnitsPerXBaseUnits = xUnitsPerXBaseUnits;
    m_unitAbbr = unitAbbr;
    m_intercept = intercept;
  }

  /**
   * @return The base unit of this unit, even if this unit is the base unit.
   */
  public Unit getBaseUnit () {
    return m_baseUnit == null ? this : m_baseUnit;
  }

  /**
   * Converts a number in this unit to the base unit. For example, if this unit is decimeters and the base unit is
   * meters, an input of 10 would return 1.
   * @param numberInUnit The number in this unit.
   * @return The equivalent in the base unit.
   */
  double toBaseUnit(double numberInUnit) {
    return (numberInUnit - m_intercept) * m_xBaseUnits / m_xUnitsPerXBaseUnits;
  }

  /**
   * Converts a number in the base unit to this unit. For example, if this unit is decimeters and the base unit is
   * meters, an input of 1 woulod return 10.
   * @param numberInBaseUnit The number in the base unit.
   * @return The equivalent in this unit.
   */
  double fromBaseUnit(double numberInBaseUnit) {
    return (numberInBaseUnit * m_xUnitsPerXBaseUnits / m_xBaseUnits) + m_intercept;
  }

  /**
   * Converts between this unit and any other unit with the same base unit. If the base unit isn't the same, an
   * IllegalArgumentException is thrown.
   * @param numberInUnit The number in this unit.
   * @param newUnit The unit to convert to, sharing the same base unit.
   * @return The equivalent in the newUnit.
   */
  public double to(double numberInUnit, Unit newUnit) {
    // Check that this base unit equals the other base unit.
    if (!getBaseUnit().equals(newUnit.getBaseUnit())) {
      throw new IllegalArgumentException(String.format(
          "Cannot convert between '%s' and '%s' because they do not share a base unit.",
          toString(), newUnit.toString()
      ));
    }

    return newUnit.fromBaseUnit(toBaseUnit(numberInUnit));
  }

  public double toManual(double numberInUnit, double xThisUnits, double xThoseUnits) {
    return (xThoseUnits / xThisUnits) * numberInUnit;
  }

  /**
   * Formats the number with three decimal places and the unit abbreviation.
   * @param numberInUnit The number to format.
   * @return The string with a formatted number.
   */
  public String numberAsString(double numberInUnit) {
    return String.format("%s %s", decimalFormat.format(numberInUnit), m_unitAbbr);
  }

  @Override
  public String toString() {
    return m_unitAbbr;
  }
}
