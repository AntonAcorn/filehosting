package com.acorn.service.rabbitMQ.impl;

import com.acorn.service.MainService;
import com.acorn.service.rabbitMQ.ConsumerService;
import com.acorn.service.rabbitMQ.ProducerService;
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
    private final MainService mainService;

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessage(Update update) {
        var message = mainService.processTextMessage(update);
        producerService.produceAnswer(message);
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
