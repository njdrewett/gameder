package com.gameder.controller.message;

import com.gameder.api.Message;
import com.gameder.api.message.CreateMessageRequest;
import com.gameder.api.message.RetrieveMessageResponse;
import com.gameder.api.message.UpdateMessageRequest;

public class MessageConverter {

    public static Message toMessage(final CreateMessageRequest createMessageRequest) {
        return new Message(null, createMessageRequest.getMessageText(), createMessageRequest.getCreationDate());
    }

    public static Message toMessage(final UpdateMessageRequest updateMessageRequest) {
        return new Message(updateMessageRequest.getId(), updateMessageRequest.getMessageText(), updateMessageRequest.getCreationDate());
    }

    public static RetrieveMessageResponse toRetrieveMessageResponse(final Message message) {
        return new RetrieveMessageResponse(message.getId(), message.getMessageText(), message.getCreationDate());
    }

}
