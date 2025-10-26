package com.rahuldas.nasaapod.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApodResponse {
    private String date;
    private String title;
    private String explanation;
    private String url;

    @JsonProperty("hdurl")
    private String hdurl;

    @JsonProperty("media_type")
    private String mediaType;

    private String copyright;
}
