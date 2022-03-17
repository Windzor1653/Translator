package apptranslator.repository;

import apptranslator.repository.entity.TranslationRequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationRequestInfo, Long> {
    @Modifying
    @Query(value = "insert into TRANSLATION_REQUEST_INFO(en, ru, params, localDateTime, ip) values " +
            "(:en, :ru, :params, :localDateTime, :ip)",
            nativeQuery = true)
    @Transactional
    void insertTranslationRequestInfo(@Param("en") String en, @Param("ru") String ru,
                                      @Param("params") String params, @Param("localDateTime") LocalDateTime localDateTime,
                                      @Param("ip") String ip);
}
