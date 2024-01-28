package com.acorn.service;

import com.acorn.entity.AccountState;
import com.acorn.model.TelegramEvent;
import com.acorn.service.comands.Commands;
import com.acorn.service.rabbitMQ.ProducerService;
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
    private final ProducerService producerService;

    public void processTextMessage(Update update) {
        TelegramEvent telegramEvent = TelegramEvent.builder()
                .update(update)
                .build();
        var chatId = telegramEvent.getUpdate().getMessage().getChatId().toString();
        updateTelegramService.save(telegramEvent);
        var account = accountService.findOrCreate(telegramEvent);
        var textFromTelegram = telegramEvent.getUpdate().getMessage().getText();
        if (AccountState.BASIC_STATE.equals(account.getAccountState())) {
            processCommands(textFromTelegram, chatId);
        }
    }

    private void processCommands(String textFromTelegram, String chatId) {
        if (textFromTelegram.equals(Commands.REGISTRATION.toString())) {
            sendMessage("This feature is in progress", chatId);
        } else if (textFromTelegram.equals(Commands.HELP.toString())) {
            sendMessage("You can choose one of the following commands: /start, /help, /register", chatId);
        } else if (textFromTelegram.equals(Commands.START.toString())) {
            sendMessage("Hello in file hosting", chatId);
        } else {
            sendMessage("Unknown command, try: /help", chatId);
        }
    }

    private void sendMessage(String message, String chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        producerService.produceAnswer(sendMessage);
    }
}
