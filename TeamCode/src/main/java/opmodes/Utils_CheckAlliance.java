package opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcontroller.external.AllianceGetter;
import org.firstinspires.ftc.robotcontroller.external.AllianceGetter.Alliance;

@Autonomous(name = "Util Check Alliance", group = "Util")
public class Utils_CheckAlliance extends OpMode {
  @Override
  public void init() {
    telemetry.addData("Status", "Initializing");

    Alliance alliance = AllianceGetter.getAlliance();

    // Tell the driver that initialization is complete.
    telemetry.addData("Alliance", alliance);
  }

  @Override
  public void loop() {}
}
