package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MotorGroup;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.commands.PIDCalc;

@TeleOp(name = "PID OpMode", group = "Test OpMode")
public class PID_OpMode extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
    Robot robot = new Robot2017(hardwareMap);
    MotorGroup testMotor = robot.getOtherMotor("test");
    PIDCalc pid = new PIDCalc();

    testMotor.resetEncoders();
    testMotor.useEncoders();
    testMotor.coast();
    sleep(100);

    waitForStart();

    double gainP = 0;
    double gainI = 0;
    double gainD = 0;

    while (opModeIsActive()) {
      if (gamepad1.a) {
        gainP = (gamepad1.left_stick_y * 0.5) + 0.5; // Max of 1, min of 0
      }
      if (gamepad1.b) {
        gainI = (gamepad1.left_stick_y * 2.5) + 2.5; // Max of 5, min of 0
      }
      if (gamepad1.x) {
        gainD = (gamepad1.left_stick_y * 2.5) + 2.5; // Max of 5, min of 0
      }

      float power = (float) pid.calcPid(testMotor.getAverageClicks(), 0, gainP, gainI, gainD);
      testMotor.setPower(power);

      telemetry.addData("Gain P", gainP);
      telemetry.addData("Gain I", gainI);
      telemetry.addData("Gain D", gainD);

      telemetry.addData("Motor power", power);
      telemetry.addData("Motor encoders", testMotor.getAverageClicks());
      telemetry.addData("Encoders target", 500);
      telemetry.update();
    }
  }
}
