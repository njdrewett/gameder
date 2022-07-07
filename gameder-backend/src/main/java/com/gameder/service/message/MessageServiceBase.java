package com.gameder.service.message;

import com.gameder.repository.MessageRepository;
import com.gameder.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MessageServiceBase implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    MessageServiceBase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

}
