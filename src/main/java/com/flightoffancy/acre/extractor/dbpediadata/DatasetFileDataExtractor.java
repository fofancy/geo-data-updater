package com.flightoffancy.acre.extractor.dbpediadata;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatasetFileDataExtractor {
    private final String JSON_ARRAY_BEGINNING = "[";
    private final String JSON_ARRAY_ENDING = "]";

    private Logger logger = Logger.getLogger(DatasetFileDataExtractor.class.getName());

    private Pattern p;

    public DatasetFileDataExtractor() {
        String PATTERN_STRING = "point> \"(.*?) (.*?)\"@en <(http:\\/\\/en\\.wikipedia\\.org\\/wiki\\/.*?#)> \\.";

        this.p = Pattern.compile(PATTERN_STRING);
    }

    public void extractData(String filename, String outputFilename) {
        clearFile(outputFilename);

        WikiResultProcessorFile processor = new WikiResultProcessorFile();

        String strLine;
        Matcher m;

        File file = new File(filename);
        float totalSize = file.length();
        float readBytes = 0;
        logger.info("Total length : " + (long) totalSize);

        long startTime = System.currentTimeMillis();
        printCurrentDate(startTime);

        try (FileInputStream fstream = new FileInputStream(filename);
             DataInputStream in = new DataInputStream(fstream);
             BufferedReader br = new BufferedReader(new InputStreamReader(in));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename, true)))) {

            out.print(JSON_ARRAY_BEGINNING);
            while ((strLine = br.readLine()) != null) {
                readBytes += strLine.length();

                m = p.matcher(strLine);
                while (m.find()) {
                    WikiMapObject wikiMapObject = new WikiMapObject(m.group(3),
                            Float.valueOf(m.group(1)),
                            Float.valueOf(m.group(2))
                    );

                    processor.processResult(wikiMapObject, out);
                }

                printProgress(readBytes, startTime, totalSize);
            }
            out.print(JSON_ARRAY_ENDING);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        printFinished(totalSize);
    }


    private void clearFile(String filename) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printCurrentDate(long timeInMilis) {
        Date date = new Date(timeInMilis);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        logger.info("Beginning time: " + dateFormatted);
    }

    private void printProgress(float readBytes, long startTime, float totalSize) {
        long tickEndTime = System.currentTimeMillis();
        float bytesSpeed = 0;
        float remainingSec = 0;

        if(tickEndTime - startTime >= 1000){
            long numbOfSec = (tickEndTime - startTime) / 1000;
            bytesSpeed = readBytes / (float) numbOfSec;
            remainingSec = (totalSize - readBytes) / bytesSpeed;
        }

        float progress = readBytes / totalSize * 100;
        System.out.print("Progress : " + (long)readBytes + "/" + (long)totalSize + " |  " +
                String.format("%.0f", progress) + "%" + " |     " +
                bytesSpeed + " bytes/sec |      " +
                "Remaining time : " + (int) (remainingSec / 60) + "min " + (int) (remainingSec % 60) + "sec " +
                "\r"
        );
    }

    private void printFinished(float totalSize) {
        System.out.print("Progress : " + (long)totalSize + "/" + (long)totalSize + " | " +
                "100%" + " | " +
                0 + " bytes/sec | " +
                "Remaining time : " + (int) 0 + "min " + (int) 0 + "sec " +
                "\r"
        );
    }
}
