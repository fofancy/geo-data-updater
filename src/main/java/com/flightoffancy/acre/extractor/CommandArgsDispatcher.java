package com.flightoffancy.acre.extractor;

import com.flightoffancy.acre.extractor.dbpediadata.DbpediaCommand;

import java.util.Arrays;

public class CommandArgsDispatcher {

    public void forward(String[] appArgs) {
        if(appArgs.length == 0)
            throw new CommandLineException("Command wasn't specified");

        String commandName = appArgs[0];
        String[] commandArgs = Arrays.copyOfRange(appArgs, 1, appArgs.length);

        switch (commandName){
            case "dbpedia" : {
                DbpediaCommand dbpediaCommand = new DbpediaCommand();
                dbpediaCommand.execute(commandArgs);
            }
        }

    }
}
