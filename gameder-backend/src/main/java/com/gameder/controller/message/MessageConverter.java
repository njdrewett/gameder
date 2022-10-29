package com.gameder.controller.message;

import com.gameder.api.Message;
import com.gameder.api.message.CreateMessageRequest;
import com.gameder.api.message.RetrieveMessageResponse;
import com.gameder.api.message.UpdateMessageRequest;
import com.gameder.controller.gamer.GamerConverter;

import java.util.List;
import java.util.stream.Collectors;

public class MessageConverter {

    public static Message toMessage(final CreateMessageRequest createMessageRequest) {
        return new Message(null, createMessageRequest.getMessageText(), null, null,
                createMessageRequest.getFromGamerId(), createMessageRequest.getToGamerId());
    }

    public static Message toMessage(final UpdateMessageRequest updateMessageRequest) {
        return new Message(updateMessageRequest.getId(), updateMessageRequest.getMessageText(),
                null,null, updateMessageRequest.getFromGamerId(), updateMessageRequest.getToGamerId());
    }

    public static RetrieveMessageResponse toRetrieveMessageResponse(final Message message) {
        return new RetrieveMessageResponse(message.getId(), message.getMessageText(), message.getCreationDate(), message.getLastUpdatedDate(),
                message.getFromGamerId(), message.getToGamerId());
    }

    public static List<RetrieveMessageResponse> toRetrieveMessageResponse(final List<Message> messages) {
        return messages.stream().map(MessageConverter::toRetrieveMessageResponse).collect(Collectors.toList());
    }

}
