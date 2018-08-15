/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package opmodes;

import com.nathanvarner.units.Units;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.MotorGroup;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.ServoGroup;
import org.firstinspires.ftc.teamcode.sensors.MrGyro;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode". An OpMode is a 'program'
 * that runs in either the autonomous or the teleop period of an FTC match. The names of OpModes
 * appear on the menu of the FTC Driver Station. When an selection is made from the menu, the
 * corresponding OpMode class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot It
 * includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new
 * name. Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode
 * list
 */

@TeleOp(name = "Competition TeleOp", group = "TeleOp")
public class Competition_TeleOp extends OpMode {

  // The robot is set up such that there are two drive motors in the back
  private ElapsedTime runtime = new ElapsedTime();
  private Robot robot;
  private PidController pid;

  private MotorGroup lift;
  private ServoGroup claw;
  private ServoGroup knock;
  private MrGyro gyro;

  private double clawPosition = 0;
  private double knockPosition = 32;

  private final double kp = 0.001;
  private final double ki = 0;
  private final double kd = 0.3;

  /*
   * Code to run ONCE when the driver hits INIT
   */
  @Override
  public void init() {
    telemetry.addData("Status", "Initializing... this is the new one right?");

    robot = new Robot2017(hardwareMap);

    lift = robot.getOtherMotor("lift");
    claw = robot.getServo("claw");
    knock = robot.getServo("knock");
    gyro = (MrGyro) robot.getSensor("gyroAngular");

    lift.useEncoders();
    claw.converge();

    // Tell the driver that initialization is complete.
    telemetry.addData("Status", "Ready");
  }

  /*
   * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
   */
  @Override
  public void init_loop() {
  }

  /*
   * Code to run ONCE when the driver hits PLAY
   */
  @Override
  public void start() {
    runtime.reset();
    lift.resetEncoders();
    pid = new PidController();
  }

  /*
   * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
   */
  @Override
  public void loop() {
    driveLoop();

    double liftPower = gamepad2.left_stick_y;
    double liftDist = lift.getAverageDistance(Units.decimeter);

    // 3dm max, 0dm min
    if (liftDist >= 3 && liftPower > 0) {
      liftPower = 0;
    } else if (liftDist <= 0 && liftPower < 0) {
      liftPower = 0;
    }
    lift.setPower(liftPower);

    telemetry.addData("Actual power", liftPower);

    if (gamepad2.a) {
      clawPosition = 100;
    } else if (gamepad2.b) {
      clawPosition = 0;
    }

    claw.setAngle(clawPosition);

    if (gamepad2.x) {
      knockPosition = 120;
    } else if (gamepad2.y) {
      knockPosition = 32;
    }

    knock.setAngle(knockPosition);

    // Show the elapsed game time and wheel power.
    telemetry.addData("Status", "Running, for time: " + runtime.toString());
    telemetry.update();
  }

  /*
   * Code to run ONCE after the driver hits STOP
   */
  @Override
  public void stop() {
    robot.forwardMotors.setPower(0);
    lift.setPower(0);
  }

  private void driveLoop() {
    double gyroResult = (double) gyro.read();

    float drive = gamepad1.left_stick_y;
    float turn = gamepad1.right_stick_x;

    telemetry.addData("Drive", drive);
    telemetry.addData("Turn", turn);

    float targetAngularVelocity = turn * 20;

    double correction = pid.calcPid(gyroResult, targetAngularVelocity, kp, ki, kd);

    float leftPower = (float) (drive + correction);
    float rightPower = (float) (drive - correction);

    float max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
    if (max > 1) {
      leftPower /= max;
      rightPower /= max;
    }

    robot.getTurnMotor(0).setPower(leftPower);
    robot.getTurnMotor(1).setPower(rightPower);

    telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
  }
}
