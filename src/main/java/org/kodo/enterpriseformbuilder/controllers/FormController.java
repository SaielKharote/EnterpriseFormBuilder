package org.kodo.enterpriseformbuilder.controllers;

import org.kodo.enterpriseformbuilder.dtos.CreateFormRequestDTO;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.FormException;
import org.kodo.enterpriseformbuilder.exceptions.FormFieldNotFoundException;
import org.kodo.enterpriseformbuilder.exceptions.FormNotFoundException;
import org.kodo.enterpriseformbuilder.services.FormFieldService;
import org.kodo.enterpriseformbuilder.services.FormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;
    private final FormFieldService formFieldService;

    public FormController(FormService formService, FormFieldService formFieldService) {
        this.formService = formService;
        this.formFieldService = formFieldService;
    }

    @GetMapping("/count")
    public Long getFormCount() {
        return formService.getFormCount();
    }

    @PostMapping("/create")
    public ResponseEntity<Form> createForm(@RequestBody CreateFormRequestDTO createFormRequestDTO) {
        Form form = formService.createForm(createFormRequestDTO.getTitle(),
                createFormRequestDTO.getFields(),
                createFormRequestDTO.getSubmitButtonLabel());
        return new ResponseEntity<>(form, HttpStatus.CREATED);
    }

    @PostMapping("/{formId}/fields/{fieldId}")
    public ResponseEntity<Form> addFormFields(@PathVariable Long formId, @PathVariable Long fieldId) throws Exception {
        try {
            Form form = formService.getFormById(formId);
            FormField formField = formFieldService.getFormFieldById(fieldId);
            form.getFields().add(formField);
            formService.saveForm(form);
            return ResponseEntity.ok(form);
        } catch (FormNotFoundException e) {
            throw new FormNotFoundException("Form not found");
        } catch (FormFieldNotFoundException e) {
            throw new FormFieldNotFoundException("Form field not found");
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable Long id) {
        try {
            Form form = formService.getFormById(id);
            return ResponseEntity.ok(form);
        } catch (Exception e) {
            throw new FormException("Form with id " + id + " not found");
        }
    }

    @GetMapping("title/{title}")
    public ResponseEntity<Form> getFormByTitle(@PathVariable String title) {
        if (title == null) {
            throw new NullPointerException("Title cannot be null");
        }
        try {
            Form form = formService.getFormByTitle(title);
            return ResponseEntity.ok(form);
        } catch (Exception e) {
            throw new FormException("Form with title " + title + " not found");
        }
    }

    @GetMapping("/allForms")
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }
}
