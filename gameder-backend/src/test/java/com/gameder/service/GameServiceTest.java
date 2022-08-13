package com.gameder.service;

import com.gameder.api.Game;
import com.gameder.domain.GameEntity;
import com.gameder.repository.GameRepository;
import com.gameder.service.game.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Game unit Test
 */
@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

   private final Logger log = LoggerFactory.getLogger(GameServiceTest.class);

   private GameService gameService;

   @Mock
   private GameRepository gameRepository;

   @BeforeEach
   void setup() {
      log.info("setup");
      gameService = new GameServiceImpl(gameRepository);
   }

   @Test
   public void testCreateGame() {
      log.info("testCreateGame");

      final Game game = new Game(null,"NewGame", 18,
              null, "Hi Im a game");

      final GameEntity persistedGameEntity = persistedGameEntity(game);

      Mockito.when(gameRepository.save(Mockito.any(GameEntity.class))).thenReturn(persistedGameEntity);

      final Game returnedGame = gameService.createGame(game);

      assertNotNull(returnedGame.getId());
      assertEquals(returnedGame.getId(), persistedGameEntity.getId());
      assertEquals(returnedGame.getDescriptionText(), game.getDescriptionText());
      assertEquals(returnedGame.getDisplayName(), game.getDisplayName());
      assertEquals(returnedGame.getAgeRestriction(), game.getAgeRestriction());
      assertEquals(returnedGame.getGameImage(), game.getGameImage());

      log.info("testCreateGameResponse {}", returnedGame );
   }

   @Test
   public void testUpdateGame() {
      log.info("testUpdateGame");

      final Game game = new Game("1","NewGame", 18,
              null, "Hi Im a game");

      final GameEntity persistedGameEntity = persistedGameEntity(game);

      Mockito.when(gameRepository.findById(game.getId())).thenReturn(Optional.of(persistedGameEntity));
      Mockito.when(gameRepository.save(Mockito.any(GameEntity.class))).thenReturn(persistedGameEntity);

      final Game returnedGame = gameService.updateGame(game);

      assertNotNull(returnedGame.getId());
      assertEquals(returnedGame.getId(), persistedGameEntity.getId());
      assertEquals(returnedGame.getDescriptionText(), game.getDescriptionText());
      assertEquals(returnedGame.getDisplayName(), game.getDisplayName());
      assertEquals(returnedGame.getAgeRestriction(), game.getAgeRestriction());
      assertEquals(returnedGame.getGameImage(), game.getGameImage());

      log.info("testUpdateGame {}", returnedGame);
   }

   @Test
   public void testUpdateGameNotExists() {
      log.info("testUpdateGame");

      final Game game = new Game("1","NewGame", 18,
              null, "Hi Im a game");

      Mockito.when(gameRepository.findById(game.getId())).thenReturn(Optional.empty());

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

      final Game game = new Game("1","NewGame", 18,
              null, "Hi Im a game");

      final GameEntity persistedGameEntity = persistedGameEntity(game);

      Mockito.when(gameRepository.findById("1")).thenReturn(Optional.of(persistedGameEntity));

      final Game returnedGame = gameService.retrieveGame("1");

      assertNotNull(returnedGame.getId());
      assertEquals(returnedGame.getId(), persistedGameEntity.getId());
      assertEquals(returnedGame.getDescriptionText(), game.getDescriptionText());
      assertEquals(returnedGame.getDisplayName(), game.getDisplayName());
      assertEquals(returnedGame.getAgeRestriction(), game.getAgeRestriction());
      assertEquals(returnedGame.getGameImage(), game.getGameImage());

      log.info("testRetrieveGame {}", returnedGame);
   }

   @Test
   public void testRetrieveGameNotFound() {
      log.info("testRetrieveGame");

      Mockito.when(gameRepository.findById("1")).thenReturn(Optional.empty());

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

      final Game game = new Game("1","NewGame", 18,
              null, "Hi Im a game");
      final GameEntity persistedGameEntity = persistedGameEntity(game);

      Mockito.when(gameRepository.findById("1")).thenReturn(Optional.of(persistedGameEntity));

      gameService.archiveGame("1");

      log.info("testArchiveGame");
   }

   @Test
   public void testArchiveGameNotFound() {
      log.info("testArchiveGame");

      Mockito.when(gameRepository.findById("1")).thenReturn(Optional.empty());

      try {
         gameService.archiveGame("1");

         fail("EntityNotFoundException NOT thrown as expected");

      } catch (EntityNotFoundException exception) {
         log.info("EntityNotFoundException thrown as expected" );
      }

      log.info("testArchiveGameNotFound");
   }


   private GameEntity persistedGameEntity(final Game game) {
      final GameEntity gameEntity = new GameEntity();
      gameEntity.setId("1");
      gameEntity.setDisplayName(game.getDisplayName());
      gameEntity.setAgeRestriction(game.getAgeRestriction());
      gameEntity.setDescriptionText(game.getDescriptionText());
      gameEntity.setGameImage(game.getGameImage());

      return gameEntity;
   }
}
