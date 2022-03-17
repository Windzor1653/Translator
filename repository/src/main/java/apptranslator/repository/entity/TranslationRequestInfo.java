package apptranslator.repository.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TRANSLATION_REQUEST_INFO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TranslationRequestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String en;
    private String ru;
    private String ip;
    private LocalDate requestDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEn() {
        return en;
    }

    public String getIp() {
        return ip;
    }

    public String getRu() {
        return ru;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }
}

