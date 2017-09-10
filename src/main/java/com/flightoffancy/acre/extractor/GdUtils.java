package com.flightoffancy.acre.extractor;

import com.flightoffancy.acre.extractor.dbpediadata.DatasetFileDataExtractor;

public class GdUtils {

    public static void main(String [] args)
    {
        CommandArgsDispatcher dispatcher = new CommandArgsDispatcher();
        dispatcher.forward(args);

//        System.out.println("GdUtils run");
//
//        String inputFilename = GdUtils.class.getClassLoader().getResource("dbpediadata/dbpedia-dataset.nq").getFile();
//        String outputFilename = GdUtils.class.getClassLoader().getResource("dbpediadata/dbpedia-dataset-mapping-result.txt").getFile();
//
//        DatasetFileDataExtractor datasetFileDataExtractor = new DatasetFileDataExtractor();
//
//        datasetFileDataExtractor.extractData(inputFilename, outputFilename);

    }
}
