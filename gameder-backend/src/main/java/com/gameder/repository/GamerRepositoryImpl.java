package com.gameder.repository;

import com.gameder.api.Gamer;
import com.gameder.api.GamerCriteria;
import com.gameder.controller.message.MessageControllerImpl;
import com.gameder.domain.GamerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.lang.model.UnknownEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GamerRepositoryImpl implements GamerRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(MessageControllerImpl.class);

    private final EntityManager entityManager;

    @Autowired
    GamerRepositoryImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<GamerEntity> findByCriteria(final GamerCriteria gamerCriteria) {
        log.debug("Entering findByCriteria {}:",gamerCriteria );

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<GamerEntity> criteriaQuery = criteriaBuilder.createQuery(GamerEntity.class);

        final Root<GamerEntity> gamer = criteriaQuery.from(GamerEntity.class);
        final List<Predicate> predicates = new ArrayList<>();

        if (gamerCriteria.getDateOfBirth() != null) {
            predicates.add(criteriaBuilder.equal(gamer.get("dateOfBirth"), gamerCriteria.getDateOfBirth()));
        }
        if (gamerCriteria.getDisplayName() != null) {
            predicates.add(criteriaBuilder.equal(gamer.get("displayName"), gamerCriteria.getDisplayName()));
        }
        if (gamerCriteria.getEmailAddress() != null) {
            predicates.add(criteriaBuilder.equal(gamer.get("emailAddress"), gamerCriteria.getEmailAddress()));
        }
        if (gamerCriteria.getId() != null) {
            predicates.add(criteriaBuilder.equal(gamer.get("id"), gamerCriteria.getId()));
        }
        if (gamerCriteria.getTelephoneNumber() != null) {
            predicates.add(criteriaBuilder.equal(gamer.get("telaphoneNumber"), gamerCriteria.getTelephoneNumber()));
        }
        if (gamerCriteria.getExcludeId() != null) {
            predicates.add(criteriaBuilder.notEqual(gamer.get("id"), gamerCriteria.getExcludeId()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        final List<GamerEntity> gamerEntities = entityManager.createQuery(criteriaQuery).getResultList();

        log.debug("Exiting findByCriteria {}" , gamerEntities);

        return  gamerEntities;
    }

    @Override
    public GamerEntity findByReference(final String id) {
        log.debug("Entering findByReference: {}" , id);

        final GamerEntity gamerEntity = entityManager.getReference(GamerEntity.class, id);

        if(gamerEntity == null) {
            throw new EntityNotFoundException("GamerEntity: " + id);
        }

        log.debug("Exiting findByReference: {}" , id);

        return gamerEntity;
    }
}
