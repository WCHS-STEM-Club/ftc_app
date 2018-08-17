package org.firstinspires.ftc.teamcode;

import java.util.Map;

class MockRobot extends Robot {
  MockRobot() {
    super();
  }

  MockRobot(MotorGroup forwardMotors, MotorGroup[] turnMotors, MotorGroup[] strafeMotors) {
    super(forwardMotors, turnMotors, strafeMotors);
  }

  MockRobot (MotorGroup forwardMotors, MotorGroup[] turnMotors, MotorGroup[] strafeMotors,
             Map<String, MotorGroup> otherMotors) {
    super(forwardMotors, turnMotors, strafeMotors, otherMotors);
  }
}
