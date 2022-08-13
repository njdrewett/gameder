package com.gameder.converter;

import com.gameder.api.Game;
import com.gameder.domain.GameEntity;

public class GameGameEntityConverter {

    public static Game toGame(final GameEntity gameEntity) {
        return new Game(gameEntity.getId(), gameEntity.getDisplayName(), gameEntity.getAgeRestriction(),
                gameEntity.getGameImage(),gameEntity.getDescriptionText());
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
