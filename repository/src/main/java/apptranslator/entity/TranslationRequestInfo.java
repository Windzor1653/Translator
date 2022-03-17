package apptranslator.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSLATION_REQUEST_INFO", schema = "")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TranslationRequestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String sourceWord;
    private String targetLang;
    private String translatedWord;
    private LocalDateTime localDateTime;
    private String ip;
}

