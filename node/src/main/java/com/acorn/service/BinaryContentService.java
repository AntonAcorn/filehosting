package com.acorn.service;

import com.acorn.entity.BinaryContentEntity;
import com.acorn.model.BinaryContent;
import com.acorn.repository.BinaryContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BinaryContentService {

    private final BinaryContentRepository binaryContentRepository;

    public BinaryContentEntity save (byte[] binaryContent) {
        return binaryContentRepository.save(binaryContent);
    }
}
