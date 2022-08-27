package com.gameder.controller.game;

import com.gameder.api.game.*;
import com.gameder.api.gamer.RetrieveGamerResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GameController {

    ResponseEntity<CreateGameResponse> createGame(final CreateGameRequest createGameRequest);

    ResponseEntity<RetrieveGameResponse> retrieveGame(final String GameId);

    ResponseEntity<UpdateGameResponse> updateGame(final UpdateGameRequest updateGameRequest);

    ResponseEntity<List<RetrieveGameResponse>> retrieveAllGames();

    void archiveGame(String GameId) ;
}
