package com.acorn.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageUtils {

    public SendMessage generateMessageToSend (Update update, String message) {
        var chatId = update.getMessage().getChatId().toString();
        return new SendMessage(chatId, message);
    }
}
