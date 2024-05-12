package org.kodo.enterpriseformbuilder.dtos;

import lombok.*;
import org.kodo.enterpriseformbuilder.entities.FormField;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class FormDTO {
    private String title;
    private List<FormField> fields;
    private String submitButtonLabel;

}
