package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.MotorGroup;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.sensors.MrGyro;

@Autonomous(name = "Sensor OpMode", group = "Test OpMode")
public class Sensor_OpMode extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
    Robot robot = new Robot2017(hardwareMap);

    MotorGroup lift = robot.getOtherMotor("lift");
    lift.useEncoders();
    lift.resetEncoders();

    MrGyro gyro = (MrGyro) robot.getSensor("gyro");
    gyro.calibrate();

    waitForStart();

    while (opModeIsActive()) {
      telemetry.addData("MrGyro angle 1", gyro.getHeading());
      telemetry.addData("Motor encoders lift", lift.getAverageClicks());
      telemetry.update();
    }
  }
}
