package org.firstinspires.ftc.teamcode.sensors;

import com.nathanvarner.units.Unit;
import com.nathanvarner.units.Units;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import org.firstinspires.ftc.robotcore.external.navigation.*;

/**
 * Sensor that detects orientation and angular velocity.
 */
public abstract class Gyro implements Sensor {

  IntegratingGyroscope m_gyro;

  /**
   * Get the orientation of the gyroscope.
   * @param angleUnit The unit to return the angle in. The base unit should be {@link Units#rotation}.
   * @return {@link Orientation}, {@link Orientation#firstAngle} is X, {@link
   * Orientation#secondAngle} is Y, {@link Orientation#thirdAngle} is Z.
   */
  public Orientation getOrientation(Unit angleUnit) {
    Orientation orientation = m_gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYX, AngleUnit.DEGREES);
    orientation.firstAngle = (float)Units.degree.to(orientation.firstAngle, angleUnit);
    orientation.secondAngle = (float)Units.degree.to(orientation.secondAngle, angleUnit);
    orientation.thirdAngle = (float)Units.degree.to(orientation.thirdAngle, angleUnit);
    return orientation;
  }

  /**
   * Get the angular velocity of the gyroscope.
   * @param angleUnit The unit for the angle to be in. Note that the time unit is always inverse seconds. The base unit
   *                  should be {@link Units#rotation}.
   * @return The angular velocity as an {@link AngularVelocity} object. Access components with
   * {@link AngularVelocity#xRotationRate}, {@link AngularVelocity#yRotationRate}, and
   * {@link AngularVelocity#zRotationRate}. The angle unit is (angleUnit)/s.
   */
  public AngularVelocity getAngularVelocity(Unit angleUnit) {
    AngularVelocity angularVelocity = m_gyro.getAngularVelocity(AngleUnit.DEGREES);
    angularVelocity.xRotationRate = (float)Units.degree.to(angularVelocity.xRotationRate, angleUnit);
    angularVelocity.yRotationRate = (float)Units.degree.to(angularVelocity.yRotationRate, angleUnit);
    angularVelocity.zRotationRate = (float)Units.degree.to(angularVelocity.zRotationRate, angleUnit);
    return angularVelocity;
  }

  /**
   * Convenience method to get the heading in degrees.
   * @return The gyro's heading in degrees.
   */
  public float getHeading() {
    return getOrientation(Units.degree).thirdAngle;
  }
}
