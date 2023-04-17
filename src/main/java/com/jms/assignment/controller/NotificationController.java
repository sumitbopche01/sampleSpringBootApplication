package com.jms.assignment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jms.assignment.Notification;
import com.jms.assignment.NotificationMessages;
import com.jms.assignment.publisher.PublisherNotification;

@RestController
@Slf4j
public class NotificationController {

    private final PublisherNotification emailMessagePublisher;

    @Autowired
    public NotificationController(PublisherNotification emailMessagePublisher, NotificationMessages emailMessages) {
        this.emailMessagePublisher = emailMessagePublisher;
    }

    @PostMapping("/")
    public String sendEmail(@RequestBody Notification email) {
        log.info("Received Notification Request from Controller {}", email);
        boolean isMessageSent = emailMessagePublisher.sendEmail(email);
        return isMessageSent ? "SUCCESS" : "FAILED";
    }

    @GetMapping("/")
    @ResponseBody
    public String getNotification() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1'>");
        sb.append("<tr>");
        sb.append("<td>").append("This Spring Application is working!").append("</td>");
        sb.append("</tr>");
        sb.append("</table>");
        return sb.toString();
    }
}
