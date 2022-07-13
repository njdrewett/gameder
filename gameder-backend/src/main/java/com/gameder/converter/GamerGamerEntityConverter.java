package com.gameder.converter;

import com.gameder.api.Gamer;
import com.gameder.domain.GamerEntity;

public class GamerGamerEntityConverter {

    public static Gamer toGamer(final GamerEntity gamerEntity) {
        return new Gamer(gamerEntity.getId(), gamerEntity.getDisplayName(),gamerEntity.getDateOfBirth(),
                gamerEntity.getEmailAddress(),gamerEntity.getTelephoneNumber(),gamerEntity.getProfileImage(),gamerEntity.getIntroductionText());
    }

    public static GamerEntity toGamerEntity(final Gamer gamer) {
        return toGamerEntity(gamer, new GamerEntity());
    }

    public static GamerEntity toGamerEntity(final Gamer gamer, final GamerEntity gamerEntity) {
        gamerEntity.setId(gamer.getId());
        gamerEntity.setDateOfBirth(gamer.getDateOfBirth());
        gamerEntity.setDisplayName(gamer.getDisplayName());
        gamerEntity.setEmailAddress(gamer.getEmailAddress());
        gamerEntity.setIntroductionText(gamer.getIntroductionText());
        gamerEntity.setProfileImage(gamer.getProfileImage());
        gamerEntity.setTelephoneNumber(gamer.getTelephoneNumber());

        return gamerEntity;
    }

}
