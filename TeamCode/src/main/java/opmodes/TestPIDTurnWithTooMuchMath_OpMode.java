package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot2017;
import org.firstinspires.ftc.teamcode.commands.NormalTurnButIinjectedItWithPIDToMakeItAMoreAccurateCrappyTurn;

/**
 * Created by rachel on 1/19/2018.
 */
@Autonomous (name = "Right and Left")
public class TestPIDTurnWithTooMuchMath_OpMode extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException {

        Robot2017 samuel = new Robot2017(hardwareMap);
        NormalTurnButIinjectedItWithPIDToMakeItAMoreAccurateCrappyTurn testright = new NormalTurnButIinjectedItWithPIDToMakeItAMoreAccurateCrappyTurn(90,1, samuel, this);
        NormalTurnButIinjectedItWithPIDToMakeItAMoreAccurateCrappyTurn testleft = new NormalTurnButIinjectedItWithPIDToMakeItAMoreAccurateCrappyTurn(-90, 1, samuel, this);

        telemetry.addData("Status", "started");
        telemetry.update();

        sleep(1000);

        telemetry.addData("Status", "should have done nothing");
        telemetry.update();

        testright.start();

        telemetry.addData("Status", "should have turned right");
        telemetry.update();

        testleft.start();

        telemetry.addData("Status", "should have turned left");
        telemetry.update();


    }
}
