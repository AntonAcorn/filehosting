package com.acorn.controller;

import com.acorn.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/get-photo")
    public ResponseEntity<?> getPhotoById(@RequestParam String id) {
        return fileService.getPhotoById(id);
    }

    @GetMapping("/get-document")
    public ResponseEntity<?> getDocumentById(@RequestParam String docId) {
        return fileService.getDocById(docId);
    }

}
