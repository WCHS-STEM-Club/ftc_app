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
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.nathanvarner.units.Units;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class MotorGroupTest {

  /**
   * Ensure that the constructor doesn't break with bad arguments that should throw an error.
   */
  @Test
  public void MotorGroup_error() {
    try {
      new MotorGroup((DcMotor[]) null);
      fail("Did not throw IllegalArgumentException for null motor.");
    } catch (IllegalArgumentException e) {
      // Expecting exception, pass
    }

    try {
      new MotorGroup(mock(DcMotor.class), null);
      fail("Did not throw IllegalArgumentException for null motor.");
    } catch (IllegalArgumentException e) {
      // Expecting exception, pass
    }
  }

  /**
   * Ensure that the constructor constructs the right way.
   */
  @Test
  public void MotorGroup_construction() {
    DcMotor motor = mock(DcMotor.class);

    MotorGroup motorGroup = new MotorGroup(Units.decimeter, motor);
    if (!(motorGroup.encoderUnit == Units.decimeter)) {
      fail("MotorGroup didn't save encoder units");
    }
  }

  /**
   * Ensure that setPower actually sets the power.
   */
  @Test
  public void setPower_success() {
    DcMotor motor = mock(DcMotor.class);
    ArgumentCaptor<Double> motorPowerArg = ArgumentCaptor.forClass(Double.class);

    MotorGroup motorGroup = new MotorGroup(motor);

    motor = mock(DcMotor.class);
    motorGroup = new MotorGroup(motor);
    // Full force
    motorGroup.setPower(1.0f);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't set motor power to 1.0", 1.0, motorPowerArg.getValue());

    motor = mock(DcMotor.class);
    motorGroup = new MotorGroup(motor);
    // Fraction
    motorGroup.setPower(0.5f);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't set motor power to 0.5", 0.5, motorPowerArg.getValue());

    motor = mock(DcMotor.class);
    motorGroup = new MotorGroup(motor);
    // Zero
    motorGroup.setPower(0.0f);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't set motor power to 0.0", 0.0, motorPowerArg.getValue());

    motor = mock(DcMotor.class);
    motorGroup = new MotorGroup(motor);
    // Negative
    motorGroup.setPower(-0.5f);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't set motor power to -0.5", -0.5, motorPowerArg.getValue());
  }

  /**
   * Ensure that setPower actually trims the ranges
   */
  @Test
  public void setPower_clip() {
    DcMotor motor = mock(DcMotor.class);
    ArgumentCaptor<Double> motorPowerArg = ArgumentCaptor.forClass(Double.class);

    MotorGroup motorGroup = new MotorGroup(motor);

    // Trim down
    motorGroup.setPower(100);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't trim motor power down", 1.0, motorPowerArg.getValue());

    motor = mock(DcMotor.class);
    motorGroup = new MotorGroup(motor);
    // Trim down a float
    motorGroup.setPower(57.3f);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't trim motor power float down", 1.0, motorPowerArg.getValue());

    motor = mock(DcMotor.class);
    motorGroup = new MotorGroup(motor);
    // Trim up
    motorGroup.setPower(-100);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't trim motor power up", -1.0, motorPowerArg.getValue());

    motor = mock(DcMotor.class);
    motorGroup = new MotorGroup(motor);
    // Trim up float
    motorGroup.setPower(-57.3f);
    verify(motor).setPower(motorPowerArg.capture());
    assertEquals("Didn't trim motor power float up", -1.0, motorPowerArg.getValue());
  }

  /**
   * Ensure that the robot actually stops with brake
   */
  @Test
  public void brake() {
    DcMotor motor = mock(DcMotor.class);
    ArgumentCaptor<Double> motorPowArg = ArgumentCaptor.forClass(Double.class);
    ArgumentCaptor<ZeroPowerBehavior> zeroPowArg = ArgumentCaptor.forClass(ZeroPowerBehavior.class);

    MotorGroup motorGroup = new MotorGroup(motor);
    motorGroup.setPower(1); // Make sure that brake changes the power

    // Check if brake did anything
    motorGroup.brake();
    verify(motor, atLeastOnce()).setPower(motorPowArg.capture());
    verify(motor, atLeastOnce()).setZeroPowerBehavior(zeroPowArg.capture());

    // If the motor didn't actually brake
    if (motorPowArg.getValue() != 0 || zeroPowArg.getValue() != ZeroPowerBehavior.BRAKE) {
      fail("Did not brake");
    }
  }

  /**
   * Ensure that the motor coasts with coast
   */
  @Test
  public void coast() {
    DcMotor motor = mock(DcMotor.class);
    ArgumentCaptor<Double> motorPowArg = ArgumentCaptor.forClass(Double.class);
    ArgumentCaptor<ZeroPowerBehavior> zeroPowArg = ArgumentCaptor.forClass(ZeroPowerBehavior.class);

    MotorGroup motorGroup = new MotorGroup(motor);
    motorGroup.setPower(1); // Make sure that coast changes the power

    // Check if coast did anything
    motorGroup.coast();
    verify(motor, atLeastOnce()).setPower(motorPowArg.capture());
    verify(motor, atLeastOnce()).setZeroPowerBehavior(zeroPowArg.capture());

    // If the motor didn't actually coast
    if (motorPowArg.getValue() != 0 || zeroPowArg.getValue() != ZeroPowerBehavior.FLOAT) {
      fail("Did not coast");
    }
  }

  /**
   * Ensure that the motors switch to encoders mode
   */
  @Test
  public void useEncoders() {
    DcMotor motor = mock(DcMotor.class);
    ArgumentCaptor<RunMode> runModeArg = ArgumentCaptor.forClass(RunMode.class);

    MotorGroup motorGroup = new MotorGroup(motor);

    // Check if useEncoders did anything
    motorGroup.useEncoders();
    verify(motor, atLeastOnce()).setMode(runModeArg.capture());

    // If the motor didn't actually use encoders
    if (runModeArg.getValue() != RunMode.RUN_USING_ENCODER) {
      fail("Did not use encoders mode");
    }
  }
}
