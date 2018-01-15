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

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

import org.firstinspires.ftc.teamcode.sensors.ColorSensor;
import org.firstinspires.ftc.teamcode.sensors.Sensor;
import org.junit.Before;
import org.junit.Test;

public class RobotTest {

  private Robot robot;

  /**
   * Run a setup before each test.
   */
  @Before
  public void beforeEach() {
    robot = new MockRobot();
  }

  /**
   * Ensure that setForwardMotors sets the forward motors.
   */
  @Test
  public void setForwardMotors() {
    MotorGroup forwardMotors = new MotorGroup(1, 1, new MockDcMotor());
    robot.setForwardMotors(forwardMotors);
    robot.ready = true; // Don't do this in Commands/OpModes!

    assertEquals("Did not set the forward motors", forwardMotors, robot.forwardMotors);
  }

  /**
   * Ensure that setTurnMotors sets the turn motors
   */
  @Test
  public void setTurnMotors() {
    MotorGroup[] turnMotors = {
        new MotorGroup(1, 1, new MockDcMotor()),
        new MotorGroup(1, 1, new MockDcMotor())
    };
    robot.setTurnMotors(turnMotors);
    robot.ready = true; // Don't do this in OpModes/Commands!

    assertEquals("Did not set the turn motors", turnMotors, robot.turnMotors);
  }

  /**
   * Ensure that setStrafeMotors sets the strafe motors
   */
  @Test
  public void setStrafeMotors() {
    MotorGroup[] strafeMotors = {
        new MotorGroup(1, 1, new MockDcMotor()),
        new MotorGroup(1, 1, new MockDcMotor())
    };
    robot.setStrafeMotors(strafeMotors);
    robot.ready = true; // Don't do this in OpModes/Commands!

    assertEquals("Did not set the turn motors", strafeMotors, robot.strafeMotors);
  }

  /**
   * Ensure that add/getOtherMotor and otherMotorExists work as expected
   */
  @Test
  public void otherMotor() {
    String key = "key";
    MotorGroup motor = new MotorGroup(1, 1, new MockDcMotor());

    robot.addOtherMotor(key, motor);

    if (!robot.otherMotorExists(key)) {
      fail("The other motor failed to add or otherMotorExists failed to properly check.");
    }

    assertEquals("Other motor failed to store properly or retrieve properly.", motor,
        robot.getOtherMotor(key));
  }

  /**
   * Ensure that add/getSensor and sensorExists work as expected
   */
  @Test
  public void sensor() {
    String key = "key";
    Sensor sensor = new ColorSensor(new MockColorSensor(), false);

    robot.addSensor(key, sensor);

    if (!robot.sensorExists(key)) {
      fail("The sensor failed to add or sensorExists failed to properly check.");
    }

    assertEquals("Sensor failed to store properly or retrieve properly.", sensor,
        robot.getSensor(key));
  }
}
