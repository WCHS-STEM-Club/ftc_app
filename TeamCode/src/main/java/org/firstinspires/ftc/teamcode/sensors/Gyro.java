package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Sensor that detects orientation.
 */
public abstract class Gyro implements Sensor {

  protected IntegratingGyroscope gyro;

  /**
   * Get the orientation of the gyroscope.
   *
   * @return {@link Orientation}, {@link Orientation#firstAngle} is X, {@link
   * Orientation#secondAngle} is Y, {@link Orientation#thirdAngle} is Z. In degrees.
   */
  @Override
  public Orientation getSensorValue() {
    return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYX, AngleUnit.DEGREES);
  }
}
