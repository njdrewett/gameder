package com.gameder.converter;

import com.gameder.api.Gamer;
import com.gameder.domain.GamerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GamerGamerEntityConverter {

    public static Gamer toGamer(final GamerEntity gamerEntity) {
        return new Gamer(gamerEntity.getId(), gamerEntity.getDisplayName(),gamerEntity.getDateOfBirth(),
                gamerEntity.getEmailAddress(),gamerEntity.getTelephoneNumber(),gamerEntity.getProfileImage(),gamerEntity.getIntroductionText(),gamerEntity.getPassword());
    }

    public static List<Gamer> toGamer(final List<GamerEntity> gamerEntity) {
        return gamerEntity.stream().map(GamerGamerEntityConverter::toGamer).collect(Collectors.toList());
    }

    public static GamerEntity toGamerEntity(final Gamer gamer) {
        return toGamerEntity(gamer, new GamerEntity());
    }

    public static GamerEntity toGamerEntity(final Gamer gamer, final GamerEntity gamerEntity) {
        gamerEntity.setId(gamer.getId());
        gamerEntity.setDateOfBirth(gamer.getDateOfBirth());
        gamerEntity.setDisplayName(gamer.getDisplayName());
        String emailAddress = gamer.getEmailAddress();
        if(gamer.getEmailAddress() != null) {
            emailAddress = emailAddress.toLowerCase();
        }
        gamerEntity.setEmailAddress(emailAddress);
        gamerEntity.setIntroductionText(gamer.getIntroductionText());
        gamerEntity.setProfileImage(gamer.getProfileImage());
        gamerEntity.setTelephoneNumber(gamer.getTelephoneNumber());
        gamerEntity.setPassword(gamer.getPassword());

        return gamerEntity;
    }

}
