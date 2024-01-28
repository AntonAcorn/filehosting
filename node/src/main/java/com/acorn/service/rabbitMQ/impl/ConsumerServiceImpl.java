package com.acorn.service.rabbitMQ.impl;

import com.acorn.RabbitQueueDestination;
import com.acorn.service.MainService;
import com.acorn.service.rabbitMQ.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    private final MainService mainService;

    @Override
    @RabbitListener(queues = RabbitQueueDestination.TEXT_MESSAGE_UPDATE)
    public void consumeTextMessage(Update update) {
        log.debug("NODE: Text is received");
        mainService.processTextMessage(update);
    }

    @Override
    @RabbitListener(queues = RabbitQueueDestination.DOC_MESSAGE_UPDATE)
    public void consumeDocMessage(Update update) {
        log.debug("NODE: Document is received");
        mainService.processDocMessage(update);
    }

    @Override
    @RabbitListener(queues = RabbitQueueDestination.PHOTO_MESSAGE_UPDATE)
    public void consumePhotoMessage(Update update) {
        log.debug("NODE: Photo is received");
        mainService.processPhotoMessage(update);
    }
}
