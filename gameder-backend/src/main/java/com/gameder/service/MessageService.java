package com.gameder.service;


import com.gameder.api.Gamer;
import com.gameder.api.Message;

public interface MessageService {

    /**
     * Create a Message Entity
     * @param message Message
     * @return Created Message
     */
    Message createMessage(final Message message);

    /**
     * Retreive an existing Message
     * @param identifier message id
     * @return requested message
     */
    Message retrieveMessage(final String identifier);

    /**
     * Update an existing Message entity
     * @param message new Message details.
     * @return Updated Message
     */
    Message updateMessage(final Message message);

    /**
     * Archive the identified Message
     * @param identifier message identifier
     */
    void archiveMessage(final String identifier);
}
