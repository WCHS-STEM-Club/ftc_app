package org.firstinspires.ftc.teamcode;

public abstract class Robot {
    public final MotorGroup forwardMotors;
    public final MotorGroup[] turnMotors;
    public final MotorGroup[] strafeMotors;

    /**
     * Constructor
     *
     * @param forwardMotors A MotorGroup with motors that go forward when you want to go forward.
     * @param turnMotors A list of MotorGroups. On a right turn, element 0 goes forward and 1 goes
     *                   backward.
     * @param strafeMotors A list of MotorGroups. On a strafe right, element 0 goes forward and
     *                     1 goes backward.
     */
    public Robot(MotorGroup forwardMotors, MotorGroup[] turnMotors, MotorGroup[] strafeMotors) {
        this.forwardMotors = forwardMotors;
        this.turnMotors = turnMotors;
        this.strafeMotors = strafeMotors;
    }
}
