package com.gameder.service.game;

import com.gameder.api.Game;
import com.gameder.api.Gamer;
import com.gameder.converter.GameGameEntityConverter;
import com.gameder.converter.GamerGamerEntityConverter;
import com.gameder.domain.GameEntity;
import com.gameder.domain.GamerEntity;
import com.gameder.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameServiceImpl extends GameServiceBase {

    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    public GameServiceImpl(final GameRepository gameRepository) {
        super(gameRepository);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Game createGame(final Game game) {
        log.info("Entering createGame: {}",game);

        final GameEntity gameEntity = GameGameEntityConverter.toGameEntity(game);

        final GameEntity createdEntity = getGameRepository().save(gameEntity);

        final Game gameResponse = GameGameEntityConverter.toGame(createdEntity);

        log.info("Exiting createGame: {}" , gameResponse);

        return gameResponse;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Game retrieveGame(String identifier) {
        log.info("Entering retrieveGame: {}",identifier);

        final GameEntity gameEntity = getGameRepository().findById(identifier).orElseThrow(() -> new EntityNotFoundException("Game: " + identifier));

        final Game gameResponse = GameGameEntityConverter.toGame(gameEntity);

        log.info("Exiting retrieveGame: {}" , gameResponse);

        return gameResponse;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Game> retrieveAllGames() {
        log.info("Entering retrieveAllGames");

        final List<GameEntity> gameEntities = getGameRepository().findAll();

        final List<Game> gameResponse = GameGameEntityConverter.toGame(gameEntities);

        log.info("Exiting retrieveAllGames");

        return gameResponse;

    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Game updateGame(final Game game) {
        log.info("Entering updateGame: {}",game);

        final GameEntity foundGameEntity = getGameRepository().findById(game.getId()).orElseThrow(() -> new EntityNotFoundException("Game: " + game.getId()));

        final GameEntity updateGame = GameGameEntityConverter.toGameEntity(game, foundGameEntity);

        final GameEntity createdEntity = getGameRepository().save(updateGame);

        final Game gameResponse = GameGameEntityConverter.toGame(createdEntity);

        log.info("Exiting updateGame: {}" , gameResponse);

        return game;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void archiveGame(String identifier) {
        log.info("Entering archiveGame: {}",identifier);

        final GameEntity foundGameEntity = getGameRepository().findById(identifier).orElseThrow(() -> new EntityNotFoundException("Game: " + identifier));

        foundGameEntity.setArchived(true);

        getGameRepository().save(foundGameEntity);

        log.info("Exiting archiveGame" );
    }
}
