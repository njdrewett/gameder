package com.gameder.service.message;

import com.gameder.repository.GamerRepositoryCustom;
import com.gameder.repository.MessageRepository;
import com.gameder.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MessageServiceBase implements MessageService {

    private final MessageRepository messageRepository;
    private final GamerRepositoryCustom gamerRepositoryCustom;

    @Autowired
    MessageServiceBase(final MessageRepository messageRepository, final GamerRepositoryCustom gamerRepositoryCustom) {
        this.messageRepository = messageRepository;
        this.gamerRepositoryCustom = gamerRepositoryCustom;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public GamerRepositoryCustom getGamerRepositoryCustom() {
        return gamerRepositoryCustom;
    }
}
