package com.gameder.converter;

import com.gameder.api.Gamer;
import com.gameder.domain.GamerEntity;
import com.gameder.service.GamerServiceIT;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Gamer To Entity test
 *
 */
public class GamerGamerEntityConverterTest {

    private final Logger log = LoggerFactory.getLogger(GamerGamerEntityConverterTest.class);

    @Test
    public void toGamerTest() {
        GamerEntity gamerEntity = createGamerEntity();
        log.info("toGamerTest {}", gamerEntity);

        final Gamer gamer = GamerGamerEntityConverter.toGamer(gamerEntity);

        assertNotNull(gamer.getId());
        assertEquals(gamer.getId(), gamerEntity.getId());
        assertEquals(gamer.getDateOfBirth(), gamerEntity.getDateOfBirth());
        assertEquals(gamer.getDisplayName(), gamerEntity.getDisplayName());
        assertEquals(gamer.getEmailAddress(), gamerEntity.getEmailAddress());
        assertEquals(gamer.getIntroductionText(), gamerEntity.getIntroductionText());
        assertEquals(gamer.getTelephoneNumber(), gamerEntity.getTelephoneNumber());

        log.info("toGamerTest {}", gamer);
    }

    @Test
    public void toGamerListTest() {
        GamerEntity gamerEntity = createGamerEntity();
        log.info("toGamerTest {}", gamerEntity);

        List<GamerEntity> gamerEntities = Collections.singletonList(gamerEntity);

        final List<Gamer> allGamers = GamerGamerEntityConverter.toGamer(gamerEntities);

        assertTrue( allGamers.size() == 1,"List must have one element");
        Gamer gamer = allGamers.get(0);
        assertNotNull(gamer.getId());
        assertEquals(gamer.getId(), gamerEntity.getId());
        assertEquals(gamer.getDateOfBirth(), gamerEntity.getDateOfBirth());
        assertEquals(gamer.getDisplayName(), gamerEntity.getDisplayName());
        assertEquals(gamer.getEmailAddress(), gamerEntity.getEmailAddress());
        assertEquals(gamer.getIntroductionText(), gamerEntity.getIntroductionText());
        assertEquals(gamer.getTelephoneNumber(), gamerEntity.getTelephoneNumber());

        log.info("toGamerTest {}", gamer);
    }


    @Test
    public void toGamerEntityTest() {
        Gamer gamer = createGamer();
        log.info("toGamerTest {}", gamer);

        final GamerEntity gamerEntity = GamerGamerEntityConverter.toGamerEntity(gamer);

        assertNotNull(gamerEntity.getId());
        assertEquals(gamerEntity.getId(), gamer.getId());
        assertEquals(gamerEntity.getDateOfBirth(), gamer.getDateOfBirth());
        assertEquals(gamerEntity.getDisplayName(), gamer.getDisplayName());
        assertEquals(gamerEntity.getEmailAddress(), gamer.getEmailAddress());
        assertEquals(gamerEntity.getIntroductionText(), gamer.getIntroductionText());
        assertEquals(gamerEntity.getTelephoneNumber(), gamer.getTelephoneNumber());

        log.info("toGamerTest {}", gamerEntity);
    }

    private GamerEntity createGamerEntity() {
        final GamerEntity gamerEntity = new GamerEntity();
        gamerEntity.setId("1");
        gamerEntity.setDateOfBirth(new Date(1234567890L));
        gamerEntity.setDisplayName("Test Gamer");
        gamerEntity.setTelephoneNumber("12234557890");
        gamerEntity.setIntroductionText("Hi Im A Gemer");
        gamerEntity.setEmailAddress("gamer@gamers.com");

        return gamerEntity;
    }

    private Gamer createGamer() {
        final Gamer gamer = new Gamer();
        gamer.setId("1");
        gamer.setDateOfBirth(new Date(1234567890L));
        gamer.setDisplayName("Test Gamer");
        gamer.setTelephoneNumber("12234557890");
        gamer.setIntroductionText("Hi Im A Gemer");
        gamer.setEmailAddress("gamer@gamers.com");
        return gamer;
    }

}
