package opmodes;

import com.nathanvarner.units.Units;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.commands.DriveForDistEst;
import org.firstinspires.ftc.teamcode.commands.GyroTurn;

@Autonomous(name = "Gyro OpMode", group = "Test OpMode")
public class Gyro_OpMode extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
    Robot robot = new Robot2017(hardwareMap);

    waitForStart();

    new GyroTurn(90, Units.degree, 1, robot, telemetry).start();
    // 6.223 decimeters/sec
    new DriveForDistEst(20, 6.223f, 1, robot).start();
  }
}
