package org.firstinspires.ftc.teamcode;

import java.util.HashMap;
import org.firstinspires.ftc.teamcode.sensors.Sensor;

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
   * All motors not related to driving the robot. Don't access this directly; use {@link
   * Robot#addOtherMotor(String, MotorGroup)}, {@link Robot#getOtherMotor(String)}, and {@link
   * Robot#otherMotorExists(String)}.
   *
   * <p>Recommended keys for motors:</p> <table> <thead> <tr> <td>Motor's job</td> <td>Motor
   * key</td> </tr> </thead> <tbody> <tr> <td>Main lift motor</td> <td>lift</td> </tr> </tbody>
   * </table>
   */
  private HashMap<String, MotorGroup> otherMotors = new HashMap<>();

  /**
   * All sensors on board the robot should be added here.
   *
   * <p>Recommended keys for sensors:</p>
   * <table>
   * <thead>
   * <tr>
   * <td>Sensor's type</td>
   * <td>Sensor key</td>
   * </tr>
   * <tr>
   * <td>Note: how to use this table to get a key</td>
   * <td>Add &lt;job&gt; after the recommended name. Ex: Jewel color sensor becomes colorJewel</td>
   * </tr>
   * </thead>
   * <tbody>
   * <tr>
   * <td>Gyro</td>
   * <td>gyro</td>
   * </tr>
   * <tr>
   * <td>VuMark Identifier</td>
   * <td>vuMark</td>
   * </tr>
   * <tr>
   * <td>Color</td>
   * <td>color</td>
   * </tr>
   * </tbody>
   * </table>
   */
  private HashMap<String, Sensor> sensors = new HashMap<>();

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

  public Robot(MotorGroup forwardMotors, MotorGroup[] turnMotors, MotorGroup[] strafeMotors,
      HashMap<String, MotorGroup> otherMotors) {
    setForwardMotors(forwardMotors);
    setTurnMotors(turnMotors);
    setStrafeMotors(strafeMotors);
    this.otherMotors = otherMotors;
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

  /**
   * Adds an other motor to the robot.
   *
   * @param key The key to use for the motor. Check the Javadoc for {@link Robot#otherMotors} for
   * recommended names.
   * @param value The MotorGroup to assign to the key.
   */
  public void addOtherMotor(String key, MotorGroup value) {
    if (ParamCheck.isNull(value)) {
      throw new IllegalArgumentException("value is null, cannot be null.");
    }

    if (otherMotorExists(key)) {
      throw new IllegalArgumentException(
          "key " + key + "already exists!");
    }

    otherMotors.put(key, value);
  }

  /**
   * Get the MotorGroup that corresponds to the key.
   *
   * @param key The key that corresponds to the MotorGroup
   * @return The MotorGroup that corresponds to the key
   */
  public MotorGroup getOtherMotor(String key) {
    if (!otherMotorExists(key)) {
      throw new IllegalArgumentException("No such key as " + key);
    }

    return otherMotors.get(key);
  }

  /**
   * Check if some other motor exists by checking if the key already exists.
   *
   * @param key The key to check
   * @return True if the key exists, false if not
   */
  public boolean otherMotorExists(String key) {
    return otherMotors.containsKey(key);
  }

  /**
   * Adds a sensor to the robot.
   *
   * @param key The key to use for the sensor. Check the Javadoc for {@link Robot#sensors} for
   * recommended names.
   * @param value The sensor to assign to the key.
   */
  public void addSensor(String key, Sensor value) {
    if (ParamCheck.isNull(value)) {
      throw new IllegalArgumentException("value is null, cannot be null.");
    }

    if (sensorExists(key)) {
      throw new IllegalArgumentException(
          "key " + key + "already exists!");
    }

    sensors.put(key, value);
  }

  /**
   * Get the sensor that corresponds to the key.
   *
   * @param key The key that corresponds to the sensor
   * @return The sensor that corresponds to the key
   */
  public Sensor getSensor(String key) {
    if (!sensorExists(key)) {
      throw new IllegalArgumentException("No such key as " + key);
    }

    return sensors.get(key);
  }

  /**
   * Check if some sensor exists by checking if the key already exists.
   *
   * @param key The key to check
   * @return True if the key exists, false if not
   */
  public boolean sensorExists(String key) {
    return sensors.containsKey(key);
  }
}
