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
     * Retrieve an existing Gamer
     * @param identifier gamerId
     * @return found gamer or null if not exists.
     */
    Gamer retrieveGamer(final String identifier);

    /**
     * Retrieve all existing, non-archived Gamers
     * @return All existing gamers.
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
     * @param identifier gamer id to archive.
     */
    void archiveGamer(final String identifier);

    /**
     * Return true if the given email address already exists against a gamer
     * @param emailAddress email address to check if exists.
     * @return true if email exists.
     */
    Boolean emailExists(final String emailAddress);

    /**
     * Find a return Gamers that result from the given query object.
     * @Param GamerQuery Object.
     *
     */

}
