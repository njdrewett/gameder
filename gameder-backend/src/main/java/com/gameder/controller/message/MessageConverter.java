package com.gameder.controller.message;

import com.gameder.api.Message;
import com.gameder.api.message.CreateMessageRequest;
import com.gameder.api.message.RetrieveMessageResponse;
import com.gameder.api.message.UpdateMessageRequest;

public class MessageConverter {

    public static Message toMessage(final CreateMessageRequest createMessageRequest) {
        return new Message(null, createMessageRequest.getMessageText(), null, null,
                createMessageRequest.getFromUserId(), createMessageRequest.getToUserId());
    }

    public static Message toMessage(final UpdateMessageRequest updateMessageRequest) {
        return new Message(updateMessageRequest.getId(), updateMessageRequest.getMessageText(),
                null,null, updateMessageRequest.getFromUserId(), updateMessageRequest.getToUserId());
    }

    public static RetrieveMessageResponse toRetrieveMessageResponse(final Message message) {
        return new RetrieveMessageResponse(message.getId(), message.getMessageText(), message.getCreationDate(), message.getLastUpdatedDate(),
                message.getFromUserId(), message.getToUserId());
    }

}
