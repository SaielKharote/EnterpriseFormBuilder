package org.kodo.enterpriseformbuilder.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kodo.enterpriseformbuilder.dtos.CreateFormRequestDTO;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.exceptions.FormNotFoundException;
import org.kodo.enterpriseformbuilder.repositories.FormRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FormServiceTest {

    @Mock
    private FormRepository formRepository;

    @InjectMocks
    private FormService formService;

    @Test
    void createForm_shouldCreateAndSaveForm() {
        CreateFormRequestDTO requestDTO = new CreateFormRequestDTO("Test Form", "Submit", new ArrayList<>());
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());

        when(formRepository.save(any(Form.class))).thenReturn(mockForm);

        Form createdForm = formService.createForm(requestDTO);

        assertEquals(mockForm.getId(), createdForm.getId());
        verify(formRepository, times(1)).save(any(Form.class));
    }

    @Test
    void getFormById_shouldReturnForm_whenFormExists() {
        Long formId = 1L;
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());

        when(formRepository.findById(formId)).thenReturn(Optional.of(mockForm));

        Form form = formService.getFormById(formId);

        assertEquals(mockForm, form);
    }

    @Test
    void getFormById_shouldThrowFormNotFoundException_whenFormDoesNotExist() {
        Long formId = 1L;

        when(formRepository.findById(formId)).thenReturn(Optional.empty());

        assertThrows(FormNotFoundException.class, () -> formService.getFormById(formId));
    }

    @Test
    void getFormByTitle_shouldReturnForm_whenFormExists() {
        String title = "Test Form";
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());

        when(formRepository.findByTitle(title)).thenReturn(mockForm);

        Form form = formService.getFormByTitle(title);

        assertEquals(mockForm, form);
    }

    @Test
    void getFormByTitle_shouldThrowFormNotFoundException_whenFormDoesNotExist() {
        String title = "Test Form";

        when(formRepository.findByTitle(title)).thenReturn(null);

        assertThrows(FormNotFoundException.class, () -> formService.getFormByTitle(title));
    }

    @Test
    void getFormCount_shouldReturnFormCount() {
        long formCount = formService.getFormCount();
        assertThat(formCount).isGreaterThan(-1);
    }

    @Test
    void getAllForms_shouldReturnAllForms() {
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());
        when(formRepository.findAll()).thenReturn(new ArrayList<>() {{
            add(mockForm);
        }});

        assertThat(formService.getAllForms()).isNotEqualTo(mockForm);
    }

    @Test
    void saveForm_shouldSaveForm() {
        Form mockForm = new Form("Test Form", "Submit", new ArrayList<>());
        formService.saveForm(mockForm);
        verify(formRepository, times(1)).save(mockForm);
    }
}