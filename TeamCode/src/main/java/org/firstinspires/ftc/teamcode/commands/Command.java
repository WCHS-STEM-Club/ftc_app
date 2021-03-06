package org.firstinspires.ftc.teamcode.commands;

import android.os.AsyncTask;
import opmodes.LinearOpMode;

/**
 * Class that is extended to perform simple tasks using Sensor, MotorGroup, and ServoGroup. The
 * basic tasks are used by OpModes to perform more complex tasks.
 */
public abstract class Command {

  /**
   * Used to determine if the OpMode is still running, and, if so, to make it wait.
   */
  private LinearOpMode opMode;
  /**
   * Used to be able to cancel the thread if the OpMode is stopped.
   */
  private CommandThread commandThread;
  /**
   * Read to determine if the execution was a success.
   */
  public boolean success = false;
  /**
   * Read to determine when the command is finished.
   */
  public volatile boolean isFinished = false;

  /**
   * Constructor
   *
   * @param opMode Provides access to idle, aka Thread.yield for the OpMode thread
   */
  public Command(LinearOpMode opMode) {
    this.opMode = opMode;
  }

  /**
   * Start command. This method runs while the robot is running the command.
   *
   * @return True if start was a success, false if start failed
   */
  public boolean start() {
    if (runThread(this)) {
      while (!isFinished && opMode.opModeIsActive()) {
        opMode.idle();
      }
      commandThread.cancel(true);
    }
    return success;
  }

  /**
   * Called by start so that there is no need to create a thread every time a new Command is
   * created. Instead, a thread is created automatically, and this method's job is to do just
   * that- automatically create a thread.
   *
   * @param command For access to the command
   * @return The success of creating the thread
   */
  private boolean runThread(Command command) {
//    try {
    // This execute is not the same execute that is defined in this class!
    commandThread = new CommandThread();
    commandThread.execute(this);
    return true;
//    } catch (Exception e) { // Return false for any errors
//      return false;
//    }
  }

  /**
   * Executes command. Should only be called by the start method. Creates a new thread and
   * does not run for the duration of the command.
   *
   * @param commandThread To give the command access to isCancelled
   */
  public abstract boolean execute(AsyncTask commandThread);

  /**
   * Thread to execute command. Requires commands because it needs to be able to set success
   */
  protected static class CommandThread extends AsyncTask<Command, Integer, Boolean> {

    /**
     * Used to execute the command and update when the command is finished and the command's
     * success.
     */
    private Command command;

    /**
     * The actual job that the thread performs. Just executes the command.
     *
     * @param commands So that the command has access to itself and so it can set success
     * @return The success of the command's execution
     */
    @Override
    protected Boolean doInBackground(Command[] commands) {
      this.command = commands[0];
      return this.command.execute(this);
    }

    /**
     * Sets the success of the command's execution. doInBackground's return result is the
     * parameter.
     *
     * @param result The success of the command's execution
     */
    @Override
    protected void onPostExecute(Boolean result) {
      this.command.success = result;
      this.command.isFinished = true;
    }
  }
}
