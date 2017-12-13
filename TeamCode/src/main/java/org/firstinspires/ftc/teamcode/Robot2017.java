package org.firstinspires.ftc.teamcode;

import android.os.AsyncTask;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.Command;

import java.util.ArrayList;

public class Robot2017 extends Robot {
    private HardwareMap hwMap;

    public Robot2017(HardwareMap hwMap) {
        super(1440, 319);

        this.hwMap = hwMap;
    }

    @Override
    public HardwareMap getHwMap() {
        return hwMap;
    }
}
