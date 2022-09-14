package com.gameder.repository;

import com.gameder.api.Gamer;
import com.gameder.domain.GamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamerRepository extends JpaRepository<GamerEntity, String> {

    List<GamerEntity> findByEmailAddress(final String emailAddress);

    List<GamerEntity> findByEmailAddressAndPassword(final String emailAddress, final String password);
}
