package com.gameder.converter;

import com.gameder.api.Gamer;
import com.gameder.domain.GamerEntity;
import com.gameder.service.GamerServiceIT;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Gamer To Entity test
 *
 * @todo This should more ideally fail if new attributes are added to entity or gamer.
 */
public class GamerGamerEntityConverterTest {

    private final Logger log = LoggerFactory.getLogger(GamerGamerEntityConverterTest.class);

    @Test
    public void toGamerTest() {
        GamerEntity gamerEntity = createGamerEntity();
        log.info("toGamerTest {}", gamerEntity);

        Gamer gamer = GamerGamerEntityConverter.toGamer(gamerEntity);


        assertNotNull(gamer.getId());
        assertEquals(gamer.getId(), gamerEntity.getId());
        assertEquals(gamer.getDateOfBirth(), gamerEntity.getDateOfBirth());
        assertEquals(gamer.getDisplayName(), gamerEntity.getDisplayName());

        log.info("toGamerTest {}", gamer);
    }

    @Test
    public void toGamerEntityTest() {
        Gamer gamer = createGamer();
        log.info("toGamerTest {}", gamer);

        GamerEntity gamerEntity = GamerGamerEntityConverter.toGamerEntity(gamer);

        assertNotNull(gamerEntity.getId());
        assertEquals(gamerEntity.getId(), gamer.getId());
        assertEquals(gamerEntity.getDateOfBirth(), gamer.getDateOfBirth());
        assertEquals(gamerEntity.getDisplayName(), gamer.getDisplayName());

        log.info("toGamerTest {}", gamerEntity);
    }

    private GamerEntity createGamerEntity() {
        GamerEntity gamerEntity = new GamerEntity();
        gamerEntity.setId("1");
        gamerEntity.setDateOfBirth(new Date(1234567890l));
        gamerEntity.setDisplayName("Test Gamer");
        return gamerEntity;
    }

    private Gamer createGamer() {
        Gamer gamer = new Gamer();
        gamer.setId("1");
        gamer.setDateOfBirth(new Date(1234567890l));
        gamer.setDisplayName("Test Gamer");
        return gamer;
    }

}
