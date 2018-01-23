package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.sensors.MRGyro;
import org.firstinspires.ftc.teamcode.sensors.VuMarkIdentify;

/**
 * The 2017-2018 season robot.
 */
public class Robot2017 extends Robot {

  /**
   * Constructor
   *
   * @param hwMap The hardware map
   */
  public Robot2017(HardwareMap hwMap) {
    // Get the motors from the hardware map: based on the robot configuration
    DcMotor leftDrive = hwMap.get(DcMotor.class, "left_drive");
    DcMotor rightDrive = hwMap.get(DcMotor.class, "right_drive");

    Servo knock = hwMap.get(Servo.class, "knock");
    Servo clawR = hwMap.get(Servo.class, "claw_r");
    Servo clawL = hwMap.get(Servo.class, "claw_l");

    // Set motor direction: this is based on how the robot was built
    leftDrive.setDirection(DcMotor.Direction.FORWARD);
    rightDrive.setDirection(DcMotor.Direction.REVERSE);

    setForwardMotors(new MotorGroup(
        1440,
        319,
        leftDrive,
        rightDrive
    ));
    setTurnMotors(new MotorGroup[]{
        new MotorGroup(
            1440,
            319,
            leftDrive
        ),
        new MotorGroup(
            1440,
            319,
            rightDrive
        )
    });
    setStrafeMotors(null); // This line is optional: it disables strafe, disabled is default

    DcMotor lift = hwMap.get(DcMotor.class, "lift");

    lift.setDirection(Direction.FORWARD);

    lift.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);

    addOtherMotor("lift", new MotorGroup(1, 1, lift));

    // Sensors
    addSensor("vuMarkPictograph", new VuMarkIdentify("RelicVuMark", hwMap, true));
    addSensor("gyro", new MRGyro(hwMap, "gyro"));

    // Servos
    addServo("claw", new ServoGroup(clawL, clawR));
    addServo("knock", new ServoGroup(knock));
  }
}
