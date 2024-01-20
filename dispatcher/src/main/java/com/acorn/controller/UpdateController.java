package com.acorn.controller;

import com.acorn.service.UpdateProducer;
import com.acorn.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.acorn.RabbitQueueDestination.DOC_MESSAGE_UPDATE;
import static com.acorn.RabbitQueueDestination.PHOTO_MESSAGE_UPDATE;
import static com.acorn.RabbitQueueDestination.TEXT_MESSAGE_UPDATE;

/***
 *  This component is intended to distribute incoming messages from the telegramBot
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateController {

    private TelegramBot telegramBot;
    private final MessageUtils messageUtils;
    private final UpdateProducer updateProducer;

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
        var message = messageUtils.generateMessageToSend(update, "A not supported type message was received.");
        telegramBot.sendResponseMessage(message);
    }

    private void processPhotoMessage(Update update) {
        updateProducer.produce(PHOTO_MESSAGE_UPDATE, update);
        var message = messageUtils.generateMessageToSend(update, "Photo received");
        telegramBot.sendResponseMessage(message);
    }

    private void processDocumentMessage(Update update) {
        updateProducer.produce(DOC_MESSAGE_UPDATE, update);
        var message = messageUtils.generateMessageToSend(update, "Document received");
        telegramBot.sendResponseMessage(message);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(TEXT_MESSAGE_UPDATE, update);
        var message = messageUtils.generateMessageToSend(update, "Text received");
        telegramBot.sendResponseMessage(message);
    }
}
