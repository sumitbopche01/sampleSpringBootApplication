package com.jms.assignment;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMessages {

    private final List<Notification> jmsMessages;

    public NotificationMessages() {
        this.jmsMessages = new ArrayList<>();
    }

    public void push(Notification email) {
        jmsMessages.add(email);
    }

    public List<Notification> getAllJmsMessages() {
        return jmsMessages;
    }
}
