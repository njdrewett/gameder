package com.gameder.service;


import com.gameder.api.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MessageServiceIT {

    private final Logger log = LoggerFactory.getLogger(MessageServiceIT.class);

    @Autowired
    private MessageService messageService;

    @Test
    public void testCreateMessage() {
        log.info("testCreateMessage");

        final Message returnedMessage = createMessage();

        log.info("testCreateMessageResponse {}", returnedMessage );
    }

    private Message createMessage() {
        final Message Message = new Message(null,"NewMessage", new Date(1656366879731L));

        final Message returnedMessage = messageService.createMessage(Message);

        assertNotNull(returnedMessage.getId());
        assertEquals(returnedMessage.getId(), returnedMessage.getId());
        assertEquals(returnedMessage.getCreationDate(), returnedMessage.getCreationDate());
        assertEquals(returnedMessage.getMessageText(), returnedMessage.getMessageText());
        return returnedMessage;
    }

    @Test
    public void testUpdateMessage() {
        log.info("testUpdateMessage");

        final Message createdMessage = createMessage();

        final Message message = new Message(createdMessage.getId(),"NewMessageUpdated", new Date(1656366879731L));

        final Message returnedMessage = messageService.updateMessage(message);

        assertNotNull(returnedMessage.getId());
        assertEquals(returnedMessage.getId(), returnedMessage.getId());
        assertEquals(returnedMessage.getCreationDate(), returnedMessage.getCreationDate());
        assertEquals(returnedMessage.getMessageText(), returnedMessage.getMessageText());

        log.info("testUpdateMessage {}", returnedMessage);
    }

    @Test
    public void testUpdateMessageNotExists() {
        log.info("testUpdateMessage");

        final Message message = new Message("1","NewMessage", new Date(1656366879731L));

        try {
            messageService.updateMessage(message);

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testUpdateMessageNotExists");
    }

    @Test
    public void testRetrieveMessage() {
        log.info("testRetrieveMessage");

        final Message createdMessage = createMessage();

        final Message returnedMessage = messageService.retrieveMessage(createdMessage.getId());

        assertNotNull(returnedMessage.getId());

        log.info("testRetrieveMessage {}", returnedMessage);
    }

    @Test
    public void testRetrieveMessageNotFound() {
        log.info("testRetrieveMessage");

        try {
            messageService.retrieveMessage("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testRetrieveMessage ");
    }

    @Test
    public void testArchiveMessage() {
        log.info("testArchiveMessage");

        final Message createdMessage = createMessage();

        messageService.archiveMessage(createdMessage.getId());

        log.info("testArchiveMessage");
    }

    @Test
    public void testArchiveMessageNotFound() {
        log.info("testArchiveMessage");

        try {
            messageService.archiveMessage("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveMessageNotFound");
    }
}
