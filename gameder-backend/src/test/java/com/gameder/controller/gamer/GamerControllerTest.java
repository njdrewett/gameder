package com.gameder.controller.gamer;

import com.gameder.api.Gamer;
import com.gameder.api.GamerCriteria;
import com.gameder.api.gamer.*;
import com.gameder.service.GamerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Gamer unit Test
 */
@ExtendWith(MockitoExtension.class)
public class GamerControllerTest {

    private static final Logger log = LoggerFactory.getLogger(GamerControllerTest.class);

    private GamerController gamerController;

    @Mock
    private GamerService gamerService;

    @BeforeEach
    public void init() {
        gamerController = new GamerControllerImpl(gamerService);
    }

    @Test
    public void testCreateGamer() {
        log.info("testCreateGamer");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        Mockito.when(gamerService.createGamer(Mockito.any(Gamer.class))).thenReturn(gamer);

        final CreateGamerRequest createGamerRequest = new CreateGamerRequest("NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        final ResponseEntity<CreateGamerResponse> returnedGamer = gamerController.createGamer(createGamerRequest);

        final CreateGamerResponse body = returnedGamer.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), gamer.getId());

        log.info("testCreateGamerResponse {}", returnedGamer );
    }

    @Test
    public void testUpdateGamer() {
        log.info("testUpdateGamer");
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        Mockito.when(gamerService.updateGamer(Mockito.any(Gamer.class))).thenReturn(gamer);

        final UpdateGamerRequest updateGamerRequest = new UpdateGamerRequest("1", "NewGamer",
                new Date(1656366879731L),"gamer@gamers.com", "019191999991911",
                null, "Hi Im a gamer", "password");
        final ResponseEntity<UpdateGamerResponse> returnedGamer = gamerController.updateGamer(updateGamerRequest, file);

        final UpdateGamerResponse body = returnedGamer.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), gamer.getId());

        log.info("testUpdateGamer {}", returnedGamer);
    }

    @Test
    public void testUpdateGamerNotExists() {
        log.info("testUpdateGamerNotExists");

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        Mockito.when(gamerService.updateGamer(Mockito.any(Gamer.class))).thenReturn(gamer);

        final UpdateGamerRequest updateGamerRequest = new UpdateGamerRequest("2", "NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        final ResponseEntity<UpdateGamerResponse> returnedGamer = gamerController.updateGamer(updateGamerRequest, file);

        final UpdateGamerResponse body = returnedGamer.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), gamer.getId());

        log.info("testUpdateGamerNotExists");
    }

    @Test
    public void testRetrieveGamer() {
        log.info("testRetrieveGamer");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null,
                "Hi Im a gamer", "password");

        Mockito.when(gamerService.retrieveGamer("1")).thenReturn(gamer);

        final ResponseEntity<RetrieveGamerResponse> returnedGamer = gamerController.retrieveGamer("1");

        assertNotNull(returnedGamer.getBody().getId());

        log.info("testRetrieveGamer {}", returnedGamer);
    }

    @Test
    public void testRetrieveAllGamers() {
        log.info("testRetrieveAllGamers");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null,
                "Hi Im a gamer", "password");

        Mockito.when(gamerService.retrieveAllGamers()).thenReturn(Collections.singletonList(gamer));

        final ResponseEntity<List<RetrieveGamerResponse>> returnedGamer = gamerController.retrieveAllGamers();

        assertNotNull(returnedGamer.getBody());

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

        gamerController.archiveGamer("1");

        log.info("testArchiveGamer");
    }

    @Test
    public void testEmailExists() {
        log.info("testEmailExists");

        Mockito.when(gamerService.emailExists("test@emailAddress.com")).thenReturn(Boolean.TRUE);

        ResponseEntity<Boolean> result = gamerController.emailExists("test@emailAddress.com");

        final Boolean body = result.getBody();
        assertTrue(body);

        log.info("testEmailExists");
    }


    @Test
    public void testCriteriaFindById() {
        log.info("testCriteriaFindById");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null,
                "Hi Im a gamer", "password");

        FindGamerRequest findGamerRequest = new FindGamerRequest();
        findGamerRequest.setId(gamer.getId());

        GamerCriteria gamerCriteria = new GamerCriteria();
        gamerCriteria.setId(gamer.getId());

        Mockito.when(gamerService.findGamers(gamerCriteria)).thenReturn(Collections.singletonList(gamer));

        final ResponseEntity<List<RetrieveGamerResponse>> results = gamerController.findGamer(findGamerRequest);

        assertNotNull(results);
        assertEquals(HttpStatus.OK, results.getStatusCode());
        assertEquals(results.getBody().get(0).getId(), gamer.getId());

        log.info("Exitting testFindById ");
    }
    @Test
    public void testCriteriaFindByExcludeId() {
        log.info("testCriteriaFindByExcludeId");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null,
                "Hi Im a gamer", "password");

        final Gamer excludeGamer = new Gamer("2","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null,
                "Hi Im a gamer", "password");

        final FindGamerRequest findGamerRequest = new FindGamerRequest();
        findGamerRequest.setExcludeId(excludeGamer.getId());

        final GamerCriteria gamerCriteria = new GamerCriteria();
        gamerCriteria.setExcludeId(excludeGamer.getId());

        Mockito.when(gamerService.findGamers(gamerCriteria)).thenReturn(Collections.singletonList(gamer));

        final ResponseEntity<List<RetrieveGamerResponse>> results = gamerController.findGamer(findGamerRequest);

        assertNotNull(results);
        assertEquals(HttpStatus.OK, results.getStatusCode());
        assertEquals(results.getBody().get(0).getId(), gamer.getId());
        assertNotEquals(results.getBody().get(0).getId(), excludeGamer.getId());

        log.info("Exiting testCriteriaFindByExcludeId ");
    }

}
