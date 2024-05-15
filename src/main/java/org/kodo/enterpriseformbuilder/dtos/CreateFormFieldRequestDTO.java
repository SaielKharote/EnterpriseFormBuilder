package org.kodo.enterpriseformbuilder.dtos;

import lombok.*;
import org.kodo.enterpriseformbuilder.entities.DataType;
import org.kodo.enterpriseformbuilder.entities.Form;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateFormFieldRequestDTO {
    private String label;
    private DataType dataType;
    private Boolean isRequired;
    private Long minValue;
    private Long maxValue = Long.MAX_VALUE;
    private Integer decimalPlaces;
    private Form form;
}
