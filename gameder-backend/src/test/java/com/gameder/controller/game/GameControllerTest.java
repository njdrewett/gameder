package com.gameder.controller.game;

import com.gameder.api.Game;
import com.gameder.api.game.*;
import com.gameder.controller.game.GameController;
import com.gameder.controller.game.GameControllerImpl;
import com.gameder.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Game unit Test
 */
@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private static final Logger log = LoggerFactory.getLogger(GameControllerTest.class);

    GameController gameController;

    @Mock
    private GameService gameService;

    @BeforeEach
    public void init() {
        gameController = new GameControllerImpl(gameService);
    }

    @Test
    public void testCreateGame() {
        log.info("testCreateGame");

        final Game game = new Game("1","NewGameDisplayName",18,
                null,"This is a new Game" );
        Mockito.when(gameService.createGame(Mockito.any(Game.class))).thenReturn(game);

        final CreateGameRequest createGameRequest = new CreateGameRequest("NewGameDisplayName",
                "This is a new Game", null, 18);
        final ResponseEntity<CreateGameResponse> returnedGame = gameController.createGame(createGameRequest);

        final CreateGameResponse body = returnedGame.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), game.getId());

        log.info("testCreateGameResponse {}", returnedGame );
    }

    @Test
    public void testUpdateGame() {
        log.info("testUpdateGame");

        final Game game = new Game("1","NewGameDisplayName",18,
                null,"This is a new Game" );
        Mockito.when(gameService.updateGame(Mockito.any(Game.class))).thenReturn(game);

        final UpdateGameRequest updateGameRequest = new UpdateGameRequest("1","NewGameDisplayName",
                "This is a new Game", null, 18);
        final ResponseEntity<UpdateGameResponse> returnedGame = gameController.updateGame(updateGameRequest);

        final UpdateGameResponse body = returnedGame.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), game.getId());

        log.info("testUpdateGame {}", returnedGame);
    }

    @Test
    public void testUpdateGameNotExists() {
        log.info("testUpdateGameNotExists");

        final Game game = new Game("1","NewGameDisplayName",18,
                null,"This is a new Game" );
        Mockito.when(gameService.updateGame(Mockito.any(Game.class))).thenReturn(game);

        final UpdateGameRequest updateGameRequest = new UpdateGameRequest("2","NewGameDisplayName","This is a new Game",
                null ,18);
        final ResponseEntity<UpdateGameResponse> returnedGame = gameController.updateGame(updateGameRequest);

        final UpdateGameResponse body = returnedGame.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), game.getId());

        log.info("testUpdateGameNotExists");
    }

    @Test
    public void testRetrieveGame() {
        log.info("testRetrieveGame");

        final Game game = new Game("1","NewGameDisplayName",18,
                null,"This is a new Game" );

        Mockito.when(gameService.retrieveGame("1")).thenReturn(game);

        final ResponseEntity<RetrieveGameResponse> returnedGame = gameController.retrieveGame("1");

        assertNotNull(returnedGame.getBody().getId());

        log.info("testRetrieveGame {}", returnedGame);
    }

    @Test
    public void testRetrieveGameNotFound() {
        log.info("testRetrieveGame");

        Mockito.when(gameService.retrieveGame("1")).thenThrow(new EntityNotFoundException());

        try {
            gameController.retrieveGame("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testRetrieveGame ");
    }

    @Test
    public void testArchiveGame() {
        log.info("testArchiveGame");

        Mockito.doNothing().when(gameService).archiveGame("1");

        gameService.archiveGame("1");

        log.info("testArchiveGame");
    }

    @Test
    public void testArchiveGameNotFound() {
        log.info("testArchiveGame");

        Mockito.doThrow(new EntityNotFoundException()).when(gameService).archiveGame("1");

        try {
            gameService.archiveGame("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveGameNotFound");
    }
}
