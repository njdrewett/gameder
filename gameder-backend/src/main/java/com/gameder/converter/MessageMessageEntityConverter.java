package com.gameder.converter;

import com.gameder.api.Message;
import com.gameder.domain.MessageEntity;

public class MessageMessageEntityConverter {

    public static Message toMessage(final MessageEntity messageEntity) {
        return new Message(messageEntity.getId(), messageEntity.getMessageText(),messageEntity.getCreationDate(),
                messageEntity.getLastUpdatedDate(), messageEntity.getFromUserId(), messageEntity.getToUserId());
    }

    public static MessageEntity toMessageEntity(final Message message) {
        return toMessageEntity(message, new MessageEntity());
    }

    public static MessageEntity toMessageEntity(final Message message, final MessageEntity messageEntity) {
        messageEntity.setId(message.getId());
        messageEntity.setMessageText(message.getMessageText());
        messageEntity.setCreationDate(message.getCreationDate());
        messageEntity.setLastUpdatedDate(message.getLastUpdatedDate());
        messageEntity.setFromUserId(message.getFromUserId());
        messageEntity.setToUserId(message.getToUserId());
        return messageEntity;
    }

}
