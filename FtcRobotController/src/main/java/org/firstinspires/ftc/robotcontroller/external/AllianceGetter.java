package org.firstinspires.ftc.robotcontroller.external;

public class AllianceGetter {

  public enum Alliance {
    RED,
    BLUE,
    UNKNOWN
  }

  private static Alliance alliance=Alliance.UNKNOWN;

  public static void setAlliance(Alliance newAlliance) {
    alliance = newAlliance;
  }

  public static Alliance getAlliance() {
    return alliance;
  }
}