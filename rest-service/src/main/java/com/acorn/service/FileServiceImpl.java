package com.acorn.service;

import com.acorn.model.BinaryContent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final AppPhotoService appPhotoService;
    private final AppDocumentService appDocumentService;

    @Override
    public ResponseEntity<Resource> getDocById(String docId) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Resource> getPhotoById(String photoId) {
        var appPhotoById = appPhotoService.getAppPhotoById(photoId);
        if (appPhotoById == null) {
            return ResponseEntity.badRequest().build();
        }
        var fileSystemResource = getFileSystemResource(appPhotoById.getBinaryContent());
        if (fileSystemResource == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"photo_from_db_%s.jpg\"", photoId))
                .body(fileSystemResource);
    }

    @Override
    public FileSystemResource getFileSystemResource(BinaryContent binaryContent) {
        try {
            //TODO добавить генерацию временных имен
            File temp = File.createTempFile("tempFile", ".bin");
            temp.deleteOnExit();
            FileUtils.writeByteArrayToFile(temp, binaryContent.getBinaryContent());
            return new FileSystemResource(temp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
