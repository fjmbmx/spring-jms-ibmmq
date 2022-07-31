package com.sample.jms.ibmmq.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.function.Consumer;

@Named
@Slf4j
public class MQGateway {

    private JmsTemplate jmsTemplate;
    private MQProperties mqProperties;
    private Consumer<String> messageConsumer;

    @Autowired
    public MQGateway(JmsTemplate jmsTemplate, MQProperties mqProperties, @Named("messageConsumer") Consumer<String> messageConsumer) {
        this.jmsTemplate = jmsTemplate;
        this.mqProperties = mqProperties;
        this.messageConsumer = messageConsumer;
    }

    @JmsListener (destination = "${ibm.mq.incoming-queue}")
    public void onMessage(TextMessage message) throws JMSException {
         log.debug("Listenner - Message: {}", message);

        try {
            messageConsumer.accept(message.getText());
        } catch (JMSException e) {
            log.error("No se pudo consumir el mensaje: {}", message, e);
        }

    }

    public void send(String message) {
        log.info("Enviando message a  IBM Mensaje:  {}", message );
         jmsTemplate.convertAndSend(mqProperties.getIncomingQueue(), message);
    }


}

