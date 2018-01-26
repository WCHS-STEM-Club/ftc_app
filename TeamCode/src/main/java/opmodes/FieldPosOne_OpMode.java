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

    sleep(100);
    telemetry.addData("currently: ", "slept");
    telemetry.update();
    //initial glyph holding

    robot.getServo("claw").converge();
    new Grab(robot);
    telemetry.addData("currently: ", "grabbed glyph");
    telemetry.update();

    //jewel knock mockup

    new MoveKnocker(robot, 120);
    telemetry.addData("currently: ", "knocker down");
    telemetry.update();

    if(colorSensor.red() > colorSensor.blue()){
      //
      telemetry.addData("color: ", colorSensor.blue());
      telemetry.addData("color: ", colorSensor.red());
      telemetry.update();
    }else{

      telemetry.addData("color: ", colorSensor.blue());
      telemetry.addData("color: ", colorSensor.red());
      telemetry.update();
    }

    //knocker up

    new MoveKnocker(robot, 0);
    telemetry.addData("currently: ", "knocker up");
    telemetry.update();

    //vu

    telemetry.addData("mark: ", robot.getSensor("vuMarkPictograph").getSensorValue());
    telemetry.update();

    new DriveForDistEst(10, 6.223f, 1, robot);
    telemetry.addData("currently: ", "moved towards cryptobox");
    telemetry.update();

    //turn
    new GyroTurn(-90, robot, telemetry);
    telemetry.addData("currently: ", "should have turned left 90");
    telemetry.update();

    new DriveForDistEst(10, 6.223f, 1, robot);
    telemetry.addData("currently: ", "moved towards cryptobox");
    telemetry.update();
    //drive

    new Release(robot);

    //glyph

    //drive straight

  }
}
