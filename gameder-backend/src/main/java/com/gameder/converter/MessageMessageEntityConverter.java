package com.gameder.converter;

import com.gameder.api.Message;
import com.gameder.domain.GamerEntity;
import com.gameder.domain.MessageEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MessageMessageEntityConverter {

    public static Message toMessage(final MessageEntity messageEntity) {
        return new Message(messageEntity.getId(), messageEntity.getMessageText(),messageEntity.getCreationDate(),
                messageEntity.getLastUpdatedDate(), messageEntity.getFromGamer().getId(), messageEntity.getToGamer().getId());
    }

    public static List<Message> toMessage(final List<MessageEntity> messageEntities) {
        return messageEntities.stream().map(MessageMessageEntityConverter::toMessage).collect(Collectors.toList());

    }

    public static MessageEntity toMessageEntity(final Message message, final GamerEntity fromGamerEntity, final GamerEntity toGamerEntity) {
        return toMessageEntity(message, new MessageEntity(), fromGamerEntity,toGamerEntity);
    }

    public static MessageEntity toMessageEntity(final Message message, final MessageEntity messageEntity, final GamerEntity fromGamerEntity, final GamerEntity toGamerEntity) {
        messageEntity.setId(message.getId());
        messageEntity.setMessageText(message.getMessageText());
        messageEntity.setCreationDate(message.getCreationDate());
        messageEntity.setLastUpdatedDate(message.getLastUpdatedDate());
        messageEntity.setFromGamer(fromGamerEntity);
        messageEntity.setToGamer(toGamerEntity);
        return messageEntity;
    }

}
