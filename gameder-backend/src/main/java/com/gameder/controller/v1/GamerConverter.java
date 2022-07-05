package com.gameder.controller.v1;

import com.gameder.api.Gamer;
import com.gameder.api.v1.CreateGamerRequest;
import com.gameder.api.v1.GetGamerResponse;
import com.gameder.api.v1.UpdateGamerRequest;

public class GamerConverter {

    public static Gamer toGamer(final CreateGamerRequest createGamerRequest) {
        return new Gamer(null, createGamerRequest.getDisplayName(), createGamerRequest.getDateOfBirth());
    }

    public static Gamer toGamer(final UpdateGamerRequest updateGamerRequest) {
        return new Gamer(updateGamerRequest.getId(), updateGamerRequest.getDisplayName(), updateGamerRequest.getDateOfBirth());
    }

    public static GetGamerResponse toGetGamerResponse(final Gamer gamer) {
        return new GetGamerResponse(gamer.getId(), gamer.getDisplayName(), gamer.getDateOfBirth());
    }

}
