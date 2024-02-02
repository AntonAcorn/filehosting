package com.acorn.model;

import com.acorn.entity.BinaryContentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToOne;

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

    private BinaryContentEntity binaryContent;
}
