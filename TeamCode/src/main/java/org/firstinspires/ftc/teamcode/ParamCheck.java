package org.firstinspires.ftc.teamcode;

import java.util.HashMap;

/**
 * Class with all static methods to check values, especially parameters.
 */
public class ParamCheck {

  /**
   * Checks an array and sees it it is of some length.
   *
   * @param array The array to check
   * @param len The length the array must be
   * @return If the array is of the set length
   */
  static boolean arrayOfLength(Object[] array, int len) {
    return array.length == len;
  }

  /**
   * Checks arrays for null elements.
   *
   * @param array The array to check
   * @return If the array has any null elements
   */
  static boolean containsNull(Object[] array) {
    // Will return true as soon as a null object is seen, otherwise returns false
    for (Object object : array) {
      if (object == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks HashMaps for null values.
   *
   * @param hashMap The HashMap to check
   * @return If the HashMap has any null elements
   */
  static <K, V> boolean containsNull(HashMap<K, V> hashMap) {
    // Will return true as soon as a null object is seen, otherwise returns false
    for (V value : hashMap.values()) {
      if (value == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check a single object to see if it is null.
   *
   * @param object The object to check
   * @return If the object is null
   */
  static boolean isNull(Object object) {
    return object == null;
  }

  /**
   * Method to determine if some integer is between two numbers, inclusive.
   *
   * @param number The number to test
   * @param max The highest possible value that number can be
   * @param min The lowest possible value that number can be
   * @return True if number is between max and min, false if not
   */
  static boolean isBetween(double number, double max, double min) {
    return number <= max && number >= min;
  }

  static boolean isPositive(double number) {
    return number >= 0;
  }
}
