package com.project.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessInfo {
    private String country;
    private String viewability;
    private boolean embeddable;
    private boolean publicDomain;
    private String textToSpeechPermission;
    Epub EpubObject;
    Pdf PdfObject;
    private String webReaderLink;
    private String accessViewStatus;
    private boolean quoteSharingAllowed;
}
