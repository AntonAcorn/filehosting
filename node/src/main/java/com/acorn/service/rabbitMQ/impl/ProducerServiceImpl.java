package com.acorn.service.rabbitMQ.impl;

import com.acorn.RabbitQueueDestination;
import com.acorn.service.rabbitMQ.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void produceAnswer(SendMessage sendMessage) {
        rabbitTemplate.convertAndSend(RabbitQueueDestination.ANSWER_MESSAGE_UPDATE, sendMessage);
    }
}
