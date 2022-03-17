package apptranslator.repository;

import apptranslator.repository.entity.TranslationRequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationRequestInfo, Long> {
    @Modifying
    @Query(value = "insert into TRANSLATION_REQUEST_INFO values (1, 2, 3, 4)", nativeQuery = true)
    @Transactional
    void insertTranslationRequestInfo();
}
