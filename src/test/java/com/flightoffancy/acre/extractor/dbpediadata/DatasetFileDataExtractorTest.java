package com.flightoffancy.acre.extractor.dbpediadata;

import org.junit.Test;

public class DatasetFileDataExtractorTest {
    @Test
    public void extractData() throws Exception {
        String inputFilename = getClass().getClassLoader().getResource("geodataextractiontest.txt").getFile();
        String outputFilename = getClass().getClassLoader().getResource("geodataextractiontest-result.txt").getFile();

        DatasetFileDataExtractor datasetFileDataExtractor = new DatasetFileDataExtractor();

        datasetFileDataExtractor.extractData(inputFilename, outputFilename);
    }

}