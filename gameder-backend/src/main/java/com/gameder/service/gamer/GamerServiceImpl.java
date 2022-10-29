package com.gameder.service.gamer;

import com.gameder.api.Gamer;
import com.gameder.api.GamerCriteria;
import com.gameder.converter.GamerGamerEntityConverter;
import com.gameder.domain.GamerEntity;
import com.gameder.repository.GamerRepository;
import com.gameder.repository.GamerRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GamerServiceImpl extends GamerServiceBase {

    private static final Logger log = LoggerFactory.getLogger(GamerServiceImpl.class);

    @Autowired
    public GamerServiceImpl(final GamerRepository gamerRepository,
                            final GamerRepositoryCustom gamerRepositoryCustom) {
        super(gamerRepository, gamerRepositoryCustom);
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

        final GamerEntity gamerEntity = getGamerRepository().findById(identifier).orElseThrow(() -> new EntityNotFoundException("Gamer: " + identifier));

        final Gamer gamerResponse = GamerGamerEntityConverter.toGamer(gamerEntity);

        log.info("Exiting retrieveGamer: {}" , gamerResponse);

        return gamerResponse;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Gamer> retrieveAllGamers() {
        log.info("Entering retrieveAllGamers");

        final List<GamerEntity> gamerEntities = getGamerRepository().findAll();

        final List<Gamer> gamerResponse = GamerGamerEntityConverter.toGamer(gamerEntities);

        log.info("Exiting retrieveAllGamers: ");

        return gamerResponse;

    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Gamer updateGamer(final Gamer gamer) {
        log.info("Entering updateGamer: {}",gamer);

        final GamerEntity foundGamerEntity = getGamerRepository().findById(gamer.getId()).orElseThrow(() -> new EntityNotFoundException("Gamer: " + gamer.getId()));

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

        // Should change all finders and dao methods to only return non-archived entities.
        final GamerEntity foundGamerEntity = getGamerRepository().findById(identifier).orElseThrow(() -> new EntityNotFoundException("Gamer: " + identifier));

        foundGamerEntity.setArchived(true);

        getGamerRepository().save(foundGamerEntity);

        log.info("Exiting archiveGame" );
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Boolean emailExists(final String emailAddress) {
        log.info("Entering emailExists: {}",emailAddress);

        final List<GamerEntity> gamerEntities = getGamerRepository().findByEmailAddress(emailAddress);

        final Boolean emailExists = !gamerEntities.isEmpty();

        log.info("Exiting retrieveGamer: {}" , emailExists);

        return emailExists;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Gamer> findGamers(final GamerCriteria gamerCriteria) {
        log.info("Entering findGamers: {}", gamerCriteria);

        final List<GamerEntity> gamerEntities = getGamerRepositoryCustom().findByCriteria(gamerCriteria);

        final List<Gamer> gamerResponse = GamerGamerEntityConverter.toGamer(gamerEntities);

        log.info("Exiting findGamers: {}" , gamerResponse);

        return gamerResponse;
    }

}
