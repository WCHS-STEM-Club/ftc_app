package org.firstinspires.ftc.teamcode;

public abstract class Robot {

  /**
   * If the robot's variables have been initialised. Be sure to check this variable before trying to
   * access {@link Robot#forwardMotors}, {@link Robot#turnMotors}, or {@link Robot#strafeMotors}.
   */
  public boolean ready = false;

  /**
   * If the robot has strafing ability. Be sure to check this variable before trying to access
   * {@link Robot#strafeMotors}. Do not write to this variable! Instead, pass null to {@link
   * Robot#setStrafeMotors(MotorGroup[])} to disable strafe, or pass a valid MotorGroup[] to
   * enable.
   */
  public boolean canStrafe = false; // Robot cannot strafe by default.

  /**
   * The motors that move forward to make the robot move forward. Cannot be null. Be sure to check
   * {@link Robot#ready} before accessing. Do not write to this variable! Instead, use
   * {@link Robot#setForwardMotors(MotorGroup)}.
   */
  public MotorGroup forwardMotors;
  /**
   * The motors that are used to turn the robot. On a right turn, element 0 motors will move forward
   * while element 1 motors will move backwards, and vice versa for left turns. This must be not
   * null, 2 elements long, and contain no null elements. Be sure to check {@link Robot#ready}
   * before accessing. Do not write to this variable! Instead, use
   * {@link Robot#setTurnMotors(MotorGroup[])}.
   */
  public MotorGroup[] turnMotors;
  /**
   * The motors that are used to strafe the robot. On a right strafe, element 0 motors will move
   * forward while element 1 motors will move backwards, and vice versa for left strafes. This may
   * be null if the robot cannot strafe. If not null, it must be 2 elements long and contain no null
   * elements. Be sure to check {@link Robot#ready} and {@link Robot#canStrafe} before accessing. Do
   * not write to this variable! Instead, use {@link Robot#setStrafeMotors(MotorGroup[])}.
   */
  public MotorGroup[] strafeMotors;

  /**
   * Constructor
   *
   * @param forwardMotors A MotorGroup with motors that go forward when you want to go forward.
   * @param turnMotors A list of MotorGroups. On a right turn, element 0 goes forward and 1 goes
   * backward.
   * @param strafeMotors A list of MotorGroups. On a strafe right, element 0 goes forward and 1 goes
   * backward.
   */
  public Robot(MotorGroup forwardMotors, MotorGroup[] turnMotors, MotorGroup[] strafeMotors) {
    setForwardMotors(forwardMotors);
    setTurnMotors(turnMotors);
    setStrafeMotors(strafeMotors);
  }

  /**
   * Default constructor. If used, the robot will not be ready by default. You must add forward,
   * turn, and optionally strafe motors using {@link Robot#setForwardMotors(MotorGroup)},
   * {@link Robot#setTurnMotors(MotorGroup[])}, and {@link Robot#setStrafeMotors(MotorGroup[])},
   * respectively.
   */
  public Robot() {
    // Not ready, so motors must be added some other way
  }

  /**
   * Set the forward motors for the robot.
   *
   * @param forwardMotors A MotorGroup with motors that go forward when you want to go forward.
   */
  public void setForwardMotors(MotorGroup forwardMotors) {
    if (ParamCheck.isNull(forwardMotors)) {
      throw new IllegalArgumentException(
          "Null input(s) to Robot constructor. Only strafeMotors[] " +
              "may be null.");
    }

    this.forwardMotors = forwardMotors;

    if (!ParamCheck.isNull(this.turnMotors)) {
      ready = true;
    }
  }

  /**
   * Set the turning motors for the robot.
   *
   * @param turnMotors A list of MotorGroups. On a right turn, element 0 goes forward and 1 goes
   * backward.
   */
  public void setTurnMotors(MotorGroup[] turnMotors) {
    if (ParamCheck.isNull(turnMotors)) {
      throw new IllegalArgumentException("turnMotors is null, cannot be null.");
    }

    if (!ParamCheck.arrayOfLength(turnMotors, 2)) {
      throw new IllegalArgumentException(
          "turnMotors doesn't have a length of 2, must have a length of 2.");
    }

    if (ParamCheck.containsNull(turnMotors)) {
      throw new IllegalArgumentException("turnMotors has null elements, cannot be null.");
    }

    this.turnMotors = turnMotors;

    if (!ParamCheck.isNull(this.forwardMotors)) {
      ready = true;
    }
  }

  /**
   * Set the strafe motors for the robot. Passing null will disable strafe.
   *
   * @param strafeMotors A list of MotorGroups. On a strafe right, element 0 goes forward and 1 goes
   * backward.
   */
  public void setStrafeMotors(MotorGroup[] strafeMotors) {
    if (ParamCheck.isNull(strafeMotors)) {
      canStrafe = false;
      return;
    }

    if (!ParamCheck.arrayOfLength(strafeMotors, 2)) {
      throw new IllegalArgumentException(
          "strafeMotors doesn't have a length of 2, must have a length of 2.");
    }

    if (ParamCheck.containsNull(strafeMotors)) {
      throw new IllegalArgumentException("strafeMotors has null elements, cannot be null.");
    }

    this.strafeMotors = strafeMotors;

    canStrafe = true;
  }
}
