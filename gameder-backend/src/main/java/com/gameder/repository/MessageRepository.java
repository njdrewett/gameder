package com.gameder.repository;

import com.gameder.domain.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, String> {

    @Query("select message from MessageEntity message where " +
            "(message.fromGamer.id = :gamerId " +
            "or " +
            "message.toGamer.id = :gamerId)")
    List<MessageEntity> findByGamerId(@Param("gamerId") final String gamerId);

}
