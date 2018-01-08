package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * A class to make controlling many motors at the same time easy. The class has a list of motors,
 * and all motors in that list perform the actions the group is told to do.
 */
public class MotorGroup {

  /**
   * The list of motors that make up the group.
   */
  private DcMotor[] motors;
  /**
   * Dirty flag to make sure the encoders are reset when they have to be.
   */
  private boolean encodersReset = false;

  /**
   * Some number of encoder clicks.
   */
  public final int X_CLICKS;
  /**
   * Some distance that the motors move every X_CLICKS encoder clicks.
   */
  public final double MM_PER_X_CLICKS;

  /**
   * Constructor
   *
   * @param xClicks Some number of encoder clicks
   * @param mmPerXClicks Some distance that the motors move every X_CLICKS encoder clicks
   * @param motors The list of motors that make up the group
   */
  public MotorGroup(int xClicks, double mmPerXClicks, DcMotor... motors) {
    this.X_CLICKS = xClicks;
    this.MM_PER_X_CLICKS = mmPerXClicks;
    this.motors = motors;

    resetEncoders();
  }

  /**
   * Set the power of all motors.
   *
   * @param power Some number between -1 and 1. When power is -1, the motors run backwards. When
   * power is 1, the motors run forwards. In between, the motors run with less power. At 0, the
   * motors will turn off. To turn the motors off, don't use this method, use either {@link
   * MotorGroup#brake()} or {@link MotorGroup#coast()}.
   */
  public void setPower(float power) {
    // TODO: Decide if instead of returning, this method should instead set the power to -1 or 1, whichever is closer to the original value of power.

    if (!ParamCheck.isBetween(power, -1, 1)) {
      return;
    } // Ensure that power is good

    for (DcMotor motor : this.motors) {
      motor.setPower(power);
    }

    encodersReset = false;
  }

  /**
   * Turn the motors off and stop as quickly as possible. The motors should be held in place.
   */
  public void brake() {
    for (DcMotor motor : this.motors) {
      motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    this.setPower(0);
  }

  /**
   * Turn the motors off, but don't try to stop them. Momentum will keep the motors going until
   * overcome by friction. This should use less power.
   */
  public void coast() {
    for (DcMotor motor : this.motors) {
      motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    this.setPower(0);
  }

  /**
   * Enable encoders mode.
   */
  public void useEncoders() {
    for (DcMotor motor : this.motors) {
      motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
  }

  /**
   * Move the motors for some distance
   *
   * @param distance Distance in decimeters
   */
  public void goForDistance(float distance, float power) {
    if (!encodersReset) {
      resetEncoders();
    }

    float mmDist = distance * 100; // Decimeters to millimeters
    int clicks = (int) (mmDist * (float) (X_CLICKS / MM_PER_X_CLICKS)); // Distance -> clicks

    for (DcMotor motor : this.motors) {
      int target = motor.getCurrentPosition() + clicks;
      motor.setTargetPosition(target);

      motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    setPower(power);
  }

  /**
   * Reset the encoders to 0 clicks.
   */
  public void resetEncoders() {
    for (DcMotor motor : this.motors) {
      motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    encodersReset = true;
  }

  /**
   * Return if the motors are running.
   *
   * @return Are the motors running
   */
  public boolean isBusy() {
    boolean busy = false;

    for (DcMotor motor : this.motors) {
      if (motor.isBusy()) {
        busy = true;
      }
    }

    return busy;
  }
}
