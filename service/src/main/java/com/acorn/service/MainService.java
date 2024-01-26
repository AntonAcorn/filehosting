package com.acorn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {

    private final RawDataService rawDataService;

    public SendMessage processTextMessage(Update update) {
        var rawData = rawDataService.save(update);
        log.info("RawData is saved with ID:[%s]".formatted(rawData.id));
        log.debug("[NODE] Text message is received from [DISPATCHER]");
        return new SendMessage(update.getMessage().getChatId().toString(), "Hello from NODE");
    }
}
