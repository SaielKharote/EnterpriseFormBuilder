package org.kodo.enterpriseformbuilder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "form_fields")
public class FormField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private DataType dataType;

    @Column(nullable = false)
    private Boolean isRequired;

    @Column(nullable = true)
    private Integer minValue;

    @Column(nullable = true)
    private Long maxValue = 255L;

    @Column(nullable = true)
    private Integer decimal_places;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    @JsonIgnore
    private Form form;
}
