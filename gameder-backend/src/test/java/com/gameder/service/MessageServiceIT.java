package com.gameder.service;


import com.gameder.api.Gamer;
import com.gameder.api.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class MessageServiceIT {

    private final Logger log = LoggerFactory.getLogger(MessageServiceIT.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private GamerService gamerService;

    @Test
    public void testCreateMessage() {
        log.info("testCreateMessage");

        final Message returnedMessage = createMessage();
        assertNotNull(returnedMessage.getId());

        log.info("testCreateMessageResponse {}", returnedMessage );
    }

    private Message createMessage() {

        final Gamer gamerFrom = crateGamer("NewGamerFrom");

        final Gamer gamerTo = crateGamer("NewGamerTo");

        final Message message = new Message(null,"NewMessageUpdated", null, null,gamerFrom.getId(), gamerTo.getId());

        final Message returnedMessage = messageService.createMessage(message);

        assertNotNull(returnedMessage.getId());
        assertNotNull(returnedMessage.getCreationDate());
        assertNotNull(returnedMessage.getLastUpdatedDate());
        assertEquals(returnedMessage.getMessageText(), message.getMessageText());
        assertEquals(returnedMessage.getFromGamerId(), message.getFromGamerId());
        assertEquals(returnedMessage.getToGamerId(), message.getToGamerId());

        return returnedMessage;
    }

    private Gamer crateGamer(final String displayName) {
        final Gamer gamerFrom = new Gamer(null,displayName, new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "jpg","Hi Im a gamer","password");
        return  gamerService.createGamer(gamerFrom);
    }

    @Test
    public void testUpdateMessage() {
        log.info("testUpdateMessage");

        final Message createdMessage = createMessage();

        // Get message to retrieve UserId
        final Message retrieveMessageResponseResponseEntity =
                messageService.retrieveMessage(createdMessage.getId());

        final Message message = new Message(retrieveMessageResponseResponseEntity.getId(),"NewMessageUpdated", new Date(1656366879731L), new Date(1656366879731L),
                retrieveMessageResponseResponseEntity.getFromGamerId(), retrieveMessageResponseResponseEntity.getToGamerId());

        final Message returnedMessage = messageService.updateMessage(message);

        assertNotNull(returnedMessage.getId());
        assertEquals(returnedMessage.getId(), returnedMessage.getId());
        assertNotNull(returnedMessage.getCreationDate());
        assertEquals(returnedMessage.getMessageText(), returnedMessage.getMessageText());
        assertNotNull(returnedMessage.getLastUpdatedDate());
        assertNotEquals(returnedMessage.getLastUpdatedDate(),createdMessage.getLastUpdatedDate() );
        log.info("testUpdateMessage {}", returnedMessage);
    }

    @Test
    public void testUpdateMessageNotExists() {
        log.info("testUpdateMessage");

        final Message message = new Message("1","NewMessage", null, null,"123","321");

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
        assertNotNull(returnedMessage.getId());
        assertEquals(returnedMessage.getId(), returnedMessage.getId());
        assertNotNull(returnedMessage.getCreationDate());
        assertEquals(returnedMessage.getMessageText(), returnedMessage.getMessageText());
        assertNotNull(returnedMessage.getLastUpdatedDate());

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

    @Test
    public void testFindMessagesForGamer() {
        log.info("testFindMessagesForGamer");
        final Gamer gamerFrom = crateGamer("NewGamerFrom");

        final Gamer gamerTo = crateGamer("NewGamerTo");

        final Message message = new Message(null,"NewMessageUpdated", null, null,gamerFrom.getId(), gamerTo.getId());

       messageService.createMessage(message);

        List<Message> returnedMessages = messageService.findMessagesForGamer(gamerFrom.getId());

        assertNotNull(returnedMessages);
        assertEquals(1, returnedMessages.size());
        Message returnedMessage = returnedMessages.get(0);
        assertNotNull(returnedMessage.getId());
        assertNotNull(returnedMessage.getCreationDate());
        assertNotNull(returnedMessage.getLastUpdatedDate());
        assertEquals(returnedMessage.getMessageText(), message.getMessageText());
        assertEquals(returnedMessage.getFromGamerId(), message.getFromGamerId());
        assertEquals(returnedMessage.getToGamerId(), message.getToGamerId());

        returnedMessages = messageService.findMessagesForGamer(gamerTo.getId());

        assertNotNull(returnedMessages);
        assertEquals(1, returnedMessages.size());
        returnedMessage = returnedMessages.get(0);
        assertNotNull(returnedMessage.getId());
        assertNotNull(returnedMessage.getCreationDate());
        assertNotNull(returnedMessage.getLastUpdatedDate());
        assertEquals(returnedMessage.getMessageText(), message.getMessageText());
        assertEquals(returnedMessage.getFromGamerId(), message.getFromGamerId());
        assertEquals(returnedMessage.getToGamerId(), message.getToGamerId());

        log.info("Exiting testFindMessagesForGamer");
    }


}
