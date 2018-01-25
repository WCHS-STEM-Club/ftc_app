package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.util.Range;

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
    if (xClicks == 0 || mmPerXClicks == 0) { // Prevent divide by zero error
      throw new IllegalArgumentException("xClicks and mmPerXClicks cannot be zero.");
    }
    // TODO: Make the below if statements throw errors, or invert the value?
    if (!ParamCheck.isPositive(xClicks)) {
      xClicks *= -1; // Or, could throw an error. Probably better to just invert it.
    }
    if (!ParamCheck.isPositive(mmPerXClicks)) {
      mmPerXClicks *= -1; // Or, could throw an error. Probably better to just invert it.
    }

    if (ParamCheck.isNull(motors)) {
      throw new IllegalArgumentException("motors cannot be null.");
    }

    if (ParamCheck.containsNull(motors)) {
      throw new IllegalArgumentException("There may be no null motors in a MotorGroup.");
    }

    this.X_CLICKS = xClicks;
    this.MM_PER_X_CLICKS = mmPerXClicks;
    this.motors = motors;

    disableEncoders(); // Solve a bug that was being caused by having no RunMode
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
    power = Range.clip(power, -1, 1); // Power should be in this range

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
   * Disable encoders mode.
   */
  public void disableEncoders() {
    for (DcMotor motor : motors) {
      motor.setMode(RunMode.RUN_WITHOUT_ENCODER);
    }
  }

  /**
   * Move the motors for some distance
   *
   * @param distance Distance in decimeters
   */
  public void goForDistance(float distance, float power) {
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
   * Set the motors to some distance
   *
   * @param distance Distance in decimeters
   * @param power Power from 1 to 0
   */
  public void setDistance(float distance, float power) {
    float mmDist = distance * 100; // Decimeters to millimeters
    int clicks = (int) (mmDist * (float) (X_CLICKS / MM_PER_X_CLICKS)); // Distance -> clicks

    for (DcMotor motor : this.motors) {
      motor.setTargetPosition(clicks);

      motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    setPower(power);
  }

  public void setClicks(int clicks, float power) {
    for (DcMotor motor : this.motors) {
      motor.setTargetPosition(clicks);
      motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    setPower(power);
  }

  public int getAverageClicks() {
    float total = 0;

    for (DcMotor motor : motors) {
      total += motor.getCurrentPosition();
    }

    return ((int) total / motors.length); // Average
  }

  public float getAverageDistance() {
    int clicks = getAverageClicks();
    float mm = ((float) MM_PER_X_CLICKS / (float) X_CLICKS) * (float) clicks; // Clicks -> mm
    return (1.0f / 100.0f) * mm; // mm -> dm
  }

  /**
   * Reset the encoders to 0 clicks.
   */
  public void resetEncoders() {
    for (DcMotor motor : this.motors) {
      while (motor.getCurrentPosition() != 0) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      }
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
