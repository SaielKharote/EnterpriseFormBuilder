package org.kodo.enterpriseformbuilder.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Form(String title, String submitButtonLabel, List<FormField> fields) {
        this.title = title;
        this.submitButtonLabel = submitButtonLabel;
        this.fields = fields;
    }

    @Column(nullable = false)
    private String title;

    @Column(name = "submit_button_label", nullable = false)
    private String submitButtonLabel;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormField> fields;

}
