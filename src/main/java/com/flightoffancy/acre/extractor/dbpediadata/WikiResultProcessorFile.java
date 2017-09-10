package com.flightoffancy.acre.extractor.dbpediadata;

import java.io.*;

public class WikiResultProcessorFile implements  WikiResultProcessor {
    public WikiResultProcessorFile() {

    }

    @Override
    public void processResult(WikiMapObject object, PrintWriter out) {
        String str = object.toString();
        out.println(str);
    }
}
