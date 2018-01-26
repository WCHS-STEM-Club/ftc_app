package org.firstinspires.ftc.teamcode;

public class RobotGetter {

  private static Robot robot;

  public static void setRobot(Robot newRobot) {
    robot = newRobot;
  }

  public static Robot getRobot() {
    return robot;
  }
}