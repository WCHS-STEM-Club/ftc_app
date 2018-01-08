package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Command to drive the robot forward for some distance
 */
public class VuMarkIdentify extends Sensor {
    // My legit working licence key
    // It's an actual licence key
    // So don't mess with it
    // I'm talking to you, Danny
    private final String licenceKey = "AVe/9AP/////AAAAmeNenbwd9E/Rq2fv3JjhC4khnm7De7Eq/w9wTxBd1Xu8w+g5hAnBXWyAhNpCY5my63ZPUGsdiGqhavffUHyISwN5Qo1KVp3AQ3ASwOmawvB7Bk2kLvlWrXf+zvC9imQHUB84p+jY+N2vM0Ktav/a93d9WCsAZPqiyEPOAHcylbSj93MtF3lwCLwX3beL5MpKirkqHaVZ640fq9jDgcxShcFjePxPfobVP9ZwLu5orXYTujxh149OdNj14FYuo2pf/jzym6+zRmat7SuVTlFVymPEtwhr9DH9Kr/gTSKKWAwxrmfeuUFmZOG7CK99+m8+4O9/NFWSLNfqyLHScaXrE9G/SRiCE89g9jXPhPuP8uJY";

    private VuforiaTrackables trackables;
    private VuforiaTrackable template;

    public VuMarkIdentify(String assetName, HardwareMap hwMap) {
        // Uncomment these lines and change the ID to put the camera view onto the RC
//        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier(
//                "cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters =
//                new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // Camera won't display on the RC
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = licenceKey;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK; // Or, FRONT/selfie cam
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        trackables = vuforia.loadTrackablesFromAsset(assetName);
//                     this.vuforia.loadTrackablesFromAsset("RelicVuMark");

        template = trackables.get(0);
    }

    /**
     * Get the value of the VuMark- this function HAS TO BE CHANGED YEARLY!!! If it doesn't work,
     * check online for the documentation for this year, update this function, and try again.
     * @return RelicRecoveryVuMark, an enum that could be LEFT, RIGHT, CENTER, or UNKNOWN
     */
    @Override
    public Object getSensorValue() {
        trackables.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(template);
        trackables.deactivate();

        return vuMark;
    }
}
