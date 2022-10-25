package com.gameder.controller.gamer;

import com.gameder.api.Gamer;
import com.gameder.api.GamerCriteria;
import com.gameder.api.gamer.CreateGamerRequest;
import com.gameder.api.gamer.FindGamerRequest;
import com.gameder.api.gamer.RetrieveGamerResponse;
import com.gameder.api.gamer.UpdateGamerRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamerConverter {

    public static Gamer toGamer(final CreateGamerRequest createGamerRequest) {
        return new Gamer(null, createGamerRequest.getDisplayName(), createGamerRequest.getDateOfBirth(),
                createGamerRequest.getEmailAddress(),createGamerRequest.getTelephoneNumber(),
                null,createGamerRequest.getIntroductionText(), createGamerRequest.getPassword());
    }

    public static Gamer toGamer(final UpdateGamerRequest updateGamerRequest, byte[] profileImageData, String profileImageContentType) {
        return new Gamer(updateGamerRequest.getId(), updateGamerRequest.getDisplayName(),
                updateGamerRequest.getDateOfBirth(),updateGamerRequest.getEmailAddress(),
                updateGamerRequest.getTelephoneNumber(),profileImageData,profileImageContentType,
                updateGamerRequest.getIntroductionText());
    }

    public static RetrieveGamerResponse toRetrieveGamerResponse(final Gamer gamer) {
        return new RetrieveGamerResponse(gamer.getId(), gamer.getDisplayName(), gamer.getDateOfBirth(),
                gamer.getEmailAddress(),gamer.getTelephoneNumber(),gamer.getIntroductionText());
    }

    public static List<RetrieveGamerResponse> toRetrieveGamerResponse(final List<Gamer> gamers) {
       return gamers.stream().map(GamerConverter::toRetrieveGamerResponse).collect(Collectors.toList());
    }

    public static GamerCriteria toGamerCriteria(final FindGamerRequest findGamerRequest) {
        return new GamerCriteria(findGamerRequest.getId(),findGamerRequest.getDisplayName(),findGamerRequest.getDateOfBirth(),
                findGamerRequest.getEmailAddress(), findGamerRequest.getTelephoneNumber(),findGamerRequest.getExcludeId());
    }

}
