package com.gameder.service.gamer;

import com.gameder.api.Gamer;
import com.gameder.converter.GamerGamerEntityConverter;
import com.gameder.domain.GamerEntity;
import com.gameder.repository.GamerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class GamerServiceImpl extends GamerServiceBase {

    private static final Logger log = LoggerFactory.getLogger(GamerServiceImpl.class);

    @Autowired
    public GamerServiceImpl(final GamerRepository gamerRepository) {
        super(gamerRepository);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Gamer createGamer(final Gamer gamer) {
        log.info("Entering createGamer: {}",gamer);

        final GamerEntity gamerEntity = GamerGamerEntityConverter.toGamerEntity(gamer);

        final GamerEntity createdEntity = getGamerRepository().save(gamerEntity);

        final Gamer gamerResponse = GamerGamerEntityConverter.toGamer(createdEntity);

        log.info("Exiting createGamer: {}" , gamerResponse);

        return gamerResponse;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Gamer retrieveGamer(String identifier) {
        log.info("Entering retrieveGamer: {}",identifier);

        final GamerEntity gamerEntity = getGamerRepository().findById(identifier).orElseThrow(EntityNotFoundException::new);

        final Gamer gamerResponse = GamerGamerEntityConverter.toGamer(gamerEntity);

        log.info("Exiting retrieveGamer: {}" , gamerResponse);

        return gamerResponse;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Gamer updateGamer(final Gamer gamer) {
        log.info("Entering updateGamer: {}",gamer);

        final GamerEntity foundGamerEntity = getGamerRepository().findById(gamer.getId()).orElseThrow(EntityNotFoundException::new);

        final GamerEntity updateGamer = GamerGamerEntityConverter.toGamerEntity(gamer, foundGamerEntity);

        final GamerEntity createdEntity = getGamerRepository().save(updateGamer);

        final Gamer gamerResponse = GamerGamerEntityConverter.toGamer(createdEntity);

        log.info("Exiting updateGamer: {}" , gamerResponse);

        return gamer;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void archiveGamer(String identifier) {
        log.info("Entering archiveGamer: {}",identifier);

        final GamerEntity foundGamerEntity = getGamerRepository().findById(identifier).orElseThrow(EntityNotFoundException::new);

        foundGamerEntity.setArchived(true);

        getGamerRepository().save(foundGamerEntity);

        log.info("Exiting archiveGame" );
    }
}
