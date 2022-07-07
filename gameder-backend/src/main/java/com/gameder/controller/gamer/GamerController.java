package com.gameder.controller.gamer;

import com.gameder.api.gamer.*;
import org.springframework.http.ResponseEntity;

public interface GamerController {

    ResponseEntity<CreateGamerResponse> createGamer(final CreateGamerRequest createGamerRequest);

    ResponseEntity<RetrieveGamerResponse> retrieveGamer(final String gamerId);

    ResponseEntity<UpdateGamerResponse> updateGamer(final UpdateGamerRequest updateGamerRequest);

    void archiveGamer(String gamerId) ;
}
