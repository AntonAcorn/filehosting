package com.acorn.service;

import com.acorn.model.BinaryContent;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileService {

    ResponseEntity<Resource> getDocById(String docId);

    ResponseEntity<Resource> getPhotoById(String photoId);

    FileSystemResource getFileSystemResource(BinaryContent binaryContent);
}
