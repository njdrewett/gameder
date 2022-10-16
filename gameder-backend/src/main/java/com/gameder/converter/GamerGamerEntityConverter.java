package com.gameder.converter;

import com.gameder.api.Gamer;
import com.gameder.controller.gamer.GamerControllerImpl;
import com.gameder.domain.GamerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GamerGamerEntityConverter {

    private static final Logger log = LoggerFactory.getLogger(GamerGamerEntityConverter.class);

    public static Gamer toGamer(final GamerEntity gamerEntity) {
        return new Gamer(gamerEntity.getId(), gamerEntity.getDisplayName(),gamerEntity.getDateOfBirth(),
                gamerEntity.getEmailAddress(),gamerEntity.getTelephoneNumber(),gamerEntity.getProfileImage(), gamerEntity.getProfileContentType(), gamerEntity.getIntroductionText(),gamerEntity.getPassword());
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
        if(StringUtils.hasLength(emailAddress)) {
            gamer.setEmailAddress(emailAddress.toLowerCase());
            gamerEntity.setEmailAddress(emailAddress);
        }

        gamerEntity.setIntroductionText(gamer.getIntroductionText());
        gamerEntity.setTelephoneNumber(gamer.getTelephoneNumber());
        if(StringUtils.hasLength(gamer.getPassword())) {
            gamerEntity.setPassword(gamer.getPassword());
        }
        if(gamer.getProfileImageData() != null) {
            gamerEntity.setProfileContentType(gamer.getProfileImageContentType());
            gamerEntity.setProfileImage(gamer.getProfileImageData());
        }

        return gamerEntity;
    }

}
