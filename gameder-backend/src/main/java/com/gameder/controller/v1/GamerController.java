package com.gameder.controller.v1;

import com.gameder.api.v1.*;
import org.springframework.http.ResponseEntity;

public interface GamerController {

    ResponseEntity<CreateGamerResponse> createGamer(final CreateGamerRequest createGamerRequest);

    ResponseEntity<GetGamerResponse> retrieveGamer(final String gamerId);

    ResponseEntity<UpdateGamerResponse> updateGamer(final UpdateGamerRequest updateCustomerRequest);

    void archiveGamer(String gamerId) ;
}
