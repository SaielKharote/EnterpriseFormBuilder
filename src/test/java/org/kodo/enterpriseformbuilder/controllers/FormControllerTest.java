package org.kodo.enterpriseformbuilder.controllers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kodo.enterpriseformbuilder.dtos.CreateFormRequestDTO;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.FormNotFoundException;
import org.kodo.enterpriseformbuilder.services.FormFieldService;
import org.kodo.enterpriseformbuilder.services.FormService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FormControllerTest {
    @Mock
    private FormService formService;

    @Mock
    private FormFieldService formFieldService;

    @InjectMocks
    private FormController formController;

    @Test
    void createForm_shouldReturnCreatedForm() {
        CreateFormRequestDTO requestDTO = new CreateFormRequestDTO();
        requestDTO.setTitle("Test Form");
        requestDTO.setSubmitButtonLabel("Submit");
        requestDTO.setFields(new ArrayList<>());

        when(formService.createForm(requestDTO)).thenReturn(new Form());

        ResponseEntity<Form> form = formController.createForm(requestDTO);

        Assertions.assertThat(form.getBody()).isNotNull();
    }

    @Test
    void addFormFields_shouldAddFieldsToForm() throws Exception {
        Long formId = 1L;
        Long fieldId = 2L;
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());

        when(formService.getFormById(formId)).thenReturn(mockForm);
        when(formFieldService.getFormFieldById(fieldId)).thenReturn(new FormField());

        ResponseEntity<Form> response = formController.addFormFields(formId, fieldId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockForm, response.getBody());
        verify(formService, times(1)).saveForm(mockForm);
    }

    @Test
    void getFormById_shouldReturnForm() {
        Long formId = 1L;
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());

        when(formService.getFormById(formId)).thenReturn(mockForm);

        ResponseEntity<Form> response = formController.getFormById(formId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockForm, response.getBody());
    }

    @Test
    void getFormByTitle_shouldReturnForm() {
        String title = "Test Form";
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());

        when(formService.getFormByTitle(title)).thenReturn(mockForm);

        ResponseEntity<Form> response = formController.getFormByTitle(title);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockForm, response.getBody());
    }

    @Test
    void getAllForms_shouldReturnAllForms() {
        when(formService.getAllForms()).thenReturn(new ArrayList<>());
        List<Form> response = formController.getAllForms();
        response.add(new Form("Test Form", "Submit", new ArrayList<>()));
        assertThat(response).isNotNull();
    }

    @Test
    void getFormCount_shouldReturnFormCount() {
        when(formService.getFormCount()).thenReturn(1L);
        long response = formController.getFormCount();
        assertThat(response).isGreaterThan(-1);
    }

    @Test
    void getFormByTitle_shouldThrowFormNotFoundException_whenFormDoesNotExist() {
        String title = "Test Form";
        when(formService.getFormByTitle(title)).thenThrow(new FormNotFoundException(title));
        assertThrows(FormNotFoundException.class, () -> formController.getFormByTitle(title));
    }
}