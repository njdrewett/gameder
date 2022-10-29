package com.gameder.service;

import com.gameder.api.Gamer;
import com.gameder.api.Message;
import com.gameder.domain.GamerEntity;
import com.gameder.domain.MessageEntity;
import com.gameder.repository.GamerRepositoryCustom;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;
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

   @Mock
   private GamerRepositoryCustom gamerRepositoryCustom;

   @BeforeEach
   void setup() {
      log.info("setup");
      messageService = new MessageServiceImpl(messageRepository,gamerRepositoryCustom);
   }

   @Test
   public void testCreateMessage() {
      log.info("testCreateMessage");

      final Message message = new Message(null,"NewMessage", new Date(1656366879731L),
              new Date(1656366879731L), "123","321");

      GamerEntity fromGamerEntity = new GamerEntity();
      fromGamerEntity.setId("123");
      Mockito.when(gamerRepositoryCustom.findByReference("123")).thenReturn(fromGamerEntity);

      GamerEntity toGamerEntity = new GamerEntity();
      toGamerEntity.setId("321");
      Mockito.when(gamerRepositoryCustom.findByReference("321")).thenReturn(toGamerEntity);

      final MessageEntity persistedMessageEntity = persistedMessageEntity(message);

      Mockito.when(messageRepository.save(Mockito.any(MessageEntity.class))).thenReturn(persistedMessageEntity);

      final Message returnedMessage = messageService.createMessage(message);

      assertNotNull(returnedMessage.getId());
      assertEquals(returnedMessage.getId(), persistedMessageEntity.getId());
      assertEquals(returnedMessage.getCreationDate(), persistedMessageEntity.getCreationDate());
      assertEquals(returnedMessage.getLastUpdatedDate(), persistedMessageEntity.getLastUpdatedDate());
      assertEquals(returnedMessage.getMessageText(), persistedMessageEntity.getMessageText());
      assertEquals(returnedMessage.getFromGamerId(), persistedMessageEntity.getFromGamer().getId());
      assertEquals(returnedMessage.getToGamerId(), persistedMessageEntity.getToGamer().getId());

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

      GamerEntity fromGamerEntity = new GamerEntity();
      fromGamerEntity.setId("123");
      Mockito.when(gamerRepositoryCustom.findByReference("123")).thenReturn(fromGamerEntity);

      GamerEntity toGamerEntity = new GamerEntity();
      toGamerEntity.setId("321");
      Mockito.when(gamerRepositoryCustom.findByReference("321")).thenReturn(toGamerEntity);

      final Message returnedMessage = messageService.updateMessage(Message);

      assertNotNull(returnedMessage.getId());
      assertEquals(returnedMessage.getId(), persistedMessageEntity.getId());
      assertEquals(returnedMessage.getCreationDate(), persistedMessageEntity.getCreationDate());
      // shouldn't equal as update
      assertNotEquals(returnedMessage.getLastUpdatedDate(), persistedMessageEntity.getLastUpdatedDate());
      assertEquals(returnedMessage.getMessageText(), persistedMessageEntity.getMessageText());
      assertEquals(returnedMessage.getFromGamerId(), persistedMessageEntity.getFromGamer().getId());
      assertEquals(returnedMessage.getToGamerId(), persistedMessageEntity.getToGamer().getId());

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
      assertEquals(returnedMessage.getFromGamerId(), persistedMessageEntity.getFromGamer().getId());
      assertEquals(returnedMessage.getToGamerId(), persistedMessageEntity.getToGamer().getId());

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
      GamerEntity fromGamer = new GamerEntity();
      fromGamer.setId("123");
      GamerEntity toGamer = new GamerEntity();
      toGamer.setId("321");

      messageEntity.setFromGamer(fromGamer);
      messageEntity.setToGamer(toGamer);

      return messageEntity;
   }

   @Test
   public void testFindMessagesForGamer() {
      log.info("testFindMessagesForGamer");
      final Message message = new Message("1","NewMessage", new Date(1656366879731L),
              new Date(1656366879731L), "123","321");

      final MessageEntity persistedMessageEntity = persistedMessageEntity(message);

      final List<MessageEntity> messages = Collections.singletonList(persistedMessageEntity);
      Mockito.when(messageRepository.findByGamerId("123")).thenReturn(messages);
      Mockito.when(messageRepository.findByGamerId("321")).thenReturn(messages);

      List<Message> returnedMessages = messageService.findMessagesForGamer("123");

      assertNotNull(returnedMessages);
      assertEquals(1, returnedMessages.size());
      Message returnedMessage = returnedMessages.get(0);
      assertNotNull(returnedMessage.getId());
      assertNotNull(returnedMessage.getCreationDate());
      assertNotNull(returnedMessage.getLastUpdatedDate());
      assertEquals(returnedMessage.getMessageText(), message.getMessageText());
      assertEquals(returnedMessage.getFromGamerId(), message.getFromGamerId());
      assertEquals(returnedMessage.getToGamerId(), message.getToGamerId());

      returnedMessages = messageService.findMessagesForGamer("321");

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
