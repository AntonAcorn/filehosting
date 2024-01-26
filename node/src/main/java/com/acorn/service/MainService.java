package com.acorn.service;

import com.acorn.model.TelegramEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {

    private final TelegramEventService updateTelegramService;
    private final AccountService accountService;

    public SendMessage processTextMessage(Update update) {
        TelegramEvent telegramEvent = TelegramEvent.builder()
                .update(update)
                .build();
        var event = updateTelegramService.save(telegramEvent);
        var account = accountService.save(telegramEvent);
        log.info("event is saved with ID:[%s]".formatted(event.id));
        log.debug("[NODE] Text message is received from [DISPATCHER]");
        return new SendMessage(update.getMessage().getChatId().toString(), "Hello from NODE");
    }
}
