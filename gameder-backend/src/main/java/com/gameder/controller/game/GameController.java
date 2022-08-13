package com.gameder.controller.game;

import com.gameder.api.game.*;
import org.springframework.http.ResponseEntity;

public interface GameController {

    ResponseEntity<CreateGameResponse> createGame(final CreateGameRequest createGameRequest);

    ResponseEntity<RetrieveGameResponse> retrieveGame(final String GameId);

    ResponseEntity<UpdateGameResponse> updateGame(final UpdateGameRequest updateGameRequest);

    void archiveGame(String GameId) ;
}
