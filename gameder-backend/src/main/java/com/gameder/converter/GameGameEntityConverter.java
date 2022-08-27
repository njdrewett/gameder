package com.gameder.converter;

import com.gameder.api.Game;
import com.gameder.api.Gamer;
import com.gameder.domain.GameEntity;
import com.gameder.domain.GamerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GameGameEntityConverter {

    public static Game toGame(final GameEntity gameEntity) {
        return new Game(gameEntity.getId(), gameEntity.getDisplayName(), gameEntity.getAgeRestriction(),
                gameEntity.getGameImage(),gameEntity.getDescriptionText());
    }

    public static List<Game> toGame(final List<GameEntity> gameEntity) {
        return gameEntity.stream().map(GameGameEntityConverter::toGame).collect(Collectors.toList());
    }

    public static GameEntity toGameEntity(final Game game) {
        return toGameEntity(game, new GameEntity());
    }

    public static GameEntity toGameEntity(final Game game, final GameEntity gameEntity) {
        gameEntity.setId(game.getId());
        gameEntity.setGameImage(game.getGameImage());
        gameEntity.setDisplayName(game.getDisplayName());
        gameEntity.setAgeRestriction(game.getAgeRestriction());
        gameEntity.setDescriptionText(game.getDescriptionText());

        return gameEntity;
    }

}
