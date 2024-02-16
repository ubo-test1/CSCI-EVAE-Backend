package com.back.csaback.Models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
=======
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
>>>>>>> 5e8e0b197ba38f2d42bfe30f5d9bbefce4fb5028
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
<<<<<<< HEAD
@Table(name = "qualificatif")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
@Table(name = "QUALIFICATIF")
>>>>>>> 5e8e0b197ba38f2d42bfe30f5d9bbefce4fb5028
public class Qualificatif {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qual_generator")
    @SequenceGenerator(name="qual_generator", sequenceName = "QUA_SEQ", allocationSize=1)
    @Column(name = "ID_QUALIFICATIF", nullable = false)
    private Integer id;

    @Size(max = 16)
    @NotNull
    @Column(name = "MAXIMAL", nullable = false, length = 16)
    private String maximal;

    @Size(max = 16)
    @NotNull
    @Column(name = "MINIMAL", nullable = false, length = 16)
    private String minimal;

}