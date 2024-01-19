package com.acorn.controller;

import com.acorn.config.BotConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final UpdateController updateController;

    /***
     *  В телеграмбот внедряется ссылка на updateController, после внедрения зависимости
     *  выполняется init метод, в котором мы передаем ссылку на сам телеграмБот внутрь
     *  updateController, для того, чтобы телеграм бот мог передать входящее сообщение в контроллер,
     *  а контроллер мог бы передать ответы обратно в телеграмбот
     */
    @PostConstruct
    public void init() {
        updateController.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var messageText = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();
            var sendMessage = new SendMessage(chatId.toString(), messageText);
            sendResponseMessage(sendMessage);
        }
    }

    private void sendResponseMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
        }
    }
}
