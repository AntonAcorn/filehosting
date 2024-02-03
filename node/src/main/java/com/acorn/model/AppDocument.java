package com.acorn.model;

import com.acorn.entity.BinaryContentEntity;
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
public class AppDocument extends Schema{

    private String fileId;

    private String fileName;

    private String mimeType;

    private Long fileSize;

    private BinaryContent binaryContent;
}
