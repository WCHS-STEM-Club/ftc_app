package org.firstinspires.ftc.teamcode.commands;

/**
 * Class that is extended to perform simple tasks using Sensor, MotorGroup, and ServoGroup. The
 * basic tasks are used by OpModes to perform more complex tasks.
 */
public abstract class Command {

  /**
   * Start command. This method runs while the robot is running the command.
   *
   * @return True if start was a success, false if start failed
   */
  public boolean start() {
    return execute();
  }

  /**
   * Executes command. Should only be called by the start method.
   */
  abstract boolean execute();
}
