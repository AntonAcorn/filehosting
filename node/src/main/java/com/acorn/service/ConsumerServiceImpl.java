package com.acorn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.acorn.RabbitQueueDestination.DOC_MESSAGE_UPDATE;
import static com.acorn.RabbitQueueDestination.PHOTO_MESSAGE_UPDATE;
import static com.acorn.RabbitQueueDestination.TEXT_MESSAGE_UPDATE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessage() {
        log.debug("NODE: Text message is received");
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumeDocMessage() {
        log.debug("NODE: Document is received");
    }

    @Override
    @RabbitListener(queues = PHOTO_MESSAGE_UPDATE)
    public void consumePhotoMessage() {
        log.debug("NODE: Photo is received");
    }
}
