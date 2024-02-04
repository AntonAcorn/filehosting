package com.acorn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppPhotoService {

    private final BinaryContentService binaryContentService;
}
