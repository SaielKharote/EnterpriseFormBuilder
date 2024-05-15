package org.kodo.enterpriseformbuilder.services;

import org.kodo.enterpriseformbuilder.dtos.CreateFormFieldRequestDTO;
import org.kodo.enterpriseformbuilder.entities.DataType;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.InvalidArgsException;
import org.kodo.enterpriseformbuilder.repositories.FormFieldRepository;
import org.springframework.stereotype.Service;

@Service
public class FormFieldService {

    private final FormFieldRepository formFieldRepository;
    public FormFieldService(FormFieldRepository formFieldRepository) {
        this.formFieldRepository = formFieldRepository;
    }

    public FormField saveFormField(CreateFormFieldRequestDTO createFormFieldDTO, Form form) {
        if (createFormFieldDTO.getDataType() == DataType.DECIMAL && createFormFieldDTO.getDecimalPlaces() == null) {
            throw new InvalidArgsException(DataType.DECIMAL);
        }
        FormField formField = new FormField(createFormFieldDTO.getLabel(),
                createFormFieldDTO.getDataType(),
                createFormFieldDTO.getIsRequired(),
                createFormFieldDTO.getMinValue(),
                createFormFieldDTO.getMaxValue(),
                createFormFieldDTO.getDecimalPlaces(),
                form);
        return formFieldRepository.save(formField);
    }


    public FormField getFormFieldByLabel(String label) {
        return formFieldRepository.findByLabel(label);
    }

    public FormField getFormFieldById(Long id) {
        return formFieldRepository.findById(id).orElse(null);
    }


}
