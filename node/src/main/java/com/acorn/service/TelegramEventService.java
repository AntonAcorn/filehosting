package com.acorn.service;

import com.acorn.model.TelegramEvent;
import com.acorn.repository.TelegramEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TelegramEventService {

    private final TelegramEventRepository telegramEventRepository;

    @Transactional
    public TelegramEvent save(TelegramEvent telegramEvent) {
        return telegramEventRepository.save(telegramEvent);
    }
}
