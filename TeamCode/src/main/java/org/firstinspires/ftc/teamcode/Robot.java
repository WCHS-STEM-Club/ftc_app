package org.firstinspires.ftc.teamcode;

public abstract class Robot {
    public boolean ready = false;

    public boolean canStrafe = false; // False because when setStrafeMotors is never called, robot cannot strafe

    public MotorGroup forwardMotors;
    public MotorGroup[] turnMotors;
    public MotorGroup[] strafeMotors;

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
        setForwardMotors(forwardMotors);
        setTurnMotors(turnMotors);
        setStrafeMotors(strafeMotors);
    }

    public Robot() {
        // Ready not set, so motors must be added some other way
    }

    public void setForwardMotors(MotorGroup forwardMotors) {
        if (ParamCheck.isNull(forwardMotors)) {
            throw new IllegalArgumentException("Null input(s) to Robot constructor. Only strafeMotors[] " +
                    "may be null.");
        }

        this.forwardMotors = forwardMotors;

        if (!ParamCheck.isNull(this.turnMotors)) ready = true;
    }

    public void setTurnMotors(MotorGroup[] turnMotors) {
        if (ParamCheck.isNull(turnMotors)) {
            throw new IllegalArgumentException("turnMotors is null, cannot be null.");
        }

        if (!ParamCheck.arrayOfLength(turnMotors, 2)) {
            throw new IllegalArgumentException("turnMotors doesn't have a length of 2, must have a length of 2.");
        }

        if (ParamCheck.containsNull(turnMotors)) {
            throw new IllegalArgumentException("turnMotors has null elements, cannot be null.");
        }

        this.turnMotors = turnMotors;

        if (!ParamCheck.isNull(this.forwardMotors)) ready = true;
    }

    public void setStrafeMotors(MotorGroup[] strafeMotors) {
        if (ParamCheck.isNull(strafeMotors)) {
            canStrafe = false;
            return;
        }

        if (!ParamCheck.arrayOfLength(strafeMotors, 2)) {
            throw new IllegalArgumentException("strafeMotors doesn't have a length of 2, must have a length of 2.");
        }

        if (ParamCheck.containsNull(strafeMotors)) {
            throw new IllegalArgumentException("strafeMotors has null elements, cannot be null.");
        }

        this.strafeMotors = strafeMotors;

        canStrafe = true;
    }
}
