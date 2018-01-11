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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import org.junit.Test;

public class ParamCheckTest {

  /**
   * Ensure that {@link ParamCheck#arrayOfLength(Object[], int)} returns true when the array is of
   * the correct length.
   */
  @Test
  public void arrayOfLength_true() {
    Boolean[] array;

    for (int i = 1; i <= 256; i++) {
      array = new Boolean[i];
      assertTrue("Failed with array of size " + i, ParamCheck.arrayOfLength(array, i));
    }
  }

  /**
   * Ensure that {@link ParamCheck#arrayOfLength(Object[], int)} returns false  when the array is
   * not of the correct length.
   */
  @Test
  public void arrayOfLength_false() {
    Boolean[] array;

    for (int i = 1; i <= 256; i++) {
      array = new Boolean[i];
      assertFalse("Failed with an array of size " + i + " and checked size of " + (i + 1),
          ParamCheck.arrayOfLength(array, i + 1));
    }
  }

  /**
   * Ensure that {@link ParamCheck#containsNull(Object[]...)} returns true when the array contains
   * a null.
   */
  @Test
  public void containsNull_true() {
    Object[] array0 = {
        "Test",
        12345,
        12.345,
        12.345f,
        null,
        null
    };
    Object[] array1 = {
        null,
        "Test",
        12345,
        12.345,
        12.345f
    };
    Object[] array2 = {
        "Test",
        null,
        12345
    };
    Object[] array3 = {
        null
    };
    Object[] array4 = {
        null,
        null,
        null,
        null
    };

    Object[][] allArrays = {
        array0,
        array1,
        array2,
        array3,
        array4
    };

    for (int i = 0; i < allArrays.length; i++) {
      assertTrue("Failed with array #" + i, ParamCheck.containsNull(allArrays[i]));
    }

    assertTrue("Failed with many arrays", ParamCheck.containsNull(
        array0,
        array1,
        array2,
        array3,
        array4
    ));
  }

  /**
   * Ensure that {@link ParamCheck#containsNull(Object[]...)} returns false when the array contains
   * no null.
   */
  @Test
  public void containsNull_false() {
    Object[] array0 = {
        "Test",
        12345,
        12.345,
        12.345f
    };
    Object[] array1 = {
        "null",
        0,
        0.0d,
        0f,
        0L,
        false
    };
    Object[] array2 = {};

    Object[][] allArrays = {
        array0,
        array1,
        array2
    };

    for (int i = 0; i < allArrays.length; i++) {
      assertFalse("Failed with array #" + i, ParamCheck.containsNull(allArrays[i]));
    }

    assertFalse("Failed with many arrays", ParamCheck.containsNull(
        array0,
        array1,
        array2
    ));
  }

  /**
   * Ensure that {@link ParamCheck#containsNull(HashMap)} returns true when a value is null, false
   * when a false is not.
   */
  @Test
  public void containsNull_hashMap() {
    Object[][][][] testCases = {
        // {{key, value}...}, result
        {
            {
                {"test", "null"},
                {"test", ""},
                {"test2", 0},
                {"test3", false}
            },
            {{false}}
        },
        {
            {
                {"null", "valid"},
                {"", "valid"},
                {0, "valid"},
                {false, "valid"},
                {null, "valid"}
            },
            {{false}} // Detects in value, not key
        },
        {
            {
                {"normal", 0},
                {true, "normal"},
                {7.6f, true},
                {0, 7.6f}
            },
            {{false}}
        },
        {
            {
                {0, 78.0f},
                {null, null},
                {"perfectly", "valid"}
            },
            {{true}}
        },
        {
            {
                {"valid", null}
            },
            {{true}}
        }
    };

    for (int i = 0; i < testCases.length; i++) {
      Object[][][] testCase = testCases[i];
      boolean expectedResult = (boolean) testCase[1][0][0];
      HashMap<Object, Object> hashMap = new HashMap<>();

      for (Object[] inOut : testCase[0]) {
        hashMap.put(inOut[0], inOut[1]);
      }

      assertEquals("Failed due to incorrectly recognising null, test # " + i, expectedResult,
          ParamCheck.containsNull(hashMap));

    }
  }

  /**
   * Ensure that {@link ParamCheck#isNull(Object)} returns true when the object is null.
   */
  @Test
  public void isNull_true() {
    assertTrue("Failed with null", ParamCheck.isNull(null));
  }

  /**
   * Ensure that {@link ParamCheck#isNull(Object)} returns false when the object is not null.
   */
  @Test
  public void isNull_false() {
    Object object0 = "null";
    Object object1 = "";
    Object object2 = 0;

    Object[] allObjects = {
        object0,
        object1,
        object2
    };

    for (int i = 0; i < allObjects.length; i++) {
      assertFalse("Failed with object #" + i, ParamCheck.isNull(allObjects[i]));
    }
  }

  /**
   * Ensure that {@link ParamCheck#isBetween(double, double, double)} returns true when the number
   * is between the other two.
   */
  @Test
  public void isBetween_true() {
    double[][] tests = {
        // num, min, max
        {0, -1, 1},
        {1.01f, 1, 1.02f},
        {1.01, 0, 2},
        {-1, -1.000001f, -0.9999999},
        {1, 0, 2},
        {0, 0, 0},
        {0, 0, 1},
        {0, -1, 0}
    };

    for (int i = 0; i < tests.length; i++) {
      double[] test = tests[i];
      double num = test[0];
      double min = test[1];
      double max = test[2];
      assertTrue("Failed on test #" + i, ParamCheck.isBetween(num, max, min));
    }
  }

  /**
   * Ensure that {@link ParamCheck#isBetween(double, double, double)} returns false when the number
   * is not between the other two.
   */
  @Test
  public void isBetween_false() {
    double[][] tests = {
        // num, min, max
        {-1, 0, 1},
        {-1.0, 0, 1},
        {1, 0, 0},
        {1, -1, 0},
        {0, 1, -1}
    };

    for (int i = 0; i < tests.length; i++) {
      double[] test = tests[i];
      double num = test[0];
      double min = test[1];
      double max = test[2];
      assertFalse("Failed on test #" + i, ParamCheck.isBetween(num, max, min));
    }
  }

  /**
   * Ensure that {@link ParamCheck#isPositive(double)} returns true when the number is positive.
   */
  @Test
  public void isPositive_true() {
    double[] tests = {
        1,
        0,
        5042876,
        12.9867
    };

    for (int i = 0; i < tests.length; i++) {
      assertTrue("Failed on test #" + i, ParamCheck.isPositive(tests[i]));
    }
  }

  /**
   * Ensure that {@link ParamCheck#isPositive(double)} returns false when the number is negative.
   */
  @Test
  public void isPositive_false() {
    double[] tests = {
        -0.000001,
        -5,
        -538,
        -42709.9876
    };

    for (int i = 0; i < tests.length; i++) {
      assertFalse("Failed on test #" + i, ParamCheck.isPositive(tests[i]));
    }
  }
}