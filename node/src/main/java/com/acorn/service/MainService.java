package com.acorn.service;

import com.acorn.entity.AccountState;
import com.acorn.model.TelegramEvent;
import com.acorn.service.comands.Commands;
import com.acorn.service.comands.MessageConstants;
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
    private final FileService fileService;

    public void processTextMessage(Update update) {
        var telegramEvent = constructTelegramEvent(update);
        var chatId = telegramEvent.getUpdate().getMessage().getChatId().toString();
        var telegramEventWithId = updateTelegramService.save(telegramEvent);
        var account = accountService.findOrCreate(telegramEventWithId);
        var textFromTelegram = telegramEvent.getUpdate().getMessage().getText();
        if (AccountState.BASIC_STATE.equals(account.getAccountState())) {
            processCommands(textFromTelegram, chatId);
        } else if (AccountState.WAITING_FOR_EMAIL_STATE.equals(account.getAccountState())) {
            log.debug("The feature is in progress");
        } else {
            log.debug("Unknown account state");
        }
    }

    public void processDocMessage(Update update) {
        var telegramEvent = constructTelegramEvent(update);
        var chatId = telegramEvent.getUpdate().getMessage().getChatId().toString();
        updateTelegramService.save(telegramEvent);
        var account = accountService.findOrCreate(telegramEvent);
        var textFromTelegram = telegramEvent.getUpdate().getMessage().getText();
        fileService.processDoc(telegramEvent);
        sendMessage("This is a document stub", chatId);
    }

    public void processPhotoMessage(Update update) {
        var telegramEvent = constructTelegramEvent(update);
        var chatId = telegramEvent.getUpdate().getMessage().getChatId().toString();
        updateTelegramService.save(telegramEvent);
        var account = accountService.findOrCreate(telegramEvent);
        var textFromTelegram = telegramEvent.getUpdate().getMessage().getText();
        sendMessage("This is a photo stub", chatId);
    }

    private void processCommands(String textFromTelegram, String chatId) {
        if (textFromTelegram.equals(Commands.REGISTRATION.toString())) {
            sendMessage(MessageConstants.FEATURE_IN_PROGRESS_COMMAND_ANSWER, chatId);
        } else if (textFromTelegram.equals(Commands.HELP.toString())) {
            sendMessage(MessageConstants.HELP_COMMAND_ANSWER, chatId);
        } else if (textFromTelegram.equals(Commands.START.toString())) {
            sendMessage(MessageConstants.START_COMMAND_ANSWER, chatId);
        } else {
            sendMessage(MessageConstants.UNKNOWN_COMMAND_ANSWER, chatId);
        }
    }
    private static TelegramEvent constructTelegramEvent(Update update) {
        return TelegramEvent.builder()
                .update(update)
                .build();
    }



    private void sendMessage(String message, String chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        producerService.produceAnswer(sendMessage);
    }
}
