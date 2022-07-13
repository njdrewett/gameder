package com.gameder.converter;

import com.gameder.api.Message;
import com.gameder.domain.MessageEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Message To Entity test
 */
public class MessageMessageEntityConverterTest {

    private final Logger log = LoggerFactory.getLogger(MessageMessageEntityConverterTest.class);

    @Test
    public void toMessageTest() {
        MessageEntity messageEntity = createMessageEntity();
        log.info("toMessageTest {}", messageEntity);

        final Message message = MessageMessageEntityConverter.toMessage(messageEntity);

        assertNotNull(message.getId());
        assertEquals(message.getId(), messageEntity.getId());
        assertEquals(message.getMessageText(), messageEntity.getMessageText());
        assertEquals(message.getCreationDate(), messageEntity.getCreationDate());
        assertEquals(message.getLastUpdatedDate(), messageEntity.getLastUpdatedDate());
        assertEquals(message.getFromUserId(), messageEntity.getFromUserId());
        assertEquals(message.getToUserId(), messageEntity.getToUserId());

        log.info("toMessageTest {}", message);
    }

    @Test
    public void toMessageEntityTest() {
        Message message = createMessage();
        log.info("toMessageTest {}", message);

        final MessageEntity messageEntity = MessageMessageEntityConverter.toMessageEntity(message);

        assertNotNull(messageEntity.getId());
        assertEquals(messageEntity.getId(), message.getId());
        assertEquals(messageEntity.getMessageText(), message.getMessageText());
        assertEquals(messageEntity.getCreationDate(), message.getCreationDate());
        assertEquals(messageEntity.getLastUpdatedDate(), message.getLastUpdatedDate());
        assertEquals(messageEntity.getFromUserId(), message.getFromUserId());
        assertEquals(messageEntity.getToUserId(), message.getToUserId());

        log.info("toMessageTest {}", messageEntity);
    }

    private MessageEntity createMessageEntity() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId("1");
        messageEntity.setCreationDate(new Date(1234567890L));
        messageEntity.setLastUpdatedDate(new Date(1234567890L));
        messageEntity.setMessageText("Test Message");
        messageEntity.setFromUserId("123");
        messageEntity.setToUserId("321");
        return messageEntity;
    }

    private Message createMessage() {
        final Message message = new Message();
        message.setId("1");
        message.setCreationDate(new Date(1234567890L));
        message.setLastUpdatedDate(new Date(1234567890L));
        message.setMessageText("Test Message");
        message.setFromUserId("123");
        message.setToUserId("321");
        return message;
    }
}
