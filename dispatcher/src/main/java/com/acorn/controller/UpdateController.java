package com.acorn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/***
 *  This component is intended to distribute incoming messages from the telegramBot
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateController {

    private TelegramBot telegramBot;

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("Received update is Null");
            return;
        }
        if (update.hasMessage()) {
            distributeMessageByType(update);
        } else {
            log.error("Received unsupported message type");
        }

    }

    private void distributeMessageByType(Update update) {
        var message = update.getMessage();
        if (message.getText() != null) {
            processTextMessage(update);
        } else if (message.getDocument() != null) {
            processDocumentMessage(update);
        } else if (message.getPhoto() != null) {
            processPhotoMessage(update);
        } else {
            processUnsupportedMessage(update);
        }
    }

    private void processUnsupportedMessage(Update update) {
    }

    private void processPhotoMessage(Update update) {
    }

    private void processDocumentMessage(Update update) {
    }

    private void processTextMessage(Update update) {
    }
}
