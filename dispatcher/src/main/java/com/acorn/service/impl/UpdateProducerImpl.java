package com.acorn.service.impl;

import com.acorn.service.UpdateProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateProducerImpl implements UpdateProducer {

    private final RabbitTemplate rabbitTemplate;


    /***
     *  Не забыть добавить в конфигурационный класс jsonMessageConverter,
     *  convertAndSend под капотом использует @Bean jsonMessageConverter,
     *  описанный в конфигарацонным файле
     */
    @Override
    public void produce(String rabbitQueue, Update update) {
        log.debug("[DISPATCHER PRODUCER] " + update.getMessage().getText());
        rabbitTemplate.convertAndSend(rabbitQueue, update);
    }
}
