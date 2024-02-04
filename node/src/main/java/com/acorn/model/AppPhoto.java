package com.acorn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
public class AppPhoto extends Schema {

    private String fileId;

    private String fileName;

    private String mimeType;

    private Long fileSize;

    private BinaryContent binaryContent;
}
