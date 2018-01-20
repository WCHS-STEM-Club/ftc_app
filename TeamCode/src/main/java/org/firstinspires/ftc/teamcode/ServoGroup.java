package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoGroup {

    private double midwayPoint;
    private Servo[] servos;


    public ServoGroup(Servo... servos) {
        this.midwayPoint = Servo.MAX_POSITION + Servo.MIN_POSITION / 2;
        this.servos = servos;
    }

    /**
     * Halt all servos in the group.
     */
    public void stop() {
        for (Servo servo : this.servos) {
            servo.setPosition(servo.getPosition()); // Set it to where it is so it has nowhere to go
        }
    }

    public void setAngle(double degrees) {
        for (Servo servo : this.servos) {
            servo.setPosition(toServoUnits(degrees));
        }
    }

    public void setDirection(Servo.Direction newdirection) {
        for (Servo servo : this.servos) {
            servo.setDirection(newdirection);
        }
    }


    public String[] getDirection() {
        String[] directionlist = new String[servos.length];
        for(int x = 0; x < servos.length; x++){
            directionlist[x] = servos[x].getDirection().name();
        }
        return directionlist;
    }

    //midway point being default position
    public void setDefaultPos() {
        for (Servo servo : this.servos) {
            servo.setPosition(midwayPoint);
        }
    }

    //should be in degrees
    public void scaleServos(double newMin, double newMax) {
        for (Servo servo : this.servos) {
            servo.scaleRange(newMin,newMax);
        }
    }

    //since this really only matters in the case of servos oriented in a specific way, we're just going to do a lot
    //of assumption here
    public void converge(){
        servos[0].setDirection(Servo.Direction.FORWARD);
        servos[1].setDirection(Servo.Direction.REVERSE);

    }

    //servoIndex params should not exceed servos array
    public Servo getServo(int servoIndex){
        return servos[servoIndex];
    }


    //degrees should only be between 0 and 180 because like,,, no continous rotation as a motor has
    private double toServoUnits(double degrees) {
        return (degrees / 180 * 2) - 1;
    }
}
