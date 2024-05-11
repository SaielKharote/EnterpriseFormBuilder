package org.kodo.enterpriseformbuilder.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long maxLength;

    @Column(nullable = false)
    private Long minLength;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Boolean required;

    @Column(nullable = false)
    private Integer decimal_places;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;
}
