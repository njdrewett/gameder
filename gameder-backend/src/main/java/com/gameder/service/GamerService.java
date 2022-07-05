package com.gameder.service;


import com.gameder.api.Gamer;

public interface GamerService {

    /**
     * Create a Gamer Entity
     * @param gamer gamerDTO
     * @return Created Gamer DTO
     */
    Gamer createGamer(final Gamer gamer);

    /**
     * Retreive an existing Gamer
     * @param identifier
     * @return
     */
    Gamer retrieveGamer(final String identifier);

    /**
     * Update an existing Gamer entity
     * @param gamer new Gamer details.
     * @return Updated GemerDTO
     */
    Gamer updateGamer(final Gamer gamer);

    /**
     * Archive the identified gamer
     * @param identifier
     */
    void archiveGamer(final String identifier);
}
