package com.gameder.controller.game;

import com.gameder.api.Game;
import com.gameder.api.game.CreateGameRequest;
import com.gameder.api.game.RetrieveGameResponse;
import com.gameder.api.game.UpdateGameRequest;

public class GameConverter {

    public static Game toGame(final CreateGameRequest createGameRequest) {
        return new Game(null, createGameRequest.getDisplayName(), createGameRequest.getAgeRestriction(), createGameRequest.getDisplayImage(),
                createGameRequest.getDescription());
    }

    public static Game toGame(final UpdateGameRequest updateGameRequest) {
        return new Game(updateGameRequest.getId(), updateGameRequest.getDisplayName(),
                updateGameRequest.getAgeRestriction(),updateGameRequest.getDisplayImage(), updateGameRequest.getDescription());
    }

    public static RetrieveGameResponse toRetrieveGameResponse(final Game game) {
        return new RetrieveGameResponse(game.getId(), game.getDisplayName(), game.getDescriptionText(), game.getGameImage(),game.getAgeRestriction());
    }

}
