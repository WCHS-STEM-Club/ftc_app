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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.commands.DriveForDistEst;
import org.firstinspires.ftc.teamcode.commands.DriveForDistance;
import org.firstinspires.ftc.teamcode.commands.Grab;
import org.firstinspires.ftc.teamcode.commands.GyroTurn;
import org.firstinspires.ftc.teamcode.commands.Release;

@Autonomous(name = "Test OpMode", group = "Linear Opmode")
public class Test_OpMode extends LinearOpMode {

  private ElapsedTime runtime = new ElapsedTime();

  @Override
  public void runOpMode() throws InterruptedException {
    Robot robot = new Robot2017(hardwareMap);

    telemetry.addData("Status", "Initialized");
    telemetry.update();

  waitForStart();
    runtime.reset();

    if (opModeIsActive()) {
//      sleep(1000);
//      robot.getServo("claw").converge();
//      robot.getServo("claw").setDefaultPos();
//      telemetry.addData("status", "should set to default pos");
//      telemetry.update();
//      sleep(1000);
//
//
//      new Grab(robot).start();
//      telemetry.addData("status", "a grab should happen");
//      telemetry.update();
//      sleep(1000);
//
//
//      new Release(robot).start();
//      telemetry.addData("status", "a release should happene");
//      telemetry.update();
//      sleep(1000);
//
//
//      new GyroTurn(90, robot, telemetry).start();
//      telemetry.addData("status", "turning");
//      telemetry.update();
//      sleep(1000);

      new DriveForDistEst((float) 10, 6.223f, 1, robot).start();

      telemetry.addData("should have gone", "about a meter");
      telemetry.update();


    }
  }
}
