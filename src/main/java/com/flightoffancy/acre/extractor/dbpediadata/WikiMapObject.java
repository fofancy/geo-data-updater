package com.flightoffancy.acre.extractor.dbpediadata;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class WikiMapObject implements Serializable {
    private String wikiPageLink;
    private float latitude;
    private float longitude;

    public WikiMapObject( String wikiPageLink, Float latitude, Float longitude) {
        this.wikiPageLink = wikiPageLink;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getWikiPageLink() {
        return wikiPageLink;
    }

    public void setWikiPageLink(String wikiPageLink) {
        this.wikiPageLink = wikiPageLink;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void writeToStream(OutputStream outputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        try(JsonGenerator g = objectMapper.getJsonFactory().createJsonGenerator(outputStream)) {
            objectMapper.writeValue(g, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();

        String str = null;
        try {
            str = objectMapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }
}
