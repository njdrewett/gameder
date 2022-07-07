package com.gameder.controller.v1;

import com.gameder.api.Gamer;
import com.gameder.api.v1.*;
import com.gameder.service.GamerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/gamer")
public class GamerControllerImpl implements GamerController {

    private static final Logger log = LoggerFactory.getLogger(GamerControllerImpl.class);

    private final GamerService gamerService;

    @Autowired
    GamerControllerImpl(final GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @PostMapping
    public ResponseEntity<CreateGamerResponse> createGamer(@RequestBody final CreateGamerRequest createGamerRequest) {
        log.info("createGamer {}" , createGamerRequest);

        final Gamer gamer = GamerConverter.toGamer(createGamerRequest);
        final Gamer createResponse = gamerService.createGamer(gamer);

        final CreateGamerResponse response = new CreateGamerResponse(createResponse.getId(), Boolean.TRUE);

        log.info("Response: {}" , response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path="{gamerId}")
    public ResponseEntity<GetGamerResponse> retrieveGamer(@PathVariable("gamerId") String gamerId) {
        log.info("retrieveGamer {} ", gamerId);

        final Gamer gamer = gamerService.retrieveGamer(gamerId);
        final GetGamerResponse getGamerResponse = GamerConverter.toGetGamerResponse(gamer);

        log.info("retrieveGamer {}" , getGamerResponse);
        return new ResponseEntity<>(getGamerResponse, HttpStatus.OK);
    }

    @PostMapping(path="/update")
    public ResponseEntity<UpdateGamerResponse> updateGamer(@RequestBody UpdateGamerRequest updateGamerRequest) {
        log.info("updateGamer {}", updateGamerRequest);

        final Gamer gamer = GamerConverter.toGamer(updateGamerRequest);
        final Gamer gamerResponse = gamerService.updateGamer(gamer);
        final UpdateGamerResponse updateGamerResponse = new UpdateGamerResponse(gamerResponse.getId(), true);

        log.info("updateGamer {}", updateGamerResponse);
        return new ResponseEntity<>(updateGamerResponse, HttpStatus.OK);
    }

    @DeleteMapping(path="{gamerId}")
    public void archiveGamer(@PathVariable("gamerId") String gamerId) {
        log.info("archiveGamer {} ", gamerId);

        gamerService.archiveGamer(gamerId);

        log.info("archiveGamer  ");
    }


}
