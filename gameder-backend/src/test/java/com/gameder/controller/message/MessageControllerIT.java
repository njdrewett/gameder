package com.gameder.controller.message;

import com.gameder.api.message.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Message unit Test
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MessageControllerIT {

    private final Logger log = LoggerFactory.getLogger(MessageControllerIT.class);

    @Autowired
    private MessageController messageController;

    @Test
    public void testCreateMessage() {
        log.info("testCreateMessage");

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage();
//        assertEquals(body.getId(), message.getId());

        log.info("testCreateMessageResponse {}", returnedMessage );
    }

    private ResponseEntity<CreateMessageResponse> createMessage() {
        final CreateMessageRequest createMessageRequest = new CreateMessageRequest("NewMessage", new Date(1656366879731L));
        final ResponseEntity<CreateMessageResponse> returnedMessage = messageController.createMessage(createMessageRequest);

        final CreateMessageResponse body = returnedMessage.getBody();
        assertTrue(body.getSuccess());
        return returnedMessage;
    }

    @Test
    public void testUpdateMessage() {
        log.info("testUpdateMessage");

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage();
        final UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest(returnedMessage.getBody().getId(), "NewMessage", new Date(1656366879731L));
        final ResponseEntity<UpdateMessageResponse> returnedUpdateMessage = messageController.updateMessage(updateMessageRequest);

        final UpdateMessageResponse body = returnedUpdateMessage.getBody();
        assertTrue(body.getSuccess());
//        assertEquals(body.getId(), Message.getId());

        log.info("testUpdateMessage {}", returnedMessage);
    }

    @Test
    public void testUpdateMessageNotExists() {
        log.info("testUpdateMessageNotExists");

        final UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest("2", "NewMessage", new Date(1656366879731L));
        try {
            messageController.updateMessage(updateMessageRequest);
            fail("EntityNotFoundException should have been thrown ");
        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testUpdateMessageNotExists");
    }

    @Test
    public void testRetrieveMessage() {
        log.info("testRetrieveMessage");

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage();

        final ResponseEntity<RetrieveMessageResponse> getMessageResponse = messageController.retrieveMessage(returnedMessage.getBody().getId());

        assertNotNull(getMessageResponse.getBody().getId());

        log.info("testRetrieveMessage {}", returnedMessage);
    }

    @Test
    public void testRetrieveMessageNotFound() {
        log.info("testRetrieveMessage");

        try {
            messageController.retrieveMessage("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testRetrieveMessage ");
    }

    @Test
    public void testArchiveMessage() {
        log.info("testArchiveMessage");

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage();

        messageController.archiveMessage(returnedMessage.getBody().getId());

        log.info("testArchiveMessage");
    }

    @Test
    public void testArchiveMessageNotFound() {
        log.info("testArchiveMessage");

        try {
            messageController.archiveMessage("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveMessageNotFound");
    }
}
