package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.ColorSensor;

/**
 * Created by rachel on 1/24/2018.
 */

public class RedBlueKnock extends Command{

    private ColorSensor sensyboye;
    private Telemetry telem;
    public RedBlueKnock(Telemetry telem, Robot robot){
        sensyboye = (ColorSensor) robot.getSensor("color");
        this.telem = telem;
    }
    boolean execute() {

        if(sensyboye.getSensorValue().red > sensyboye.getSensorValue().blue)
            telem.addData("color sensed:", "this is approx. red");
        else {
            telem.addData("color sensed: ", "this is maybe more blue than red");
        }
                
        return false;
    }
}
