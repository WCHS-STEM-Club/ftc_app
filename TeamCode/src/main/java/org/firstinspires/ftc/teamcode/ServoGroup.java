package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo.Direction;

public class ServoGroup {

  private double midwayPoint;
  private Servo[] servos;

  public ServoGroup(Servo... servos) {
    if (ParamCheck.isNull(servos)) {
      throw new IllegalArgumentException("Cannot have null servos");
    }

    if (ParamCheck.containsNull(servos)) {
      throw new IllegalArgumentException("There can be no null objects in servos");
    }

    this.midwayPoint = Servo.MAX_POSITION + Servo.MIN_POSITION / 2;
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

  public void setAngle(double degrees) {
    // Force degrees between 0 and 360
    while (degrees >= 360) {
      degrees -= 360;
    }
    while (degrees < 0) {
      degrees += 360;
    }

    for (Servo servo : this.servos) {
      servo.setPosition(toServoUnits(degrees));
    }
  }

  public void setDirection(Direction newDirection) {
    if (ParamCheck.isNull(newDirection)) {
      throw new IllegalArgumentException("Cannot have null newDirection");
    }

    for (Servo servo : this.servos) {
      servo.setDirection(newDirection);
    }
  }


  public String[] getDirection() {
    String[] directionList = new String[servos.length];
    for (int x = 0; x < servos.length; x++) {
      directionList[x] = servos[x].getDirection().name();
    }
    return directionList;
  }

  public void setDefaultPos() {
    for (Servo servo : this.servos) {
      servo.setPosition(midwayPoint); // midway point being default position
    }
  }

  /**
   * Changes the valid ranges for servos.
   *
   * @param newMin Minimum value in degrees
   * @param newMax Maximum value in degrees
   */
  public void scaleServos(double newMin, double newMax) {
    for (Servo servo : this.servos) {
      servo.scaleRange(newMin, newMax);
    }
  }

  // since this really only matters in the case of servos oriented in a specific way, we're just
  // going to do a lot of assumption here
  public void converge() {
    servos[0].setDirection(Servo.Direction.FORWARD);
    servos[1].setDirection(Servo.Direction.REVERSE);
  }

  public Servo getServo(int servoIndex) {
    if (servoIndex >= servos.length) {
      throw new IllegalArgumentException("Cannot have out of bounds servoIndex");
    }

    return servos[servoIndex];
  }


    //degrees should only be between 0 and 180 because like,,, no continous rotation as a motor has
    private double toServoUnits(double degrees) {
      return (degrees / 180);
    }
}
