package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot2017;

/**
 * Created by rachel on 1/19/2018.
 */
@Autonomous (name = "Right and Left")
public class TestPIDTurnWithTooMuchMath_OpMode extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException {

        Robot2017 samuel = new Robot2017(hardwareMap);


        telemetry.addData("Status", "started");
        telemetry.update();


        telemetry.addData("Status", "should have done nothing");

        telemetry.addData("Status", "should have turned right");

        telemetry.addData("Status", "should have turned left");



    }
}
