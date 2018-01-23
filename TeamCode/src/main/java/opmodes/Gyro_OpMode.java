package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.commands.DriveForTime;

@Autonomous(name = "Gyro OpMode", group = "Test OpMode")
public class Gyro_OpMode extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
    Robot robot = new Robot2017(hardwareMap);

    waitForStart();

    new DriveForTime(5, 0.5f, robot).start();
  }
}
