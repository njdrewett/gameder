package com.gameder.repository;

import com.gameder.api.Gamer;
import com.gameder.api.GamerCriteria;
import com.gameder.domain.GamerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GamerRepositoryImpl implements GamerRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    GamerRepositoryImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<GamerEntity> findByCriteria(final GamerCriteria gamerCriteria) {

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

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
