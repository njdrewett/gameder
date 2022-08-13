package com.gameder.service;


import com.gameder.api.Game;

public interface GameService {

    /**
     * Create a Game Entity
     * @param game game DTO
     * @return Created Game DTO
     */
    Game createGame(final Game game);

    /**
     * Retreive an existing Game
     * @param identifier
     * @return
     */
    Game retrieveGame(final String identifier);

    /**
     * Update an existing Game entity
     * @param game new Game details.
     * @return Updated GemerDTO
     */
    Game updateGame(final Game game);

    /**
     * Archive the identified game
     * @param identifier
     */
    void archiveGame(final String identifier);
}
