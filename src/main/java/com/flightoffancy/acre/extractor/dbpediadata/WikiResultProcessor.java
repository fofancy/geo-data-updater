package com.flightoffancy.acre.extractor.dbpediadata;

import com.flightoffancy.acre.extractor.dbpediadata.WikiMapObject;

import java.io.OutputStream;
import java.io.PrintWriter;

public interface WikiResultProcessor {

    void processResult (WikiMapObject object, PrintWriter out);
}
