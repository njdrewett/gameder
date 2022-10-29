package com.gameder.controller.message;

import com.gameder.api.Message;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Message unit Test
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

        final ResponseEntity<CreateGamerResponse> returnedGamerFrom = createGamer("NewGamerA", gamerController);
        final ResponseEntity<CreateGamerResponse> returnedGamerTo = createGamer("NewGamerB", gamerController);

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage(returnedGamerFrom, returnedGamerTo);

        final CreateMessageResponse body = returnedMessage.getBody();
        assertTrue(body.getSuccess());

        return returnedMessage;
    }

    private ResponseEntity<CreateMessageResponse> createMessage(ResponseEntity<CreateGamerResponse> returnedGamerFrom, ResponseEntity<CreateGamerResponse> returnedGamerTo) {
        final CreateMessageRequest createMessageRequest = new CreateMessageRequest("NewMessage",
                returnedGamerFrom.getBody().getId(), returnedGamerTo.getBody().getId() );
        final ResponseEntity<CreateMessageResponse> returnedMessage = messageController.createMessage(createMessageRequest);
        return returnedMessage;
    }

    private ResponseEntity<CreateGamerResponse> createGamer(String NewGamerA, GamerController gamerController) {
        // we need to create to users to link the messages to
        final CreateGamerRequest createGamerRequestFrom = new CreateGamerRequest(NewGamerA, new Date(1656366879731L)
                ,"gamer@gamers.com", "019191999991911", null, "Hi Im a gamerA", "password");
        final ResponseEntity<CreateGamerResponse> returnedGamerFrom = gamerController.createGamer(createGamerRequestFrom);
        return returnedGamerFrom;
    }

    @Test
    public void testUpdateMessage() {
        log.info("testUpdateMessage");

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage();

        // Get message to retrieve UserId
        final ResponseEntity<RetrieveMessageResponse> retrieveMessageResponseResponseEntity =
                messageController.retrieveMessage(returnedMessage.getBody().getId());

        final UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest(returnedMessage.getBody().getId(), "NewMessage",
                retrieveMessageResponseResponseEntity.getBody().getFromGamerId(), retrieveMessageResponseResponseEntity.getBody().getFromGamerId());

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


    @Test
    public void testFindMessagesForGamer() {
        log.info("testFindMessagesForGamer");

        final ResponseEntity<CreateGamerResponse> returnedGamerFrom = createGamer("NewGamerA", gamerController);
        final ResponseEntity<CreateGamerResponse> returnedGamerTo = createGamer("NewGamerB", gamerController);

        final ResponseEntity<CreateMessageResponse> returnedMessage = createMessage(returnedGamerFrom, returnedGamerTo);

        ResponseEntity<List<RetrieveMessageResponse>> foundMessages = messageController.findAllMessagesForGamer(returnedGamerFrom.getBody().getId());

        assertNotNull(foundMessages);
        assertNotNull(foundMessages.getBody());
        assertTrue(foundMessages.getBody().size() == 1);

        ResponseEntity<List<RetrieveMessageResponse>> foundMessagesTo = messageController.findAllMessagesForGamer(returnedGamerTo.getBody().getId());

        assertNotNull(foundMessagesTo);
        assertNotNull(foundMessagesTo.getBody());
        assertTrue(foundMessagesTo.getBody().size() == 1);

        assertEquals(foundMessages.getBody().get(0).getId() , foundMessagesTo.getBody().get(0).getId());

        log.info("testFindMessagesForGamer {}", returnedMessage);
    }

}
