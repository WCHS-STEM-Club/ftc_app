package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType;

public class MockDcMotor implements DcMotor {

  public MotorConfigurationType motorConfigurationType = null;
  public DcMotorController dcMotorController;
  public int portNumber;
  public ZeroPowerBehavior zeroPowerBehavior;
  public int targetPosition;
  public boolean busy;
  public int currentPosition;
  public RunMode runMode;
  public Direction direction;
  public double power;
  public Manufacturer manufacturer;
  public String name;
  public int version;

  public MockDcMotor() {
    resetDeviceConfigurationForOpMode();
  }

  @Override
  public MotorConfigurationType getMotorType() {
    return motorConfigurationType;
  }

  @Override
  public void setMotorType(MotorConfigurationType motorType) {
    this.motorConfigurationType = motorType;
  }

  @Override
  public DcMotorController getController() {
    return dcMotorController;
  }

  @Override
  public int getPortNumber() {
    return portNumber;
  }

  @Override
  public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
    this.zeroPowerBehavior = zeroPowerBehavior;
  }

  @Override
  public ZeroPowerBehavior getZeroPowerBehavior() {
    return this.zeroPowerBehavior;
  }

  @Override
  public void setPowerFloat() {
    // Deprecated method
  }

  @Override
  public boolean getPowerFloat() {
    return false; // Deprecated method
  }

  @Override
  public void setTargetPosition(int position) {
    this.targetPosition = position;
  }

  @Override
  public int getTargetPosition() {
    return this.targetPosition;
  }

  @Override
  public boolean isBusy() {
    return this.busy || this.power != 0;
  }

  @Override
  public int getCurrentPosition() {
    return this.currentPosition;
  }

  @Override
  public void setMode(RunMode mode) {
    this.runMode = mode;
    switch (mode) {
      case RUN_TO_POSITION:
        this.currentPosition = this.targetPosition;
        break;
      case STOP_AND_RESET_ENCODER:
        this.currentPosition = 0;
    }
  }

  @Override
  public RunMode getMode() {
    return this.runMode;
  }

  @Override
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  @Override
  public Direction getDirection() {
    return this.direction;
  }

  @Override
  public void setPower(double power) {
    this.power = power;
  }

  @Override
  public double getPower() {
    return this.power;
  }

  @Override
  public Manufacturer getManufacturer() {
    return this.manufacturer;
  }

  @Override
  public String getDeviceName() {
    return this.name;
  }

  @Override
  public String getConnectionInfo() {
    return null; // TODO: Figure this out someday
  }

  @Override
  public int getVersion() {
    return this.version;
  }

  @Override
  public void resetDeviceConfigurationForOpMode() {
    this.dcMotorController = null;
    this.portNumber = 0;
    this.zeroPowerBehavior = ZeroPowerBehavior.BRAKE;
    this.targetPosition = 0;
    this.busy = false;
    this.currentPosition = 0;
    this.runMode = RunMode.RUN_WITHOUT_ENCODER;
    this.direction = Direction.FORWARD;
    this.power = 0;
    this.manufacturer = Manufacturer.Unknown;
    this.name = "Mock DcMotor";
    this.version = 0;
  }

  @Override
  public void close() {

  }
}