package org.firstinspires.ftc.teamcode;

import android.os.AsyncTask;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.Command;

import java.util.ArrayList;

public class Robot2017 extends Robot {
    private HardwareMap hwMap;

    private ArrayList<Command> commandCache;
    private boolean runningCommands = false;

    public Robot2017(HardwareMap hwMap) {
        super(1440, 319);

        this.hwMap = hwMap;
        this.commandCache = new ArrayList<>();
    }

    @Override
    public boolean addCommand(Command command) {
        commandCache.add(command);
        return true;
    }

    @Override
    public boolean stop() {
        runningCommands = false;

        return true;
    }

    @Override
    public boolean start() {
        runningCommands = true;
        while (runningCommands && commandCache.size() > 0) {
            Command nextCommand = commandCache.get(0);
            nextCommand.executeCommand(this);
            commandCache.remove(0);
        }

        return true;
    }

    @Override
    public HardwareMap getHwMap() {
        return hwMap;
    }

    private static class RunCommand extends AsyncTask<Object, Void, Object> {
        // Args: Command, Robot
        protected Object doInBackground(Object... args) {
            if (args.length != 2) return false;
            if (args[0].getClass() != Command.class) return false;
            if (args[1].getClass() != HardwareMap.class) return false;

            Command command = (Command) args[0];
            Robot robot = (Robot) args[1];

            return command.executeCommand(robot);
        }
    }
}
