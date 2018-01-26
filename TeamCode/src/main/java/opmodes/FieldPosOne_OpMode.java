package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.commands.DriveForDistEst;
import org.firstinspires.ftc.teamcode.commands.Grab;
import org.firstinspires.ftc.teamcode.commands.GyroTurn;
import org.firstinspires.ftc.teamcode.commands.MoveKnocker;
import org.firstinspires.ftc.teamcode.commands.Release;

/**
 * Created by rachel on 1/20/2018.
 */

@Autonomous(name = "FieldPosOne", group = "Linear Opmode")
public class FieldPosOne_OpMode extends LinearOpMode {
  @Override
  public void runOpMode() throws InterruptedException {
    Robot robot = new Robot2017(hardwareMap);
    ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

    waitForStart();

    if(opModeIsActive()) {

      sleep(100);
      telemetry.addData("currently: ", "slept");
      telemetry.update();
      //initial glyph holding

      robot.getServo("claw").converge();
      new Grab(robot);
      telemetry.addData("currently: ", "grabbed glyph");
      telemetry.update();
      sleep(1000);

      //jewel knock mockup

      new MoveKnocker(robot, 120);
      telemetry.addData("currently: ", "knocker down");
      telemetry.update();
      sleep(1000);

      if (colorSensor.red() > colorSensor.blue()) {
        //
        telemetry.addData("color: ", colorSensor.blue());
        telemetry.addData("color: ", colorSensor.red());
        telemetry.update();
      } else {

        telemetry.addData("color: ", colorSensor.blue());
        telemetry.addData("color: ", colorSensor.red());
        telemetry.update();
      }

      sleep(1000);
      //knocker up

      new MoveKnocker(robot, 0);
      telemetry.addData("currently: ", "knocker up");
      telemetry.update();
      sleep(1000);
      //vu

      telemetry.addData("mark: ", robot.getSensor("vuMarkPictograph").getSensorValue());
      telemetry.update();
      sleep(1000);

      new DriveForDistEst(10, 6.223f, 1, robot);
      telemetry.addData("currently: ", "moved towards cryptobox");
      telemetry.update();
      sleep(1000);

      //turn
      new GyroTurn(-90, robot, telemetry);
      telemetry.addData("currently: ", "should have turned left 90");
      telemetry.update();
      sleep(1000);

      new DriveForDistEst(10, 6.223f, 1, robot);
      telemetry.addData("currently: ", "moved towards cryptobox");
      telemetry.update();
      sleep(1000);
      //drive

      new Release(robot);
      sleep(1000);
      //glyph
    }
    //drive straight

  }
}
