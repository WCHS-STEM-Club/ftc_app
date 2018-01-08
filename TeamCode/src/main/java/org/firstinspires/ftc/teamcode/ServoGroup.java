package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoGroup {

  private double midwayPoint;
  private Servo[] servos;

  public ServoGroup(double midwayPoint, Servo... servos) {
    this.midwayPoint = midwayPoint;
    this.servos = servos;
  }

  /**
   * Halt all servos in the group.
   */
  public void stop() {
    for (Servo servo : this.servos) {
      servo.setPosition(servo.getPosition()); // Set it to where it is so it has nowhere to go
    }
  }

  public void setAngle(float angle) {
    double position = angle + midwayPoint; // Add the midway point, ex:
    // midwayPoint: 90, angle: 0
    // Should set the position to
    position /= 360; // Convert 0 -> 0, 180 -> 0.5, 360 -> 1
    for (Servo servo : this.servos) {
      servo.setPosition(position);
    }
  }
}
