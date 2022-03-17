package apptranslator.repository.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TRANSLATION_REQUEST_INFO", schema = "")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TranslationRequestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String en;
    private String ru;
    private String ip;
    //private LocalDate requestDate;
}

