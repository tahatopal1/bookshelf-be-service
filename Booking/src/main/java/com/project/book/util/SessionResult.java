package com.project.book.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SessionResult {
    private String errorDesc;
    private int errorCode;
    private String usage; // Holds data could be needed
}
