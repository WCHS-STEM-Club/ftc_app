package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcontroller.external.AllianceGetter;
import org.firstinspires.ftc.robotcontroller.external.AllianceGetter.Alliance;
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

    Alliance alliance = AllianceGetter.getAlliance();
    Robot robot = new Robot2017(hardwareMap);
    ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

    waitForStart();

    if(opModeIsActive()) {
      //initial glyph holding
      robot.getServo("claw").converge();
      new Grab(robot).start();
      telemetry.addData("currently: ", "grabbed glyph");
      telemetry.update();

      //jewel knock mockup
      new MoveKnocker(robot, 150).start();
      telemetry.addData("currently: ", "knocker down");
      telemetry.update();

      if ( (colorSensor.red() > colorSensor.blue() && alliance.equals(Alliance.BLUE)) ||
              (colorSensor.red() < colorSensor.blue() && alliance.equals(Alliance.RED)) ) {
        //knock over sensed jewel
        telemetry.addData("blue: ", colorSensor.blue());
        telemetry.addData("red: ", colorSensor.red());
        telemetry.addLine("Going to knock this jewel");
        telemetry.update();
      } else if( (colorSensor.red() > colorSensor.blue() && alliance.equals(Alliance.RED)) ||
              (colorSensor.red() < colorSensor.blue() && alliance.equals(Alliance.BLUE)) ){
        //backward, knock over non sensed jewel
        telemetry.addData("blue: ", colorSensor.blue());
        telemetry.addData("red: ", colorSensor.red());
        telemetry.addLine("Going to knock the other jewel");
        telemetry.update();

      } else{
        telemetry.addData("guess ill", "die");
      }

      sleep(4000);
      //knocker up

      new MoveKnocker(robot, 0).start();
      telemetry.addData("currently: ", "knocker up");
      telemetry.update();



      //vu

      telemetry.addData("mark: ", robot.getSensor("vuMarkPictograph").getSensorValue());
      telemetry.update();
      sleep(4000);

      new DriveForDistEst(-10.0f, 6.223f, 1.0f, robot).start();
      telemetry.addData("currently: ", "moved towards cryptobox");
      telemetry.update();
      sleep(1000);

      //turn
      new GyroTurn(-90f, robot, telemetry).start();
      telemetry.addData("currently: ", "should have turned left 90");
      telemetry.update();
      sleep(1000);

      new DriveForDistEst((float) 10, 6.223f, (float) 1, robot).start();
      telemetry.addData("currently: ", "moved towards cryptobox");
      telemetry.update();
      sleep(1000);
      //drive

      new Release(robot).start();
      sleep(2000);
      //glyph
    }
    //drive straight

  }
}
