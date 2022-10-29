package com.gameder.controller.message;

import com.gameder.api.Message;
import com.gameder.api.gamer.CreateGamerResponse;
import com.gameder.api.message.*;
import com.gameder.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Message unit Test
 */
@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {

    private static final Logger log = LoggerFactory.getLogger(MessageControllerTest.class);

    MessageController messageController;

    @Mock
    private MessageService messageService;

    @BeforeEach
    public void init() {
        messageController = new MessageControllerImpl(messageService);
    }

    @Test
    public void testCreateMessage() {
        log.info("testCreateMessage");

        final Message message = new Message("1","NewMessage", new Date(1656366879731L),
                new Date(1656366879731L),"123", "321");
        Mockito.when(messageService.createMessage(Mockito.any(Message.class))).thenReturn(message);

        final CreateMessageRequest createMessageRequest = new CreateMessageRequest("NewMessage", "123", "321");
        final ResponseEntity<CreateMessageResponse> returnedMessage = messageController.createMessage(createMessageRequest);

        final CreateMessageResponse body = returnedMessage.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), message.getId());

        log.info("testCreateMessageResponse {}", returnedMessage );
    }

    @Test
    public void testUpdateMessage() {
        log.info("testUpdateMessage");

        final Message message = new Message("1","NewMessage",
                new Date(1656366879731L),new Date(1656366879731L),"123", "321");
        Mockito.when(messageService.updateMessage(Mockito.any(Message.class))).thenReturn(message);

        final UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest("1", "NewMessage", "123","321");
        final ResponseEntity<UpdateMessageResponse> returnedMessage = messageController.updateMessage(updateMessageRequest);

        final UpdateMessageResponse body = returnedMessage.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), message.getId());

        log.info("testUpdateMessage {}", returnedMessage);
    }

    @Test
    public void testUpdateMessageNotExists() {
        log.info("testUpdateMessageNotExists");

        final Message message = new Message("1","NewMessage", new Date(1656366879731L),new Date(1656366879731L),
                "123", "321");
        Mockito.when(messageService.updateMessage(Mockito.any(Message.class))).thenReturn(message);

        final UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest("2", "NewMessage",
                "123", "321");
        final ResponseEntity<UpdateMessageResponse> returnedMessage = messageController.updateMessage(updateMessageRequest);

        final UpdateMessageResponse body = returnedMessage.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), message.getId());

        log.info("testUpdateMessageNotExists");
    }

    @Test
    public void testRetrieveMessage() {
        log.info("testRetrieveMessage");

        final Message Message = new Message("1","NewMessage", new Date(1656366879731L),new Date(1656366879731L),
                "123", "321");

        Mockito.when(messageService.retrieveMessage("1")).thenReturn(Message);

        final ResponseEntity<RetrieveMessageResponse> returnedMessage = messageController.retrieveMessage("1");

        assertNotNull(returnedMessage.getBody().getId());

        log.info("testRetrieveMessage {}", returnedMessage);
    }

    @Test
    public void testRetrieveMessageNotFound() {
        log.info("testRetrieveMessage");

        Mockito.when(messageService.retrieveMessage("1")).thenThrow(new EntityNotFoundException());

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

        Mockito.doNothing().when(messageService).archiveMessage("1");

        messageService.archiveMessage("1");

        log.info("testArchiveMessage");
    }

    @Test
    public void testArchiveMessageNotFound() {
        log.info("testArchiveMessage");

        Mockito.doThrow(new EntityNotFoundException()).when(messageService).archiveMessage("1");

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

        final Message message = new Message("1","NewMessage", new Date(1656366879731L),
                new Date(1656366879731L),"123", "321");
        final List<Message> messages = Collections.singletonList(message);
        Mockito.when(messageService.findMessagesForGamer("123")).thenReturn(messages);

        final ResponseEntity<List<RetrieveMessageResponse>> returnedMessages = messageController.findAllMessagesForGamer("123");

        assertNotNull(returnedMessages);
        assertNotNull(returnedMessages.getBody());
        assertEquals(1, returnedMessages.getBody().size());

        log.info("testFindMessagesForGamer {}", returnedMessages);
    }

}
