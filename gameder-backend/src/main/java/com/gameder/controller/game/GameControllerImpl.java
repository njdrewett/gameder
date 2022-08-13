package com.gameder.controller.game;

import com.gameder.api.Game;
import com.gameder.api.game.*;
import com.gameder.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/game")
public class GameControllerImpl implements GameController {

    private static final Logger log = LoggerFactory.getLogger(GameControllerImpl.class);

    private final GameService gameService;

    @Autowired
    GameControllerImpl(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<CreateGameResponse> createGame(@RequestBody final CreateGameRequest createGameRequest) {
        log.info("createGame {}" , createGameRequest);

        final Game Game = GameConverter.toGame(createGameRequest);
        final Game createResponse = gameService.createGame(Game);

        final CreateGameResponse response = new CreateGameResponse(createResponse.getId(), Boolean.TRUE);

        log.info("Response: {}" , response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path="{GameId}")
    public ResponseEntity<RetrieveGameResponse> retrieveGame(@PathVariable("gameId") String gameId) {
        log.info("retrieveGame {} ", gameId);

        final Game game = gameService.retrieveGame(gameId);
        final RetrieveGameResponse retrieveGameResponse = GameConverter.toRetrieveGameResponse(game);

        log.info("retrieveGame {}" , retrieveGameResponse);
        return new ResponseEntity<>(retrieveGameResponse, HttpStatus.OK);
    }

    @PostMapping(path="/update")
    public ResponseEntity<UpdateGameResponse> updateGame(@RequestBody UpdateGameRequest updateGameRequest) {
        log.info("updateGame {}", updateGameRequest);

        final Game game = GameConverter.toGame(updateGameRequest);
        final Game GameResponse = gameService.updateGame(game);
        final UpdateGameResponse updateGameResponse = new UpdateGameResponse(GameResponse.getId(), true);

        log.info("updateGame {}", updateGameResponse);
        return new ResponseEntity<>(updateGameResponse, HttpStatus.OK);
    }

    @DeleteMapping(path="{gameId}")
    public void archiveGame(@PathVariable("gameId") String gameId) {
        log.info("archiveGame {} ", gameId);

        gameService.archiveGame(gameId);

        log.info("archivedGame  ");
    }
}
