/*
 * Copyright 2018 Nathan Varner, All Rights Reserved
 */

package com.nathanvarner.units;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Unit {
  public Unit baseUnit;
  public double xBaseUnits;
  public double xUnitsPerXBaseUnits;
  public String unitAbbr;

  private static DecimalFormat decimalFormat = new DecimalFormat("0.######",
      DecimalFormatSymbols.getInstance(Locale.ENGLISH));

  public Unit(Unit baseUnit, double xBaseUnits, double xUnitsPerXBaseUnits, String unitAbbr) {
    this.baseUnit = baseUnit;
    this.xBaseUnits = xBaseUnits;
    this.xUnitsPerXBaseUnits = xUnitsPerXBaseUnits;
    this.unitAbbr = unitAbbr;
  }

  public double toBaseUnit(double numberInUnit) {
    return numberInUnit * this.xBaseUnits / this.xUnitsPerXBaseUnits;
  }

  public double fromBaseUnit(double numberInBaseUnit) {
    return numberInBaseUnit * this.xUnitsPerXBaseUnits / this.xBaseUnits;
  }

  public double to(double numberInUnit, Unit newUnit) {
    return newUnit.fromBaseUnit(toBaseUnit(numberInUnit));
  }

  public String numberAsString(double numberInUnit) {
    return decimalFormat.format(numberInUnit) + " " + this.unitAbbr;
  }
}
