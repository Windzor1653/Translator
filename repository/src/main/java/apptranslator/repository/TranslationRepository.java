package apptranslator.repository;

import apptranslator.entity.TranslationRequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationRequestInfo, Long> {
    @Modifying
    @Query(value = "insert into TRANSLATION_REQUEST_INFO(sourceWord, translatedWord, targetLang, localDateTime, ip) values " +
            "(:sourceWord, :translatedWord, :targetLang, :localDateTime, :ip)",
            nativeQuery = true)
    @Transactional
    void insertTranslationRequestInfo(@Param("sourceWord") String sourceWord, @Param("translatedWord") String translatedWord,
                                      @Param("targetLang") String targetLang, @Param("localDateTime") LocalDateTime localDateTime,
                                      @Param("ip") String ip);
}
