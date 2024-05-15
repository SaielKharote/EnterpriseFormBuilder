package org.kodo.enterpriseformbuilder.controllers;

import org.kodo.enterpriseformbuilder.dtos.CreateFormRequestDTO;
import org.kodo.enterpriseformbuilder.dtos.FormDTO;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
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
    public ResponseEntity<FormDTO> createForm(@RequestBody CreateFormRequestDTO createFormRequestDTO) {
        FormDTO formDTO = formService.createForm(createFormRequestDTO);
        return new ResponseEntity<>(formDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{formId}/fields/{fieldId}")
    public ResponseEntity<FormDTO> addFormFields(@PathVariable Long formId, @PathVariable Long fieldId) throws Exception {
        Form form = formService.getFormById(formId);
        FormField formField = formFieldService.getFormFieldById(fieldId);
        if (form == null) {
            throw new FormNotFoundException(formId);
        }
        if (formField == null) {
            throw new FormFieldNotFoundException(fieldId);
        }
        form.getFields().add(formField);
        formService.saveForm(form);
        return ResponseEntity.ok(new FormDTO(form.getTitle(), form.getFields(), form.getSubmitButtonLabel()));
    }

    @GetMapping("id/{formId}")
    public ResponseEntity<FormDTO> getFormById(@PathVariable Long formId) {
        try {
            Form form = formService.getFormById(formId);
            FormDTO formDTO = new FormDTO(form.getTitle(), form.getFields(), form.getSubmitButtonLabel());
            return ResponseEntity.ok(formDTO);
        } catch (Exception e) {
            throw new FormNotFoundException(formId);
        }
    }

    @GetMapping("title/{title}")
    public ResponseEntity<FormDTO> getFormByTitle(@PathVariable String title) {
        try {
            Form form = formService.getFormByTitle(title);
            FormDTO formDTO = new FormDTO(form.getTitle(), form.getFields(), form.getSubmitButtonLabel());
            return ResponseEntity.ok(formDTO);
        } catch (Exception e) {
            throw new FormNotFoundException(title);
        }
    }

    @GetMapping("/allForms")
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

}
