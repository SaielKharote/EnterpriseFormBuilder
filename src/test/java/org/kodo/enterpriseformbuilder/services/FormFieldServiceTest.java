package org.kodo.enterpriseformbuilder.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kodo.enterpriseformbuilder.dtos.CreateFormFieldRequestDTO;
import org.kodo.enterpriseformbuilder.entities.DataType;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.InvalidArgsException;
import org.kodo.enterpriseformbuilder.repositories.FormFieldRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormFieldServiceTest {
    @Mock
    private FormFieldRepository formFieldRepository;

    @InjectMocks
    private FormFieldService formFieldService;

    @Test
    void saveFormField_shouldCreateAndSaveFormField() {
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

        when(formFieldRepository.save(any(FormField.class))).thenReturn(mockFormField);

        FormField createdFormField = formFieldService.saveFormField(requestDTO, mockForm);

        assertEquals(mockFormField, createdFormField);
    }

    @Test
    void saveFormField_shouldThrowExceptionWhenDataTypeIsDecimalAndDecimalPlacesIsNull() {
        CreateFormFieldRequestDTO requestDTO = new CreateFormFieldRequestDTO();
        requestDTO.setLabel("Label");
        requestDTO.setDataType(DataType.DECIMAL);
        requestDTO.setIsRequired(false);
        requestDTO.setMinValue(null);
        requestDTO.setMaxValue(Long.MAX_VALUE);
        requestDTO.setDecimalPlaces(null);
        requestDTO.setForm(new Form());

        Form mockForm = new Form();

        Assertions.assertThrows(InvalidArgsException.class, () -> formFieldService.saveFormField(requestDTO, mockForm));
    }

    @Test
    void getFormFieldByLabel_shouldReturnFormField() {
        String label = "Label";
        FormField mockFormField = new FormField("Label", DataType.TEXT, false, null, Long.MAX_VALUE, null, new Form());

        when(formFieldRepository.findByLabel(label)).thenReturn(mockFormField);

        FormField retrievedFormField = formFieldService.getFormFieldByLabel(label);

        assertEquals(mockFormField, retrievedFormField);
    }

    @Test
    void getFormFieldById_shouldReturnFormField() {
        Long id = 1L;
        FormField mockFormField = new FormField("Label", DataType.TEXT, false, null, Long.MAX_VALUE, null, new Form());

        when(formFieldRepository.findById(id)).thenReturn(java.util.Optional.of(mockFormField));

        FormField retrievedFormField = formFieldService.getFormFieldById(id);

        assertEquals(mockFormField, retrievedFormField);
    }

    @Test
    void getFormFieldById_shouldReturnNull_whenFormFieldDoesNotExist() {
        Long id = 1L;

        when(formFieldRepository.findById(id)).thenReturn(java.util.Optional.empty());

        FormField retrievedFormField = formFieldService.getFormFieldById(id);

        assertNull(retrievedFormField);
    }

    @Test
    void getFormFieldByLabel_shouldReturnNull_whenFormFieldDoesNotExist() {
        String label = "Label";

        when(formFieldRepository.findByLabel(label)).thenReturn(null);

        FormField retrievedFormField = formFieldService.getFormFieldByLabel(label);

        assertNull(retrievedFormField);
    }
}