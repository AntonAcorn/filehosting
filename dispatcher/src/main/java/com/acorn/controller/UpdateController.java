package com.acorn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
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
        }

    }
 }
