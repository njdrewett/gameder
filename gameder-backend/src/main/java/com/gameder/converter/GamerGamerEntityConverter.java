package com.gameder.converter;

import com.gameder.api.Gamer;
import com.gameder.domain.GamerEntity;

public class GamerGamerEntityConverter {

    public static Gamer toGamer(final GamerEntity gamerEntity) {
        return new Gamer(gamerEntity.getId(), gamerEntity.getDisplayName(),gamerEntity.getDateOfBirth());
    }

    public static GamerEntity toGamerEntity(final Gamer gamer) {
        return toGamerEntity(gamer, new GamerEntity());
    }

    public static GamerEntity toGamerEntity(final Gamer gamer, final GamerEntity gamerEntity) {
        gamerEntity.setId(gamer.getId());
        gamerEntity.setDateOfBirth(gamer.getDateOfBirth());
        gamerEntity.setDisplayName(gamer.getDisplayName());
        return gamerEntity;
    }

}
