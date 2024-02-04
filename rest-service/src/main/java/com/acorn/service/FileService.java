package com.acorn.service;

import com.acorn.model.AppDocument;
import com.acorn.model.AppPhoto;
import com.acorn.model.BinaryContent;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileService {

    AppDocument getDoc(String docId);

    ResponseEntity<Resource> getPhoto(String photoId);

    FileSystemResource getFileSystemResource(BinaryContent binaryContent);
}
