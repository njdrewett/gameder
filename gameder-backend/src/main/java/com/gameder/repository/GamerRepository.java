package com.gameder.repository;

import com.gameder.domain.GamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<GamerEntity, String> {

}
