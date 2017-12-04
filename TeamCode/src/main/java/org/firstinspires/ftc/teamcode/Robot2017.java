package org.firstinspires.ftc.teamcode;

import android.os.AsyncTask;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.Command;

import java.util.ArrayList;

public class Robot2017 implements Robot {
    private HardwareMap hwMap;
    private ArrayList<Command> commandCache;
    private boolean runningCommands = false;

    public Robot2017(HardwareMap hwMap) {
        this.hwMap = hwMap;
        this.commandCache = new ArrayList<>();
    }

    @Override
    public boolean addCommand(Command command) {
        commandCache.add(command);
        return true;
    }

    @Override
    public boolean pause() {
        runningCommands = false;
        return true;
    }

    @Override
    public boolean start() {
        runningCommands = true;
        while (runningCommands) {
            Command nextCommand = commandCache.get(0);
            new RunCommand().execute(nextCommand, hwMap);
            commandCache.remove(0);
        }

        return true;
    }

    private static class RunCommand extends AsyncTask<Object, Void, Object> {
        // Args: Command, HardwareMap
        protected Object doInBackground(Object... args) {
            if (args.length != 2) return false;
            if (args[0].getClass() != Command.class) return false;
            if (args[1].getClass() != HardwareMap.class) return false;

            Command command = (Command) args[0];
            HardwareMap hwMap = (HardwareMap) args[1];

            return command.executeCommand();
        }
    }
}
