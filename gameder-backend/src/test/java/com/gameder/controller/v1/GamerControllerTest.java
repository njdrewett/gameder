package com.gameder.controller.v1;

import com.gameder.api.Gamer;
import com.gameder.api.v1.*;
import com.gameder.service.GamerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Gamer unit Test
 */
@ExtendWith(MockitoExtension.class)
public class GamerControllerTest {

    private Logger log = LoggerFactory.getLogger(GamerControllerTest.class);

    GamerController gamerController;

    @Mock
    private GamerService gamerService;

    @BeforeEach
    public void init() {
        gamerController = new GamerControllerImpl(gamerService);
    }

    @Test
    public void testCreateGamer() {
        log.info("testCreateGamer");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L));
        Mockito.when(gamerService.createGamer(Mockito.any(Gamer.class))).thenReturn(gamer);

        final CreateGamerRequest createGamerRequest = new CreateGamerRequest("NewGamer", new Date(1656366879731L));
        final ResponseEntity<CreateGamerResponse> returnedGamer = gamerController.createGamer(createGamerRequest);

        final CreateGamerResponse body = returnedGamer.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), gamer.getId());

        log.info("testCreateGamerResponse {}", returnedGamer );
    }

    @Test
    public void testUpdateGamer() {
        log.info("testUpdateGamer");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L));
        Mockito.when(gamerService.updateGamer(Mockito.any(Gamer.class))).thenReturn(gamer);

        final UpdateGamerRequest updateGamerRequest = new UpdateGamerRequest("1", "NewGamer", new Date(1656366879731L));
        final ResponseEntity<UpdateGamerResponse> returnedGamer = gamerController.updateGamer(updateGamerRequest);

        final UpdateGamerResponse body = returnedGamer.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), gamer.getId());

        log.info("testUpdateGamer {}", returnedGamer);
    }

    @Test
    public void testUpdateGamerNotExists() {
        log.info("testUpdateGamerNotExists");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L));
        Mockito.when(gamerService.updateGamer(Mockito.any(Gamer.class))).thenReturn(gamer);

        final UpdateGamerRequest updateGamerRequest = new UpdateGamerRequest("2", "NewGamer", new Date(1656366879731L));
        final ResponseEntity<UpdateGamerResponse> returnedGamer = gamerController.updateGamer(updateGamerRequest);

        final UpdateGamerResponse body = returnedGamer.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), gamer.getId());

        log.info("testUpdateGamerNotExists");
    }

    @Test
    public void testRetrieveGamer() {
        log.info("testRetrieveGamer");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L));

        Mockito.when(gamerService.retrieveGamer("1")).thenReturn(gamer);

        final ResponseEntity<GetGamerResponse> returnedGamer = gamerController.retrieveGamer("1");

        assertNotNull(returnedGamer.getBody().getId());

        log.info("testRetrieveGamer {}", returnedGamer);
    }

    @Test
    public void testRetrieveGamerNotFound() {
        log.info("testRetrieveGamer");

        Mockito.when(gamerService.retrieveGamer("1")).thenThrow(new EntityNotFoundException());

        try {
            gamerController.retrieveGamer("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testRetrieveGamer ");
    }

    @Test
    public void testArchiveGamer() {
        log.info("testArchiveGamer");

        Mockito.doNothing().when(gamerService).archiveGamer("1");

        gamerService.archiveGamer("1");

        log.info("testArchiveGamer");
    }

    @Test
    public void testArchiveGamerNotFound() {
        log.info("testArchiveGamer");

        Mockito.doThrow(new EntityNotFoundException()).when(gamerService).archiveGamer("1");

        try {
            gamerService.archiveGamer("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveGamerNotFound");
    }
}
