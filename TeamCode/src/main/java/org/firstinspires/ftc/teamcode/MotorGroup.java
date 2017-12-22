package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorGroup {
    private DcMotor[] motors;
    private boolean encodersReset = false;

    public final int X_CLICKS;
    public final double MM_PER_X_CLICKS;

    public MotorGroup(int xClicks, double mmPerXClicks, DcMotor... motors) {
        this.X_CLICKS = xClicks;
        this.MM_PER_X_CLICKS = mmPerXClicks;
        this.motors = motors;

        resetEncoders();
    }

    public void setPower(float power) {
        for (DcMotor motor : this.motors) {
            motor.setPower(power);
        }

        encodersReset = false;
    }

    public void brake() {
        for (DcMotor motor : this.motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        this.setPower(0);
    }

    public void coast() {
        for (DcMotor motor : this.motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        this.setPower(0);
    }

    public void useEncoders() {
        for (DcMotor motor : this.motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /**
     * Move the motors for some distance
     * @param distance Distance in decimeters
     */
    public void goForDistance(float distance, float power) {
        if (!encodersReset) resetEncoders();

        float mmDist = distance * 100; // Decimeters to millimeters
        int clicks = (int)(mmDist * (float)(X_CLICKS / MM_PER_X_CLICKS)); // Distance -> clicks

        for (DcMotor motor : this.motors) {
            int target = motor.getCurrentPosition() + clicks;
            motor.setTargetPosition(target);

            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        setPower(power);
    }

    public void resetEncoders() {
        for (DcMotor motor : this.motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        encodersReset = true;
    }

    public boolean isBusy() {
        boolean busy = false;

        for (DcMotor motor : this.motors) {
            if (motor.isBusy()) busy = true;
        }

        return busy;
    }
}
