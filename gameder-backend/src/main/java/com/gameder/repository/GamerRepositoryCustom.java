package com.gameder.repository;

import com.gameder.api.GamerCriteria;
import com.gameder.domain.GamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GamerRepositoryCustom  {

    List<GamerEntity> findByCriteria(final GamerCriteria gamerCriteria);

    GamerEntity findByReference(final String id);
}
