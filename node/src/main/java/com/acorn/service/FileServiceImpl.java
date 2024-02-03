package com.acorn.service;

import com.acorn.model.BinaryContent;
import com.acorn.model.TelegramEvent;
import com.acorn.repository.BinaryContentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${token}")
    private String token;

    @Value("${service.file.uri.file_info}")
    private String fileInfoUri;

    @Value("${service.file.uri.download}")
    private String fileDownloadUri;

    private final BinaryContentRepository binaryContentRepository;

    @Override
    public void processDoc(TelegramEvent telegramEvent) {
        var fileId = telegramEvent.getUpdate().getMessage().getDocument().getFileId();
        var filePath = getFilePath(fileId);
        byte[] fileInByte = downloadFile(filePath);
        var binaryContent = BinaryContent.builder()
                .binaryContent(fileInByte)
                .build();
        binaryContentRepository.save(binaryContent);

    }

    /**
     * Retrieves the file path for a given file ID.
     *
     * @param fileId the ID of the file to retrieve the path for
     * @return the file path as a String
     */
    private String getFilePath(String fileId) {
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        var request = new HttpEntity<>(headers);
        try {

            var response = restTemplate.exchange(
                    fileInfoUri,
                    HttpMethod.GET,
                    request,
                    String.class,
                    token, fileId
            );
            if (response.getStatusCode().isError()) {
                log.error("Error response received for file ID: {}", fileId);
                throw new RuntimeException();
            }
            var body = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            var jsonNode = objectMapper.readTree(body);
            return jsonNode.path("result").path("file_path").asText();
        } catch (JsonProcessingException e) {
            log.error("An error occurred while retrieving file path for ID {}: {}", fileId, e.getMessage());
            throw new RuntimeException();
        }
    }
    
    private byte[] downloadFile(String filePath) {
        var uriForDownLoading = fileDownloadUri
                .replace("{token}", token)
                .replace("{file_path}", filePath);
        try {
            URL url = new URL(uriForDownLoading);
            InputStream inputStream = url.openStream();
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
