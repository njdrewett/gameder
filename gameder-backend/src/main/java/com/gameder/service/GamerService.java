package com.gameder.service;


import com.gameder.api.Gamer;

import java.util.List;

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
     * Retrieve all existing, non-archived Gamers
     * @return
     */
    List<Gamer> retrieveAllGamers();

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

    /**
     * Return true if the given email address already exists against a gamer
     * @param emailAddress
     * @return true if email exists.
     */
    Boolean emailExists(final String emailAddress);

}
