package com.gameder.controller.game;

import com.gameder.api.game.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Game unit Test
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GameControllerIT {

    private final Logger log = LoggerFactory.getLogger(GameControllerIT.class);

    @Autowired
    private GameController gameController;

    @Test
    public void testCreateGame() {
        log.info("testCreateGame");

        final ResponseEntity<CreateGameResponse> returnedGame = createGame();
        assertNotNull(returnedGame.getBody().getId());
        assertTrue(returnedGame.getBody().getSuccess());

        log.info("testCreateGameResponse {}", returnedGame );
    }

    private ResponseEntity<CreateGameResponse> createGame() {
        final CreateGameRequest createGameRequest = new CreateGameRequest("NewGameDisplayName",
                "This is a new Game", null, 18);

        final ResponseEntity<CreateGameResponse> returnedGame = gameController.createGame(createGameRequest);

        final CreateGameResponse body = returnedGame.getBody();
        assertTrue(body.getSuccess());
        return returnedGame;
    }

    @Test
    public void testUpdateGame() {
        log.info("testUpdateGame");

        final ResponseEntity<CreateGameResponse> returnedGame = createGame();
        final UpdateGameRequest updateGameRequest =
                new UpdateGameRequest(returnedGame.getBody().getId(),"NewGameDisplayNameUpdated",
                        "This is an updated Game", null, 12);
        final ResponseEntity<UpdateGameResponse> returnedUpdateGame = gameController.updateGame(updateGameRequest);

        final UpdateGameResponse body = returnedUpdateGame.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), returnedGame.getBody().getId());

        log.info("testUpdateGame {}", returnedGame);
    }

    @Test
    public void testUpdateGameNotExists() {
        log.info("testUpdateGameNotExists");

        final UpdateGameRequest updateGameRequest = new UpdateGameRequest("2", "NewGameDisplayNameUpdated",
                "This is an updated Game", null, 12);
        try {
            gameController.updateGame(updateGameRequest);
            fail("EntityNotFoundException should have been thrown ");
        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testUpdateGameNotExists");
    }

    @Test
    public void testRetrieveGame() {
        log.info("testRetrieveGame");

        final ResponseEntity<CreateGameResponse> returnedGame = createGame();

        final ResponseEntity<RetrieveGameResponse> getGameResponse = gameController.retrieveGame(returnedGame.getBody().getId());

        assertNotNull(getGameResponse.getBody().getId());

        log.info("testRetrieveGame {}", returnedGame);
    }

    @Test
    public void testRetrieveGameNotFound() {
        log.info("testRetrieveGame");

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

        final ResponseEntity<CreateGameResponse> returnedGame = createGame();

        gameController.archiveGame(returnedGame.getBody().getId());

        log.info("testArchiveGame");
    }

    @Test
    public void testArchiveGameNotFound() {
        log.info("testArchiveGame");

        try {
            gameController.archiveGame("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveGameNotFound");
    }
}
