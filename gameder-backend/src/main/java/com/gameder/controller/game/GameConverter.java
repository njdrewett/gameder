package com.gameder.controller.game;

import com.gameder.api.Game;
import com.gameder.api.Gamer;
import com.gameder.api.game.CreateGameRequest;
import com.gameder.api.game.RetrieveGameResponse;
import com.gameder.api.game.UpdateGameRequest;
import com.gameder.api.gamer.RetrieveGamerResponse;
import com.gameder.controller.gamer.GamerConverter;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<RetrieveGameResponse> toRetrieveGameResponse(final List<Game> games) {
        return games.stream().map(GameConverter::toRetrieveGameResponse).collect(Collectors.toList());
    }

}
