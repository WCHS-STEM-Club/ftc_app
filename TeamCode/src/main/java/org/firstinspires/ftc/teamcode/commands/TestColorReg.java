/*package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.qualcomm.analytics.Analytics;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

*/
/*
public class TestColorReg implements Command {
    private ColorSensor sensorboyo;
    Telemetry telem;



    public TestColorReg(ColorSensor a, Telemetry c) {
        sensorboyo = a;
        telem = c;
    }

    @Override
    public boolean executeCommand(Robot robot) {
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();

        telem.log().setCapacity(10);

        for(int i=0; i<10;i++) {
            telem.addData("caption", sensorboyo.red());
        }

        return true;
    }

}
*/