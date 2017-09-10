package com.flightoffancy.acre.extractor.dbpediadata;

import com.flightoffancy.acre.extractor.CommandLineException;

public class DbpediaCommand {
    public void execute(String[] commandArgs) {
        if(commandArgs.length == 0)
            throw new CommandLineException("Input and output files was not specified");
        else if(commandArgs.length == 1)
            throw new CommandLineException("Output file was not specified");
        else {
            String inputFilename = commandArgs[0];
            String outputFilename = commandArgs[1];

            DatasetFileDataExtractor datasetFileDataExtractor = new DatasetFileDataExtractor();

            datasetFileDataExtractor.extractData(inputFilename, outputFilename);
        }
    }
}
