package org.firstinspires.ftc.teamcode.sensors;

/**
 *
 */
public abstract class Sensor {
    public abstract Object getSensorValue();
    public boolean calibrate() {
        return true; // So that sensors that needn't calibration needn't @Override
    };
}