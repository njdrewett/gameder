package com.gameder.service;


import com.gameder.api.Gamer;
import com.gameder.api.GamerCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
        final Gamer gamer = new Gamer(null,"NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

        final Gamer returnedGamer = gamerService.createGamer(gamer);

        assertNotNull(returnedGamer.getId());
        assertEquals(returnedGamer.getDateOfBirth(), gamer.getDateOfBirth());
        assertEquals(returnedGamer.getDisplayName(), gamer.getDisplayName());
        assertEquals(returnedGamer.getEmailAddress(), gamer.getEmailAddress());
        assertEquals(returnedGamer.getIntroductionText(), gamer.getIntroductionText());
        assertEquals(returnedGamer.getTelephoneNumber(), gamer.getTelephoneNumber());
        assertEquals(returnedGamer.getPassword(), gamer.getPassword());

        return returnedGamer;
    }

    @Test
    public void testUpdateGamer() {
        log.info("testUpdateGamer");

        final Gamer createdGamer = createGamer();

        final Gamer gamer = new Gamer(createdGamer.getId(),"NewGamerUpdated", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

        final Gamer returnedGamer = gamerService.updateGamer(gamer);

        assertNotNull(returnedGamer.getId());
        assertEquals(returnedGamer.getId(), gamer.getId());
        assertEquals(returnedGamer.getDateOfBirth(), gamer.getDateOfBirth());
        assertEquals(returnedGamer.getDisplayName(), gamer.getDisplayName());
        assertEquals(returnedGamer.getEmailAddress(), gamer.getEmailAddress());
        assertEquals(returnedGamer.getIntroductionText(), gamer.getIntroductionText());
        assertEquals(returnedGamer.getTelephoneNumber(), gamer.getTelephoneNumber());
        assertEquals(returnedGamer.getPassword(), gamer.getPassword());

        log.info("testUpdateGamer {}", returnedGamer);
    }

    @Test
    public void testUpdateGamerNotExists() {
        log.info("testUpdateGamer");

        final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

        try {
            gamerService.updateGamer(gamer);

            fail("EntityNotFoundException NOT thrown as expected");

        } catch (final EntityNotFoundException exception) {
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
    public void testRetrieveAllGamers() {
        log.info("testRetrieveAllGamers");

        final Gamer createdGamer = createGamer();

        final List<Gamer> returnedGamer = gamerService.retrieveAllGamers();

        log.info("Gamers all: " + returnedGamer);

        assertNotNull(returnedGamer);
        assertEquals(1, returnedGamer.size());

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

    @Test
    public void testEmailExists() {
        log.info("testEmailExists");

        final Gamer createdGamer = createGamer();

        final Boolean emailExists = gamerService.emailExists("gamer@gamers.com");

         assertTrue(emailExists);

        log.info("testEmailExists {}", emailExists);
    }

    @Test
    public void testCriteriaFindById() {
        log.info("testCriteriaFindById");

        final Gamer createdGamer = createGamer();

        GamerCriteria gamerCriteria = new GamerCriteria();
        gamerCriteria.setId(createdGamer.getId());

        final List<Gamer> results = gamerService.findGamers(gamerCriteria);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(results.get(0).getId(), createdGamer.getId());

        log.info("Exitting testFindById ");
    }


    @Test
    public void testCriteriaFindByExcludeId() {
        log.info("testCriteriaFindByExcludeId");

        final Gamer createdGamer = createGamer();

        final Gamer excludeCreatedGamer = createGamer();

        GamerCriteria gamerCriteria = new GamerCriteria();
        gamerCriteria.setExcludeId(excludeCreatedGamer.getId());

        final List<Gamer> results = gamerService.findGamers(gamerCriteria);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(results.get(0).getId(), createdGamer.getId());
        assertNotEquals(results.get(0).getId(), excludeCreatedGamer.getId());

        log.info("Exiting testFindById ");
    }


}
