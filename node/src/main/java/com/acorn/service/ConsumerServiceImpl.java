package com.acorn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.acorn.RabbitQueueDestination.DOC_MESSAGE_UPDATE;
import static com.acorn.RabbitQueueDestination.PHOTO_MESSAGE_UPDATE;
import static com.acorn.RabbitQueueDestination.TEXT_MESSAGE_UPDATE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    private final ProducerService producerService;

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessage(Update update) {
        log.debug("[NODE] Text message is received from [DISPATCHER]");
        var sendMessage = new SendMessage(update.getMessage().getChatId().toString(), "Hello from NODE");
        producerService.produceAnswer(sendMessage);
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumeDocMessage(Update update) {
        log.debug("NODE: Document is received");
    }

    @Override
    @RabbitListener(queues = PHOTO_MESSAGE_UPDATE)
    public void consumePhotoMessage(Update update) {
        log.debug("NODE: Photo is received");
    }
}
