package org.kodo.enterpriseformbuilder.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kodo.enterpriseformbuilder.dtos.CreateFormFieldRequestDTO;
import org.kodo.enterpriseformbuilder.dtos.FormFieldDTO;
import org.kodo.enterpriseformbuilder.entities.DataType;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.FormFieldNotFoundException;
import org.kodo.enterpriseformbuilder.services.FormFieldService;
import org.kodo.enterpriseformbuilder.services.FormService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormFieldControllerTest {

    @Mock
    private FormFieldService formFieldService;

    @Mock
    private FormService formService;

    @InjectMocks
    private FormFieldController formFieldController;

    @Test
    void createFormField_shouldReturnCreatedFormField() {
        Long formId = 1L;
        CreateFormFieldRequestDTO requestDTO = new CreateFormFieldRequestDTO();
        requestDTO.setLabel("Label");
        requestDTO.setDataType(DataType.TEXT);
        requestDTO.setIsRequired(false);
        requestDTO.setMinValue(null);
        requestDTO.setMaxValue(Long.MAX_VALUE);
        requestDTO.setDecimalPlaces(null);
        requestDTO.setForm(new Form());

        Form mockForm = new Form();
        FormField mockFormField = new FormField("Label", DataType.TEXT, false, null, Long.MAX_VALUE, null, mockForm);

        when(formService.getFormById(formId)).thenReturn(mockForm);
        when(formFieldService.saveFormField(requestDTO, mockForm)).thenReturn(mockFormField);

        ResponseEntity<FormFieldDTO> response = formFieldController.createFormField(formId, requestDTO);

        assertEquals(mockFormField.getLabel(), Objects.requireNonNull(response.getBody()).getLabel());
        assertEquals(mockFormField.getDataType(), response.getBody().getDataType());
        assertEquals(mockFormField.getIsRequired(), response.getBody().getIsRequired());
        assertEquals(mockFormField.getMinValue(), response.getBody().getMinValue());
        assertEquals(mockFormField.getMaxValue(), response.getBody().getMaxValue());
        assertEquals(mockFormField.getDecimalPlaces(), response.getBody().getDecimalPlaces());
    }

    @Test
    void getFormFieldById_shouldReturnFormField() throws FormFieldNotFoundException {
        Long fieldId = 1L;
        Form mockForm = new Form();
        FormField mockFormField = new FormField("Label", DataType.TEXT, false, null, Long.MAX_VALUE, null, mockForm);

        when(formFieldService.getFormFieldById(fieldId)).thenReturn(mockFormField);

        ResponseEntity<FormFieldDTO> response = formFieldController.getFormFieldById(fieldId);

        assertEquals(mockFormField.getLabel(), Objects.requireNonNull(response.getBody()).getLabel());
        assertEquals(mockFormField.getDataType(), response.getBody().getDataType());
        assertEquals(mockFormField.getIsRequired(), response.getBody().getIsRequired());
        assertEquals(mockFormField.getMinValue(), response.getBody().getMinValue());
        assertEquals(mockFormField.getMaxValue(), response.getBody().getMaxValue());
        assertEquals(mockFormField.getDecimalPlaces(), response.getBody().getDecimalPlaces());
    }

    @Test
    void getFormFieldByLabel_shouldReturnFormField() throws FormFieldNotFoundException {
        String label = "Label";
        Form mockForm = new Form();
        FormField mockFormField = new FormField("Label", DataType.TEXT, false, null, Long.MAX_VALUE, null, mockForm);

        when(formFieldService.getFormFieldByLabel(label)).thenReturn(mockFormField);

        ResponseEntity<FormFieldDTO> response = formFieldController.getFormFieldByLabel(label);

        assertEquals(mockFormField.getLabel(), Objects.requireNonNull(response.getBody()).getLabel());
        assertEquals(mockFormField.getDataType(), response.getBody().getDataType());
        assertEquals(mockFormField.getIsRequired(), response.getBody().getIsRequired());
        assertEquals(mockFormField.getMinValue(), response.getBody().getMinValue());
        assertEquals(mockFormField.getMaxValue(), response.getBody().getMaxValue());
        assertEquals(mockFormField.getDecimalPlaces(), response.getBody().getDecimalPlaces());
    }
}