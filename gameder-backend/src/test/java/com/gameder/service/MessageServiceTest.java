package com.gameder.service;

import com.gameder.api.Message;
import com.gameder.domain.MessageEntity;
import com.gameder.repository.MessageRepository;
import com.gameder.service.message.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Message unit Test
 */
@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

   private final Logger log = LoggerFactory.getLogger(MessageServiceTest.class);

   private MessageService messageService;

   @Mock
   private MessageRepository messageRepository;

   @BeforeEach
   void setup() {
      log.info("setup");
      messageService = new MessageServiceImpl(messageRepository);
   }

   @Test
   public void testCreateMessage() {
      log.info("testCreateMessage");

      final Message Message = new Message(null,"NewMessage", new Date(1656366879731L),
              new Date(1656366879731L), "123","321");

      final MessageEntity persistedMessageEntity = persistedMessageEntity(Message);

      Mockito.when(messageRepository.save(Mockito.any(MessageEntity.class))).thenReturn(persistedMessageEntity);

      final Message returnedMessage = messageService.createMessage(Message);

      assertNotNull(returnedMessage.getId());
      assertEquals(returnedMessage.getId(), persistedMessageEntity.getId());
      assertEquals(returnedMessage.getCreationDate(), persistedMessageEntity.getCreationDate());
      assertEquals(returnedMessage.getLastUpdatedDate(), persistedMessageEntity.getLastUpdatedDate());
      assertEquals(returnedMessage.getMessageText(), persistedMessageEntity.getMessageText());
      assertEquals(returnedMessage.getFromUserId(), persistedMessageEntity.getFromUserId());
      assertEquals(returnedMessage.getToUserId(), persistedMessageEntity.getToUserId());

      log.info("testCreateMessageResponse {}", returnedMessage );
   }

   @Test
   public void testUpdateMessage() {
      log.info("testUpdateMessage");

      final Message Message = new Message("1","NewMessage", new Date(1656366879731L),
              new Date(1656366879731L), "123","321");

      final MessageEntity persistedMessageEntity = persistedMessageEntity(Message);

      Mockito.when(messageRepository.findById(Message.getId())).thenReturn(Optional.of(persistedMessageEntity));
      Mockito.when(messageRepository.save(Mockito.any(MessageEntity.class))).thenReturn(persistedMessageEntity);

      final Message returnedMessage = messageService.updateMessage(Message);

      assertNotNull(returnedMessage.getId());
      assertEquals(returnedMessage.getId(), persistedMessageEntity.getId());
      assertEquals(returnedMessage.getCreationDate(), persistedMessageEntity.getCreationDate());
      // shouldn't equal as update
      assertNotEquals(returnedMessage.getLastUpdatedDate(), persistedMessageEntity.getLastUpdatedDate());
      assertEquals(returnedMessage.getMessageText(), persistedMessageEntity.getMessageText());
      assertEquals(returnedMessage.getFromUserId(), persistedMessageEntity.getFromUserId());
      assertEquals(returnedMessage.getToUserId(), persistedMessageEntity.getToUserId());

      log.info("testUpdateMessage {}", returnedMessage);
   }

   @Test
   public void testUpdateMessageNotExists() {
      log.info("testUpdateMessage");

      final Message message = new Message("1","NewMessage", new Date(1656366879731L),
              new Date(1656366879731L), "123","321");

      Mockito.when(messageRepository.findById(message.getId())).thenReturn(Optional.empty());

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

      final Message message = new Message("1","NewMessage", new Date(1656366879731L),
              new Date(1656366879731L), "123","321");

      final MessageEntity persistedMessageEntity = persistedMessageEntity(message);

      Mockito.when(messageRepository.findById("1")).thenReturn(Optional.of(persistedMessageEntity));

      final Message returnedMessage = messageService.retrieveMessage("1");

      assertNotNull(returnedMessage.getId());
      assertEquals(returnedMessage.getId(), persistedMessageEntity.getId());
      assertEquals(returnedMessage.getCreationDate(), persistedMessageEntity.getCreationDate());
      assertEquals(returnedMessage.getLastUpdatedDate(), persistedMessageEntity.getLastUpdatedDate());
      assertEquals(returnedMessage.getMessageText(), persistedMessageEntity.getMessageText());
      assertEquals(returnedMessage.getFromUserId(), persistedMessageEntity.getFromUserId());
      assertEquals(returnedMessage.getToUserId(), persistedMessageEntity.getToUserId());

      log.info("testRetrieveMessage {}", returnedMessage);
   }

   @Test
   public void testRetrieveMessageNotFound() {
      log.info("testRetrieveMessage");

      Mockito.when(messageRepository.findById("1")).thenReturn(Optional.empty());

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

      final Message message = new Message("1","NewMessage", new Date(1656366879731L),
              new Date(1656366879731L), "123","321");
      final MessageEntity persistedMessageEntity = persistedMessageEntity(message);

      Mockito.when(messageRepository.findById("1")).thenReturn(Optional.of(persistedMessageEntity));

      messageService.archiveMessage("1");

      log.info("testArchiveMessage");
   }

   @Test
   public void testArchiveMessageNotFound() {
      log.info("testArchiveMessage");

      Mockito.when(messageRepository.findById("1")).thenReturn(Optional.empty());

      try {
         messageService.archiveMessage("1");

         fail("EntityNotFoundException NOT thrown as expected");

      } catch (EntityNotFoundException exception) {
         log.info("EntityNotFoundException thrown as expected" );
      }

      log.info("testArchiveMessageNotFound");
   }


   private MessageEntity persistedMessageEntity(final Message message) {
      final MessageEntity messageEntity = new MessageEntity();
      messageEntity.setId("1");
      messageEntity.setCreationDate(message.getCreationDate());
      messageEntity.setLastUpdatedDate(message.getLastUpdatedDate());
      messageEntity.setMessageText(message.getMessageText());
      messageEntity.setFromUserId(message.getFromUserId());
      messageEntity.setToUserId(message.getToUserId());
      return messageEntity;
   }
}
