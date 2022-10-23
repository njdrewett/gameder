package com.gameder.service.gamer;

import com.gameder.repository.GamerRepository;
import com.gameder.repository.GamerRepositoryCustom;
import com.gameder.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GamerServiceBase implements GamerService {

    private final GamerRepository gamerRepository;
    private final GamerRepositoryCustom gamerRepositoryCustom;

    @Autowired
    GamerServiceBase(
            GamerRepository gamerRepository,
            GamerRepositoryCustom gamerRepositoryCustom) {
        this.gamerRepository = gamerRepository;
        this.gamerRepositoryCustom = gamerRepositoryCustom;
    }

    public GamerRepository getGamerRepository() {
        return gamerRepository;
    }

    public GamerRepositoryCustom getGamerRepositoryCustom() {
        return gamerRepositoryCustom;
    }

}
