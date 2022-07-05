package com.gameder.service;


import com.gameder.api.Gamer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GamerServiceIT {

    private final Logger log = LoggerFactory.getLogger(GamerServiceIT.class);

    @Autowired
    private GamerService gamerService;

    @Test
    public void testCreateGamer() {
        log.info("testCreateGamer");

        final Gamer returnedGamer = createGamer();

        log.info("testCreateGamerResponse {}", returnedGamer );
    }

    private Gamer createGamer() {
        final Gamer gamer = new Gamer(null,"NewGamer", new Date(1656366879731L));

        final Gamer returnedGamer = gamerService.createGamer(gamer);

        assertNotNull(returnedGamer.getId());
        assertEquals(returnedGamer.getId(), returnedGamer.getId());
        assertEquals(returnedGamer.getDateOfBirth(), returnedGamer.getDateOfBirth());
        assertEquals(returnedGamer.getDisplayName(), returnedGamer.getDisplayName());
        return returnedGamer;
    }

    @Test
    public void testUpdateGamer() {
        log.info("testUpdateGamer");

        final Gamer createdGamer = createGamer();

        final Gamer gamer = new Gamer(createdGamer.getId(),"NewGamerUpdated", new Date(1656366879731L));

        final Gamer returnedGamer = gamerService.updateGamer(gamer);

        assertNotNull(returnedGamer.getId());
        assertEquals(returnedGamer.getId(), returnedGamer.getId());
        assertEquals(returnedGamer.getDateOfBirth(), returnedGamer.getDateOfBirth());
        assertEquals(returnedGamer.getDisplayName(), returnedGamer.getDisplayName());

        log.info("testUpdateGamer {}", returnedGamer);
    }

    @Test
    public void testUpdateGamerNotExists() {
        log.info("testUpdateGamer");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L));

        try {
            gamerService.updateGamer(gamer);

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testUpdateGamerNotExists");
    }

    @Test
    public void testRetrieveGamer() {
        log.info("testRetrieveGamer");

        final Gamer createdGamer = createGamer();

        final Gamer returnedGamer = gamerService.retrieveGamer(createdGamer.getId());

        assertNotNull(returnedGamer.getId());

        log.info("testRetrieveGamer {}", returnedGamer);
    }

    @Test
    public void testRetrieveGamerNotFound() {
        log.info("testRetrieveGamer");

        try {
            gamerService.retrieveGamer("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testRetrieveGamer ");
    }

    @Test
    public void testArchiveGamer() {
        log.info("testArchiveGamer");

        final Gamer createdGamer = createGamer();

        gamerService.archiveGamer(createdGamer.getId());

        log.info("testArchiveGamer");
    }

    @Test
    public void testArchiveGamerNotFound() {
        log.info("testArchiveGamer");

        try {
            gamerService.archiveGamer("1");

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (EntityNotFoundException exception) {
            log.info("EntityNotFoundException thrown as expected" );
        }

        log.info("testArchiveGamerNotFound");
    }
}
