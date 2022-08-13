package com.gameder.service.game;

import com.gameder.repository.GameRepository;
import com.gameder.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GameServiceBase implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    GameServiceBase(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

}
