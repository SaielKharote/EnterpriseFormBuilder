package org.kodo.enterpriseformbuilder.dtos;

import lombok.*;
import org.kodo.enterpriseformbuilder.entities.DataType;
import org.kodo.enterpriseformbuilder.entities.Form;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormFieldDTO {
    private String label;
    private DataType dataType;
    private Boolean isRequired;
    private Long minValue;
    private Long maxValue = Long.MAX_VALUE;
    private Integer decimalPlaces;
    private Form form;
}
