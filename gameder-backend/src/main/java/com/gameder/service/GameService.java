package com.gameder.service;


import com.gameder.api.Game;
import com.gameder.api.Gamer;

import java.util.List;

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
     * Retrieve all existing, non-archived Gamers
     * @return
     */
    List<Game> retrieveAllGames();

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
