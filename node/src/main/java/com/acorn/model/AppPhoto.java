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

    private Long id;

    private String fileId;

    private String fileUniqueId;

    private Integer width;

    private Integer height;

    private Integer fileSize;

    private String filePath;

    private BinaryContent binaryContent;
}
