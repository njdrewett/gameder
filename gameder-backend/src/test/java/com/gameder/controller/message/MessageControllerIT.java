package com.gameder.controller.message;

import com.gameder.api.gamer.CreateGamerRequest;
import com.gameder.api.gamer.CreateGamerResponse;
import com.gameder.api.message.*;
import com.gameder.controller.gamer.GamerController;
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

    @Autowired
    private GamerController gamerController;

    @Test
    public void testCreateMessage() {
        log.info("testCreateMessage");

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage();

        assertNotNull(returnedMessage.getBody().getId());
        assertTrue(returnedMessage.getBody().getSuccess());

        log.info("testCreateMessageResponse {}", returnedMessage );
    }

    private ResponseEntity<CreateMessageResponse> createMessage() {

        // we need to create to users to link the messages to
        final CreateGamerRequest createGamerRequestFrom = new CreateGamerRequest("NewGamerA", new Date(1656366879731L)
                ,"gamer@gamers.com", "019191999991911", null, "Hi Im a gamerA");
        final ResponseEntity<CreateGamerResponse> returnedGamerFrom = gamerController.createGamer(createGamerRequestFrom);
        final CreateGamerRequest createGamerRequestTo = new CreateGamerRequest("NewGamerB", new Date(1656366879731L)
                ,"gamer@gamers.com", "019191999991911", null, "Hi Im a gamerA");
        final ResponseEntity<CreateGamerResponse> returnedGamerTo = gamerController.createGamer(createGamerRequestTo);


        final CreateMessageRequest createMessageRequest = new CreateMessageRequest("NewMessage",
                returnedGamerFrom.getBody().getId(), returnedGamerTo.getBody().getId() );
        final ResponseEntity<CreateMessageResponse> returnedMessage = messageController.createMessage(createMessageRequest);

        final CreateMessageResponse body = returnedMessage.getBody();
        assertTrue(body.getSuccess());

        return returnedMessage;
    }

    @Test
    public void testUpdateMessage() {
        log.info("testUpdateMessage");

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage();

        // Get message to retrieve UserId
        final ResponseEntity<RetrieveMessageResponse> retrieveMessageResponseResponseEntity =
                messageController.retrieveMessage(returnedMessage.getBody().getId());

        final UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest(returnedMessage.getBody().getId(), "NewMessage",
                retrieveMessageResponseResponseEntity.getBody().getFromUserId(), retrieveMessageResponseResponseEntity.getBody().getFromUserId());

        final ResponseEntity<UpdateMessageResponse> returnedUpdateMessage = messageController.updateMessage(updateMessageRequest);

        final UpdateMessageResponse body = returnedUpdateMessage.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), body.getId());

        log.info("testUpdateMessage {}", returnedMessage);
    }

    @Test
    public void testUpdateMessageNotExists() {
        log.info("testUpdateMessageNotExists");

        final UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest("2", "NewMessage", "213", "321");
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
