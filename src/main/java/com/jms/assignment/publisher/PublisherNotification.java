package com.jms.assignment.publisher;

import static com.jms.assignment.constants.Constants.NOTIFICATION_QUEUE_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.jms.assignment.Notification;

@Component
public class PublisherNotification {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public PublisherNotification(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public boolean sendEmail(Notification email) {
        try{
            jmsTemplate.convertAndSend(NOTIFICATION_QUEUE_NAME, email);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
