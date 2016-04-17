package com.example.rachel.myfirstapp;
import java.util.ArrayList;
/**
 * Created by William Glanton on 4/15/2016.
 */
public class CommandLogger {
    private ArrayList<Command> commandLog;
    private static volatile CommandLogger singleton;

    private CommandLogger(){
        commandLog = new ArrayList<Command>();
    }

    public static CommandLogger getInstance(){
        if(singleton == null){
            synchronized(CommandLogger.class) {
                if(singleton == null) {
                    singleton = new CommandLogger();
                }
            }
        }
        return singleton;
    }

    public void logCommand(Command cmd){
        commandLog.add(cmd);
    }
}
