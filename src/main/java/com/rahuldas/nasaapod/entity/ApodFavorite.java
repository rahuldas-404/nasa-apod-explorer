package com.rahuldas.nasaapod.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "apod_favorites")
public class ApodFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String date;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @Column(nullable = false)
    private String url;

    private String hdurl;
    private String mediaType;
    private String copyright;

    // Constructors
    public ApodFavorite() {}

    public ApodFavorite(Long id, String date, String title, String explanation, String url) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.explanation = explanation;
        this.url = url;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getHdurl() { return hdurl; }
    public void setHdurl(String hdurl) { this.hdurl = hdurl; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public String getCopyright() { return copyright; }
    public void setCopyright(String copyright) { this.copyright = copyright; }
}
