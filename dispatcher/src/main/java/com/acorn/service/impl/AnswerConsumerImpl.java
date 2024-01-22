package com.acorn.service.impl;

import com.acorn.controller.UpdateController;
import com.acorn.service.AnswerConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.acorn.RabbitQueueDestination.ANSWER_MESSAGE_UPDATE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerConsumerImpl implements AnswerConsumer {

    private final UpdateController updateController;

    @Override
    @RabbitListener(queues = ANSWER_MESSAGE_UPDATE)
    public void consumer(SendMessage message) {
        log.debug("[DISPATCHER] - messaged consumed from [NODE]" + message.getText());
        updateController.sendMessage(message);
    }
}
