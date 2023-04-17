package com.jms.assignment.consumer;

import lombok.extern.slf4j.Slf4j;

import static com.jms.assignment.constants.Constants.NOTIFICATION_QUEUE_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.jms.assignment.Notification;
import com.jms.assignment.NotificationMessages;

@Component
@Slf4j
public class NotificationListener {

    private final NotificationMessages emailMessages;

    @Autowired
    public NotificationListener(NotificationMessages emailMessages){
        this.emailMessages = emailMessages;
    }

    @JmsListener(destination = NOTIFICATION_QUEUE_NAME)
    public void receiveEmail(Notification email) {
        log.info("Email received={}", email);
        emailMessages.push(email);
    }
}
