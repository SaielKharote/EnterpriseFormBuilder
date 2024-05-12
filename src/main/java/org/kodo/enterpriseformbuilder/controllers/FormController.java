package org.kodo.enterpriseformbuilder.controllers;

import org.kodo.enterpriseformbuilder.dtos.CreateFormRequestDTO;
import org.kodo.enterpriseformbuilder.dtos.FormDTO;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.FormException;
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
        Form form = formService.getFormById(formId);
        FormField formField = formFieldService.getFormFieldById(fieldId);
        if (form == null) {
            throw new FormException("Form not found");
        }
        if (formField == null) {
            throw new FormException("Form field not found");
        }
        form.getFields().add(formField);
        formService.saveForm(form);
        return ResponseEntity.ok(form);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable Long id) throws Exception {
        Form form = formService.getFormById(id);
        return ResponseEntity.ok(form);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<Form> getFormByTitle(@PathVariable String title) throws Exception {
        Form form = formService.getFormByTitle(title);
        return ResponseEntity.ok(form);
    }

    @GetMapping("/allForms")
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }
}
