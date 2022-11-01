package com.gameder.controller.message;

import com.gameder.api.Message;
import com.gameder.api.Message.*;
import com.gameder.api.message.*;
import com.gameder.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/message")
public class MessageControllerImpl implements MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageControllerImpl.class);

    private final MessageService messageService;

    @Autowired
    MessageControllerImpl(final MessageService MessageService) {
        this.messageService = MessageService;
    }

    @PostMapping
    public ResponseEntity<CreateMessageResponse> createMessage(@RequestBody final CreateMessageRequest createMessageRequest) {
        log.info("createMessage {}" , createMessageRequest);

        final Message message = MessageConverter.toMessage(createMessageRequest);
        final Message createResponse = messageService.createMessage(message);

        final CreateMessageResponse response = new CreateMessageResponse(createResponse.getId(), Boolean.TRUE);

        log.info("Response: {}" , response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path="{messageId}")
    public ResponseEntity<RetrieveMessageResponse> retrieveMessage(@PathVariable("messageId") String MessageId) {
        log.info("retrieveMessage {} ", MessageId);

        final Message message = messageService.retrieveMessage(MessageId);
        final RetrieveMessageResponse retrieveMessageResponse = MessageConverter.toRetrieveMessageResponse(message);

        log.info("retrieveMessage {}" , retrieveMessageResponse);
        return new ResponseEntity<>(retrieveMessageResponse, HttpStatus.OK);
    }

    @PostMapping(path="/update")
    public ResponseEntity<UpdateMessageResponse> updateMessage(@RequestBody UpdateMessageRequest updateMessageRequest) {
        log.info("updateMessage {}", updateMessageRequest);

        final Message message = MessageConverter.toMessage(updateMessageRequest);
        final Message messageResponse = messageService.updateMessage(message);
        final UpdateMessageResponse updateMessageResponse = new UpdateMessageResponse(messageResponse.getId(), true);

        log.info("updateMessage {}", updateMessageResponse);
        return new ResponseEntity<>(updateMessageResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping(path="/findByGamer/{gamerId}")
    public ResponseEntity<List<RetrieveMessageResponse>> findAllMessagesForGamer(@PathVariable("gamerId") final String gamerId) {
        log.info("findAllMessagesForGamer {}", gamerId);

        final List<Message> messages = messageService.findMessagesForGamer(gamerId);
        final List<RetrieveMessageResponse> messageResponses = MessageConverter.toRetrieveMessageResponse(messages);

        log.info("findAllMessagesForGamer {}", messageResponses);
        return new ResponseEntity<>(messageResponses, HttpStatus.OK);
    }

    @DeleteMapping(path="{messageId}")
    public void archiveMessage(@PathVariable("messageId") String messageId) {
        log.info("archiveMessage {} ", messageId);

        messageService.archiveMessage(messageId);

        log.info("archiveMessage  ");
    }
}
