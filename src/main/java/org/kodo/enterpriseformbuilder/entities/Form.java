package org.kodo.enterpriseformbuilder.entities;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;


@Entity
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "submit_button_label", nullable = false)
    private String submitButtonLabel;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormField> fields;

}
