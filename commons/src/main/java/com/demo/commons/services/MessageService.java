package com.demo.commons.services;


import com.demo.commons.messages.tools.MessageTools;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@SuppressWarnings("unused")
public class MessageService {
    @Autowired
    JmsTemplate jmsTemplate;

    @SneakyThrows
    public String publish(Object messagePayload, String topic){

        //Validate message
        MessageTools.verify(messagePayload);

        var jsonPayload = MessageTools.getValue(messagePayload);

        var message = new AtomicReference<Message>();
        jmsTemplate.send(topic, session -> {
            var byteMessage = session.createBytesMessage();
            byteMessage.writeBytes(jsonPayload.getBytes(StandardCharsets.UTF_8));
            message.set(byteMessage);
            return byteMessage;
        });
        return message.get().getJMSMessageID();
    }
}