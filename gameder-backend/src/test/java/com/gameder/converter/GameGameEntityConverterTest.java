package com.gameder.converter;

import com.gameder.api.Game;
import com.gameder.api.Gamer;
import com.gameder.domain.GameEntity;
import com.gameder.domain.GamerEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Game To Entity test
 *
 */
public class GameGameEntityConverterTest {

    private final Logger log = LoggerFactory.getLogger(GameGameEntityConverterTest.class);

    @Test
    public void toGameTest() {
        GameEntity gameEntity = createGameEntity();
        log.info("toGameTest {}", gameEntity);

        final Game game = GameGameEntityConverter.toGame(gameEntity);

        assertNotNull(game.getId());
        assertEquals(game.getId(), gameEntity.getId());
        assertEquals(game.getAgeRestriction(), gameEntity.getAgeRestriction());
        assertEquals(game.getDisplayName(), gameEntity.getDisplayName());
        assertEquals(game.getDescriptionText(), gameEntity.getDescriptionText());

        log.info("toGameTest {}", game);
    }

    @Test
    public void toGameListTest() {
        GameEntity gameEntity = createGameEntity();
        log.info("toGameTest {}", gameEntity);

        List<GameEntity> gameEntities = Collections.singletonList(gameEntity);

        final List<Game> allGames = GameGameEntityConverter.toGame(gameEntities);

        assertTrue( allGames.size() == 1,"List must have one element");
        Game game = allGames.get(0);
        assertNotNull(game.getId());
        assertEquals(game.getId(), gameEntity.getId());
        assertEquals(game.getAgeRestriction(), gameEntity.getAgeRestriction());
        assertEquals(game.getDisplayName(), gameEntity.getDisplayName());
        assertEquals(game.getDescriptionText(), gameEntity.getDescriptionText());

        log.info("toGameTest {}", game);
    }

    @Test
    public void toGameEntityTest() {
        Game game = createGame();
        log.info("toGameTest {}", game);

        final GameEntity gameEntity = GameGameEntityConverter.toGameEntity(game);

        assertNotNull(gameEntity.getId());
        assertEquals(gameEntity.getId(), game.getId());
        assertEquals(gameEntity.getAgeRestriction(), game.getAgeRestriction());
        assertEquals(gameEntity.getDisplayName(), game.getDisplayName());
        assertEquals(gameEntity.getDescriptionText(), game.getDescriptionText());

        log.info("toGameTest {}", gameEntity);
    }

    private GameEntity createGameEntity() {
        final GameEntity gameEntity = new GameEntity();
        gameEntity.setId("1");
        gameEntity.setDisplayName("Test Game");
        gameEntity.setDescriptionText("Description text");
        gameEntity.setAgeRestriction(12);

        return gameEntity;
    }

    private Game createGame() {
        final Game game = new Game();
        game.setId("1");
        game.setDisplayName("Test Game");
        game.setDescriptionText("Description text");
        game.setAgeRestriction(12);
        return game;
    }

}
