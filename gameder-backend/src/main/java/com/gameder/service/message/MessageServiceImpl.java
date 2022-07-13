package com.gameder.service.message;

import com.gameder.api.Message;
import com.gameder.converter.MessageMessageEntityConverter;
import com.gameder.domain.MessageEntity;
import com.gameder.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class MessageServiceImpl extends MessageServiceBase {

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    public MessageServiceImpl(final MessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Message createMessage(final Message message) {
        log.info("Entering createMessage: {}",message);

        final MessageEntity messageEntity = MessageMessageEntityConverter.toMessageEntity(message);

        final Date creationDate = new Date();
        messageEntity.setCreationDate(creationDate);
        messageEntity.setLastUpdatedDate(creationDate);

        final MessageEntity createdEntity = getMessageRepository().save(messageEntity);

        final Message messageResponse = MessageMessageEntityConverter.toMessage(createdEntity);

        log.info("Exiting createMessage: {}" , messageResponse);

        return messageResponse;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Message retrieveMessage(String identifier) {
        log.info("Entering retrieveMessage: {}",identifier);

        final MessageEntity messageEntity = getMessageRepository().findById(identifier).orElseThrow(EntityNotFoundException::new);

        final Message MessageResponse = MessageMessageEntityConverter.toMessage(messageEntity);

        log.info("Exiting retrieveMessage: {}" , MessageResponse);

        return MessageResponse;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Message updateMessage(final Message message) {
        log.info("Entering updateMessage: {}",message);

        final MessageEntity foundMessageEntity = getMessageRepository().findById(message.getId()).orElseThrow(EntityNotFoundException::new);

        final MessageEntity updateMessage = MessageMessageEntityConverter.toMessageEntity(message, foundMessageEntity);
        // specific dates at service tier
        message.setCreationDate(foundMessageEntity.getCreationDate());
        message.setLastUpdatedDate(new Date());

        final MessageEntity createdEntity = getMessageRepository().save(updateMessage);

        final Message messageResponse = MessageMessageEntityConverter.toMessage(createdEntity);

        log.info("Exiting updateMessage: {}" , messageResponse);

        return message;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void archiveMessage(String identifier) {
        log.info("Entering archiveMessage: {}",identifier);

        final MessageEntity foundMessageEntity = getMessageRepository().findById(identifier).orElseThrow(EntityNotFoundException::new);

        foundMessageEntity.setArchived(true);

        getMessageRepository().save(foundMessageEntity);

        log.info("Exiting archiveMessage" );
    }
}
