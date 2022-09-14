package com.gameder.service;

import com.gameder.api.Gamer;
import com.gameder.domain.GamerEntity;
import com.gameder.repository.GamerRepository;
import com.gameder.service.gamer.GamerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Gamer unit Test
 */
@ExtendWith(MockitoExtension.class)
public class GamerServiceTest {

   private final Logger log = LoggerFactory.getLogger(GamerServiceTest.class);

   private GamerService gamerService;

   @Mock
   private GamerRepository gamerRepository;

   @BeforeEach
   void setup() {
      log.info("setup");
      gamerService = new GamerServiceImpl(gamerRepository);
   }

   @Test
   public void testCreateGamer() {
      log.info("testCreateGamer");

      final Gamer gamer = new Gamer(null,"NewGamer", new Date(1656366879731L),
              "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

      final GamerEntity persistedGamerEntity = persistedGamerEntity(gamer);

      Mockito.when(gamerRepository.save(Mockito.any(GamerEntity.class))).thenReturn(persistedGamerEntity);

      final Gamer returnedGamer = gamerService.createGamer(gamer);

      assertNotNull(returnedGamer.getId());
      assertEquals(returnedGamer.getId(), persistedGamerEntity.getId());
      assertEquals(returnedGamer.getDateOfBirth(), persistedGamerEntity.getDateOfBirth());
      assertEquals(returnedGamer.getDisplayName(), persistedGamerEntity.getDisplayName());
      assertEquals(returnedGamer.getEmailAddress(), gamer.getEmailAddress());
      assertEquals(returnedGamer.getIntroductionText(), gamer.getIntroductionText());
      assertEquals(returnedGamer.getTelephoneNumber(), gamer.getTelephoneNumber());
      assertEquals(returnedGamer.getPassword(), persistedGamerEntity.getPassword());

      log.info("testCreateGamerResponse {}", returnedGamer );
   }

   @Test
   public void testUpdateGamer() {
      log.info("testUpdateGamer");

      final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),"gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

      final GamerEntity persistedGamerEntity = persistedGamerEntity(gamer);

      Mockito.when(gamerRepository.findById(gamer.getId())).thenReturn(Optional.of(persistedGamerEntity));
      Mockito.when(gamerRepository.save(Mockito.any(GamerEntity.class))).thenReturn(persistedGamerEntity);

      final Gamer returnedGamer = gamerService.updateGamer(gamer);

      assertNotNull(returnedGamer.getId());
      assertEquals(returnedGamer.getId(), persistedGamerEntity.getId());
      assertEquals(returnedGamer.getDateOfBirth(), persistedGamerEntity.getDateOfBirth());
      assertEquals(returnedGamer.getDisplayName(), persistedGamerEntity.getDisplayName());
      assertEquals(returnedGamer.getEmailAddress(), persistedGamerEntity.getEmailAddress());
      assertEquals(returnedGamer.getIntroductionText(), persistedGamerEntity.getIntroductionText());
      assertEquals(returnedGamer.getTelephoneNumber(), persistedGamerEntity.getTelephoneNumber());
      assertEquals(returnedGamer.getPassword(), persistedGamerEntity.getPassword());

      log.info("testUpdateGamer {}", returnedGamer);
   }

   @Test
   public void testUpdateGamerNotExists() {
      log.info("testUpdateGamer");

      final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
              "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

      Mockito.when(gamerRepository.findById(gamer.getId())).thenReturn(Optional.empty());

      try {
         gamerService.updateGamer(gamer);

         fail("EntityNotFoundException NOT thrown as expected");

      } catch (EntityNotFoundException exception) {
         log.info("EntityNotFoundException thrown as expected" );
      }

      log.info("testUpdateGamerNotExists");
   }

   @Test
   public void testRetrieveGamer() {
      log.info("testRetrieveGamer");

      final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
              "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

      final GamerEntity persistedGamerEntity = persistedGamerEntity(gamer);

      Mockito.when(gamerRepository.findById("1")).thenReturn(Optional.of(persistedGamerEntity));

      final Gamer returnedGamer = gamerService.retrieveGamer("1");

      assertNotNull(returnedGamer.getId());
      assertEquals(returnedGamer.getId(), persistedGamerEntity.getId());
      assertEquals(returnedGamer.getDateOfBirth(), persistedGamerEntity.getDateOfBirth());
      assertEquals(returnedGamer.getDisplayName(), persistedGamerEntity.getDisplayName());
      assertEquals(returnedGamer.getEmailAddress(), persistedGamerEntity.getEmailAddress());
      assertEquals(returnedGamer.getIntroductionText(), persistedGamerEntity.getIntroductionText());
      assertEquals(returnedGamer.getTelephoneNumber(), persistedGamerEntity.getTelephoneNumber());
      assertEquals(returnedGamer.getPassword(), persistedGamerEntity.getPassword());

      log.info("testRetrieveGamer {}", returnedGamer);
   }

   @Test
   public void testRetrieveAllGamers() {
      log.info("testRetrieveAllGamers");

      final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
              "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

      final GamerEntity persistedGamerEntity = persistedGamerEntity(gamer);

      Mockito.when(gamerRepository.findAll()).thenReturn(Collections.singletonList(persistedGamerEntity));

      final List<Gamer> returnedGamers = gamerService.retrieveAllGamers();

      assertNotNull(returnedGamers);
      assertTrue(returnedGamers.size() == 1);
      Gamer returnedGamer = returnedGamers.get(0);
      assertNotNull(returnedGamer.getId());
      assertEquals(returnedGamer.getId(), persistedGamerEntity.getId());
      assertEquals(returnedGamer.getDateOfBirth(), persistedGamerEntity.getDateOfBirth());
      assertEquals(returnedGamer.getDisplayName(), persistedGamerEntity.getDisplayName());
      assertEquals(returnedGamer.getEmailAddress(), persistedGamerEntity.getEmailAddress());
      assertEquals(returnedGamer.getIntroductionText(), persistedGamerEntity.getIntroductionText());
      assertEquals(returnedGamer.getTelephoneNumber(), persistedGamerEntity.getTelephoneNumber());
      assertEquals(returnedGamer.getPassword(), persistedGamerEntity.getPassword());

      log.info("testRetrieveGamer {}", returnedGamer);
   }

   @Test
   public void testRetrieveGamerNotFound() {
      log.info("testRetrieveGamer");

      Mockito.when(gamerRepository.findById("1")).thenReturn(Optional.empty());

      try {
         gamerService.retrieveGamer("1");

         fail("EntityNotFoundException NOT thrown as expected");

      } catch (EntityNotFoundException exception) {
         log.info("EntityNotFoundException thrown as expected" );
      }

      log.info("testRetrieveGamer ");
   }

   @Test
   public void testArchiveGamer() {
      log.info("testArchiveGamer");

      final Gamer gamer = new Gamer("1","NewGamer", new Date(1656366879731L),
              "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
      final GamerEntity persistedGamerEntity = persistedGamerEntity(gamer);

      Mockito.when(gamerRepository.findById("1")).thenReturn(Optional.of(persistedGamerEntity));

      gamerService.archiveGamer("1");

      log.info("testArchiveGamer");
   }

   @Test
   public void testArchiveGamerNotFound() {
      log.info("testArchiveGamer");

      Mockito.when(gamerRepository.findById("1")).thenReturn(Optional.empty());

      try {
         gamerService.archiveGamer("1");

         fail("EntityNotFoundException NOT thrown as expected");

      } catch (EntityNotFoundException exception) {
         log.info("EntityNotFoundException thrown as expected" );
      }

      log.info("testArchiveGamerNotFound");
   }


   private GamerEntity persistedGamerEntity(final Gamer gamer) {
      final GamerEntity gamerEntity = new GamerEntity();
      gamerEntity.setId("1");
      gamerEntity.setDateOfBirth(gamer.getDateOfBirth());
      gamerEntity.setDisplayName(gamer.getDisplayName());
      gamerEntity.setEmailAddress(gamer.getEmailAddress());
      gamerEntity.setIntroductionText(gamer.getIntroductionText());
      gamerEntity.setTelephoneNumber(gamer.getTelephoneNumber());
      gamerEntity.setPassword("password");
      return gamerEntity;
   }

   @Test
   public void testEmailExists() {
      log.info("testEmailExists");

      final Gamer gamer = new Gamer(null,"NewGamer", new Date(1656366879731L),
              "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");

      final GamerEntity persistedGamerEntity = persistedGamerEntity(gamer);
      final List<GamerEntity> gamers = Collections.singletonList(persistedGamerEntity);

      Mockito.when(gamerRepository.findByEmailAddress("gamer@gamers.com")).thenReturn(gamers);

      Boolean result = gamerService.emailExists("gamer@gamers.com");

      assertTrue(result);

      log.info("testEmailExists");
   }

//   @Test
//   public void testLogin() {
//      log.info("testLogin");
//
//      final Gamer gamer = new Gamer(null,"NewGamer", new Date(1656366879731L),
//              "gamer@gamers.com", "019191999991911", null, "Hi Im a gamer", "password");
//
//      final GamerEntity persistedGamerEntity = persistedGamerEntity(gamer);
//      final List<GamerEntity> gamers = Collections.singletonList(persistedGamerEntity);
//
//      Mockito.when(gamerRepository.findByEmailAddressAndPassword("gamer@gamers.com","password")).thenReturn(gamers);
//
//      Boolean result = gamerService.login("gamer@gamers.com","password");
//
//      assertTrue(result);
//
//      log.info("testLogin");
//   }

}
