package com.gameder.controller.message;

import com.gameder.api.gamer.CreateGamerResponse;
import com.gameder.api.message.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageController {

    ResponseEntity<CreateMessageResponse> createMessage(final CreateMessageRequest createMessageRequest);

    ResponseEntity<RetrieveMessageResponse> retrieveMessage(final String messageId);

    ResponseEntity<UpdateMessageResponse> updateMessage(final UpdateMessageRequest updateMessageRequest);

    ResponseEntity<List<RetrieveMessageResponse>> findAllMessagesForGamer(final String gamerId);

    void archiveMessage(final String messageId) ;
}
