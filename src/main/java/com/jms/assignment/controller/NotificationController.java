package com.jms.assignment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jms.assignment.Notification;
import com.jms.assignment.NotificationMessages;
import com.jms.assignment.publisher.PublisherNotification;

import java.util.List;

@RestController
@Slf4j
public class NotificationController {

    private final PublisherNotification emailMessagePublisher;
    private final NotificationMessages emailMessages;

    @Autowired
    public NotificationController(PublisherNotification emailMessagePublisher, NotificationMessages emailMessages) {
        this.emailMessagePublisher = emailMessagePublisher;
        this.emailMessages = emailMessages;
    }


    @PostMapping("/")
    public String sendEmail(@RequestBody Notification email) {
        log.info("Received Email Request from Controller {}", email);
        boolean isMessageSent = emailMessagePublisher.sendEmail(email);
        return isMessageSent ? "SUCCESS" : "FAILED";
    }

    @GetMapping("/")
    @ResponseBody
    public String getAllMessage() {
        List<Notification> emails = emailMessages.getAllJmsMessages();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1'>");
        sb.append("<tr><th>To</th><th>Body</th></tr>");
        for (Notification email : emails) {
            sb.append("<tr>");
            sb.append("<td>").append(email.getTo()).append("</td>");
            sb.append("<td>").append(email.getBody()).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
