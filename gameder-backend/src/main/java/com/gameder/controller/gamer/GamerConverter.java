package com.gameder.controller.gamer;

import com.gameder.api.Gamer;
import com.gameder.api.gamer.CreateGamerRequest;
import com.gameder.api.gamer.RetrieveGamerResponse;
import com.gameder.api.gamer.UpdateGamerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamerConverter {

    public static Gamer toGamer(final CreateGamerRequest createGamerRequest) {
        return new Gamer(null, createGamerRequest.getDisplayName(), createGamerRequest.getDateOfBirth(),
                createGamerRequest.getEmailAddress(),createGamerRequest.getTelephoneNumber(),
                createGamerRequest.getProfileImage(),createGamerRequest.getIntroductionText(), createGamerRequest.getPassword());
    }

    public static Gamer toGamer(final UpdateGamerRequest updateGamerRequest) {
        return new Gamer(updateGamerRequest.getId(), updateGamerRequest.getDisplayName(),
                updateGamerRequest.getDateOfBirth(),updateGamerRequest.getEmailAddress(),
                updateGamerRequest.getTelephoneNumber(),updateGamerRequest.getProfileImage(),
                updateGamerRequest.getIntroductionText(), updateGamerRequest.getPassword());
    }

    public static RetrieveGamerResponse toRetrieveGamerResponse(final Gamer gamer) {
        return new RetrieveGamerResponse(gamer.getId(), gamer.getDisplayName(), gamer.getDateOfBirth(),
                gamer.getEmailAddress(),gamer.getTelephoneNumber(),gamer.getProfileImage(),gamer.getIntroductionText());
    }

    public static List<RetrieveGamerResponse> toRetrieveGamerResponse(final List<Gamer> gamers) {
       return gamers.stream().map(GamerConverter::toRetrieveGamerResponse).collect(Collectors.toList());
    }

}
