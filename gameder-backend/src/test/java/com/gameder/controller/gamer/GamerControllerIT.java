package com.gameder.controller.gamer;

import com.gameder.api.gamer.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Gamer unit Test
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GamerControllerIT {

    private final Logger log = LoggerFactory.getLogger(GamerControllerIT.class);

    @Autowired
    private GamerController gamerController;

    @Test
    public void testCreateGamer() {
        log.info("testCreateGamer");

        final ResponseEntity<CreateGamerResponse> returnedGamer = createGamer();
        assertNotNull(returnedGamer.getBody().getId());
        assertTrue(returnedGamer.getBody().getSuccess());

        log.info("testCreateGamerResponse {}", returnedGamer );
    }

    @Test
    public void testCreateGamerMinimalData() {
        log.info("testCreateGamer");

        final CreateGamerRequest minimalGamer = new CreateGamerRequest();
        minimalGamer.setEmailAddress("testGamerEmail@mail.com");
        minimalGamer.setPassword("password");

        final ResponseEntity<CreateGamerResponse> returnedGamer = gamerController.createGamer(minimalGamer);

        assertNotNull(returnedGamer.getBody().getId());
        assertTrue(returnedGamer.getBody().getSuccess());

        log.info("testCreateGamerResponse {}", returnedGamer );
    }


    private ResponseEntity<CreateGamerResponse> createGamer() {
        final CreateGamerRequest createGamerRequest = new CreateGamerRequest("NewGamer", new Date(1656366879731L)
                ,"gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        final ResponseEntity<CreateGamerResponse> returnedGamer = gamerController.createGamer(createGamerRequest);

        final CreateGamerResponse body = returnedGamer.getBody();
        assertTrue(body.getSuccess());
        return returnedGamer;
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

        final ResponseEntity<CreateGamerResponse> returnedGamer = createGamer();
        final UpdateGamerRequest updateGamerRequest =
                new UpdateGamerRequest(returnedGamer.getBody().getId(), "NewGamer", new Date(1656366879731L),
                        "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        final ResponseEntity<UpdateGamerResponse> returnedUpdateGamer = gamerController.updateGamer(updateGamerRequest, file);

        final UpdateGamerResponse body = returnedUpdateGamer.getBody();
        assertTrue(body.getSuccess());
        assertEquals(body.getId(), returnedGamer.getBody().getId());

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

        final UpdateGamerRequest updateGamerRequest = new UpdateGamerRequest("2", "NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
        try {
            gamerController.updateGamer(updateGamerRequest,file);
            fail("EntityNotFoundException should have been thrown ");
        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testUpdateGamerNotExists");
    }

    @Test
    public void testRetrieveGamer() {
        log.info("testRetrieveGamer");

        final ResponseEntity<CreateGamerResponse> returnedGamer = createGamer();

        final ResponseEntity<RetrieveGamerResponse> getGamerResponse = gamerController.retrieveGamer(returnedGamer.getBody().getId());

        assertNotNull(getGamerResponse.getBody().getId());

        log.info("testRetrieveGamer {}", getGamerResponse);
    }

    @Test
    public void testRetrieveAllGamers() {
        log.info("testRetrieveGamer");

        createGamer();

        final ResponseEntity<List<RetrieveGamerResponse>> getAllGamersResponse = gamerController.retrieveAllGamers();

        assertNotNull(getAllGamersResponse.getBody());

        log.info("testRetrieveGamer {}", getAllGamersResponse);
    }

    @Test
    public void testRetrieveGamerNotFound() {
        log.info("testRetrieveGamer");

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

        final ResponseEntity<CreateGamerResponse> returnedGamer = createGamer();

        gamerController.archiveGamer(returnedGamer.getBody().getId());

        log.info("testArchiveGamer");
    }

    @Test
    public void testArchiveGamerNotFound() {
        log.info("testArchiveGamer");

        try {
            gamerController.archiveGamer("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveGamerNotFound");
    }

    @Test
    public void testEmailExists() {
        log.info("testEmailExists");

        final ResponseEntity<CreateGamerResponse> returnedGamer = createGamer();

        final ResponseEntity<Boolean> result = gamerController.emailExists("gamer@gamers.com");

        final Boolean body = result.getBody();
        assertTrue(body);

        log.info("testEmailExists");
    }

//    @Test
//    public void testLogin() {
//        log.info("testLogin");
//
//        final ResponseEntity<CreateGamerResponse> returnedGamer = createGamer();
//
//        final ResponseEntity<Boolean> result = gamerController.login("gamer@gamers.com", "password");
//
//        final Boolean body = result.getBody();
//        assertTrue(body);
//
//        log.info("testLogin");
//    }
}
