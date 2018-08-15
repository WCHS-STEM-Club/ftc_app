package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Sensor that detects and reads VuMarks.
 */
public class VuMarkIdentify implements Sensor {

  /*
   * My legit working licence key
   * It's an actual licence key
   * So don't mess with it
   * I'm talking to you, Danny
   */
  private final String licenceKey = "AVe/9AP/////AAAAmeNenbwd9E/Rq2fv3JjhC4khnm7De7Eq/w9wTxBd1Xu8w+g5hAnBXWyAhNpCY5my63ZPUGsdiGqhavffUHyISwN5Qo1KVp3AQ3ASwOmawvB7Bk2kLvlWrXf+zvC9imQHUB84p+jY+N2vM0Ktav/a93d9WCsAZPqiyEPOAHcylbSj93MtF3lwCLwX3beL5MpKirkqHaVZ640fq9jDgcxShcFjePxPfobVP9ZwLu5orXYTujxh149OdNj14FYuo2pf/jzym6+zRmat7SuVTlFVymPEtwhr9DH9Kr/gTSKKWAwxrmfeuUFmZOG7CK99+m8+4O9/NFWSLNfqyLHScaXrE9G/SRiCE89g9jXPhPuP8uJY";

  private VuforiaTrackable template;
  private VuforiaLocalizer.CameraDirection direction = VuforiaLocalizer.CameraDirection.BACK;//back by default
  /**
   * Constructor
   *
   * @param assetName The name of the asset to load. This can be found in FTC's online
   * documentation.
   * @param hwMap Access to the hardware map, used to get access to the app activity.
   * @param displayOnDs True to show the camera feed on the screen, false to not. If true, it will
   * use more battery.
   */
  public VuMarkIdentify(String assetName, HardwareMap hwMap, boolean displayOnDs) {
    // Uncomment these lines and change the ID to put the camera view onto the RC
    int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier(
        "cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
    VuforiaLocalizer.Parameters parameters = displayOnDs ?
        new VuforiaLocalizer.Parameters(cameraMonitorViewId) :
        new VuforiaLocalizer.Parameters();

    parameters.vuforiaLicenseKey = this.licenceKey;
    parameters.cameraDirection = direction; // Or, FRONT/selfie cam
    VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);

    VuforiaTrackables trackables = vuforia.loadTrackablesFromAsset(assetName);
    this.template = trackables.get(0);
    this.template.setName("vuMarkTemplate");
    trackables.activate();
  }

  /**
   * Get the value of the VuMark- this function HAS TO BE CHANGED YEARLY!!! If it doesn't work,
   * check online for the documentation for this year, update this function, and try again.
   *
   * @return RelicRecoveryVuMark, an enum that could be LEFT, RIGHT, CENTER, or UNKNOWN
   */
  public RelicRecoveryVuMark readVuMark() {
    return RelicRecoveryVuMark.from(this.template);
  }

  /**
   * This cannot be calibrated, therefore nothing happens.
   * @return True; no calibration can occur
   */
  @Override
  public boolean calibrate() {
    return true;
  }

  public void changeDirection(VuforiaLocalizer.CameraDirection newDirection){
    direction = newDirection;
  }

  /**
   * As this sensor cannot be calibrated, the calibration cannot be saved
   * @return An empty string; there is no calibration
   */
  @Override
  public String saveCalibration() {
    return "";
  }

  /**
   * As this sensor cannot be calibrated, the calibration cannot be saved
   * @param calibration An empty string; there is no calibration
   * @return True; there is no calibration
   */
  @Override
  public boolean loadCalibration(String calibration) {
    return true;
  }
}
