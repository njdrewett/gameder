package com.gameder.service.gamer;

import com.gameder.repository.GamerRepository;
import com.gameder.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GamerServiceBase implements GamerService {

    private final GamerRepository gamerRepository;

    @Autowired
    GamerServiceBase(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    public GamerRepository getGamerRepository() {
        return gamerRepository;
    }

}
