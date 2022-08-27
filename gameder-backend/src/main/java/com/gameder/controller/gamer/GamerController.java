package com.gameder.controller.gamer;

import com.gameder.api.gamer.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GamerController {

    ResponseEntity<CreateGamerResponse> createGamer(final CreateGamerRequest createGamerRequest);

    ResponseEntity<RetrieveGamerResponse> retrieveGamer(final String gamerId);

    ResponseEntity<UpdateGamerResponse> updateGamer(final UpdateGamerRequest updateGamerRequest);

    ResponseEntity<List<RetrieveGamerResponse>> retrieveAllGamers();

    void archiveGamer(String gamerId);

    ResponseEntity<Boolean> emailExists(final String emailAddress);


}
