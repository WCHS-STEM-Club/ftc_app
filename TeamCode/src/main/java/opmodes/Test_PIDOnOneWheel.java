package opmodes;

import com.nathanvarner.pid.PID;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MotorGroup;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;

@TeleOp(name="Test PID On One Wheel", group="Test")
public class Test_PIDOnOneWheel extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
    Robot robot = new Robot2017(hardwareMap);
    MotorGroup testMotor = robot.getOtherMotor("test");

    final double SETPOINT = 500;

    PID pid = new PID(0, 0, 0, SETPOINT);

    testMotor.resetEncoders();
    testMotor.useEncoders();
    testMotor.coast();
    sleep(100);

    waitForStart();

    while (opModeIsActive()) {
      double power = pid.pid(testMotor.getAverageClicks());
      if (gamepad1.y) {
        testMotor.setPower(power);
      } else {
        testMotor.setPower(0);
      }

      telemetry.addData("Motor power", power);
      telemetry.addData("Motor encoders", testMotor.getAverageClicks());
      telemetry.addData("Encoders target", SETPOINT);
      telemetry.update();
    }
  }
}
