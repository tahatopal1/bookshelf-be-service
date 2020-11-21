package com.project.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PanelizationSummary {
    private boolean containsEpubBubbles;
    private boolean containsImageBubbles;
}
