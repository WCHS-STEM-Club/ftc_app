package org.firstinspires.ftc.teamcode;

/**
 * Class with all static methods to check values, especially parameters.
 */
abstract class ParamCheck {

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
   * @param arrays The arrays to check
   * @return If the array has any null elements
   */
  static boolean containsNull(Object[]... arrays) {
    // Will return true as soon as a null object is seen, otherwise returns false
    for (Object[] array : arrays) {
      for (Object object : array) {
        if (object == null) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks a group of objects to see if any are null.
   *
   * @param objects The objects to check
   * @return If any of the objects are null
   */
  static boolean areNull(Object... objects) {
    return containsNull(objects);
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
}
