package com.project.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolumeInfo {
    private String title;
    ArrayList<Object> authors = new ArrayList<Object>();
    private String publisher;
    private String publishedDate;
    private String description;
    ArrayList<Object> industryIdentifiers = new ArrayList<Object>();
    ReadingModes ReadingModesObject;
    private float pageCount;
    private float printedPageCount;
    Dimensions DimensionsObject;
    private String printType;
    ArrayList<Object> categories = new ArrayList<Object>();
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    PanelizationSummary PanelizationSummaryObject;
    ImageLinks ImageLinksObject;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
}
