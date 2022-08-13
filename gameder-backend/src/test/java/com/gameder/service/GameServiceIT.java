package com.gameder.service;


import com.gameder.api.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GameServiceIT {

    private final Logger log = LoggerFactory.getLogger(GameServiceIT.class);

    @Autowired
    private GameService gameService;

    @Test
    public void testCreateGame() {
        log.info("testCreateGame");

        final Game returnedGame = createGame();

        log.info("testCreateGameResponse {}", returnedGame );
    }

    private Game createGame() {
        final Game game = new Game(null,"NewGame", 18,
                null, "Hi Im a game");

        final Game returnedGame = gameService.createGame(game);

        assertNotNull(returnedGame.getId());
        assertEquals(returnedGame.getDescriptionText(), game.getDescriptionText());
        assertEquals(returnedGame.getDisplayName(), game.getDisplayName());
        assertEquals(returnedGame.getAgeRestriction(), game.getAgeRestriction());
        assertEquals(returnedGame.getGameImage(), game.getGameImage());

        return returnedGame;
    }

    @Test
    public void testUpdateGame() {
        log.info("testUpdateGame");

        final Game createdGame = createGame();

        final Game game = new Game(createdGame.getId(),"NewGameUpdated", 12, null,
                "hi Im updated");

        final Game returnedGame = gameService.updateGame(game);

        assertNotNull(returnedGame.getId());
        assertEquals(returnedGame.getId(), game.getId());
        assertEquals(returnedGame.getDescriptionText(), game.getDescriptionText());
        assertEquals(returnedGame.getDisplayName(), game.getDisplayName());
        assertEquals(returnedGame.getAgeRestriction(), game.getAgeRestriction());

        log.info("testUpdateGame {}", returnedGame);
    }

    @Test
    public void testUpdateGameNotExists() {
        log.info("testUpdateGame");

        final Game game = new Game("1","NewGameUpdated", 12, null,
                "hi Im updated");

        try {
            gameService.updateGame(game);

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testUpdateGameNotExists");
    }

    @Test
    public void testRetrieveGame() {
        log.info("testRetrieveGame");

        final Game createdGame = createGame();

        final Game returnedGame = gameService.retrieveGame(createdGame.getId());

        assertNotNull(returnedGame.getId());

        log.info("testRetrieveGame {}", returnedGame);
    }

    @Test
    public void testRetrieveGameNotFound() {
        log.info("testRetrieveGame");

        try {
            gameService.retrieveGame("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testRetrieveGame ");
    }

    @Test
    public void testArchiveGame() {
        log.info("testArchiveGame");

        final Game createdGame = createGame();

        gameService.archiveGame(createdGame.getId());

        log.info("testArchiveGame");
    }

    @Test
    public void testArchiveGameNotFound() {
        log.info("testArchiveGame");

        try {
            gameService.archiveGame("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveGameNotFound");
    }
}
