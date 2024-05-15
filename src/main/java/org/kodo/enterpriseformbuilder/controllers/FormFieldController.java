package org.kodo.enterpriseformbuilder.controllers;

import org.kodo.enterpriseformbuilder.dtos.CreateFormFieldRequestDTO;
import org.kodo.enterpriseformbuilder.dtos.FormFieldDTO;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.FormFieldNotFoundException;
import org.kodo.enterpriseformbuilder.exceptions.FormNotFoundException;
import org.kodo.enterpriseformbuilder.services.FormFieldService;
import org.kodo.enterpriseformbuilder.services.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fields")
public class FormFieldController {
    private final FormFieldService formFieldService;
    private final FormService formService;

    public FormFieldController(FormFieldService formFieldService, FormService formService) {
        this.formFieldService = formFieldService;
        this.formService = formService;
    }

    @PostMapping("/create")
    public ResponseEntity<FormFieldDTO> createFormField(@RequestParam Long formId, @RequestBody CreateFormFieldRequestDTO createFormFieldRequestDTO) {
        Form form = formService.getFormById(formId);
        if (form == null) {
            throw new FormNotFoundException(formId);
        }
        try {
            FormField formField = formFieldService.saveFormField(createFormFieldRequestDTO, form);
            FormFieldDTO formFieldDTO = new FormFieldDTO(formField.getLabel(),
                    formField.getDataType(),
                    formField.getIsRequired(),
                    formField.getMinValue(),
                    formField.getMaxValue(),
                    formField.getDecimalPlaces(),
                    formField.getForm());
            return ResponseEntity.ok(formFieldDTO);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid data type");
        }
    }

    @PostMapping("/remove/{formId}/{fieldId}")
    public ResponseEntity<FormFieldDTO> removeFormField(@PathVariable Long formId, @PathVariable Long fieldId) throws FormFieldNotFoundException {
            FormField formField = formFieldService.getFormFieldById(fieldId);
            Form form = formService.getFormById(formId);
            if (formField == null) {
                throw new FormFieldNotFoundException(fieldId);
            }
            if (form == null) {
                throw new FormNotFoundException(formId);
            }
            formFieldService.removeFormField(form, formField);
            FormFieldDTO formFieldDTO = new FormFieldDTO(formField.getLabel(),
                    formField.getDataType(),
                    formField.getIsRequired(),
                    formField.getMinValue(),
                    formField.getMaxValue(),
                    formField.getDecimalPlaces(),
                    formField.getForm());
            return ResponseEntity.ok(formFieldDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<FormFieldDTO> deleteFormField(@PathVariable Long id) throws FormFieldNotFoundException {
        FormField formField = formFieldService.getFormFieldById(id);
        if (formField == null) {
            throw new FormFieldNotFoundException(id);
        }
        formFieldService.deleteFormField(formField);
        FormFieldDTO formFieldDTO = new FormFieldDTO(formField.getLabel(),
                formField.getDataType(),
                formField.getIsRequired(),
                formField.getMinValue(),
                formField.getMaxValue(),
                formField.getDecimalPlaces(),
                formField.getForm());
        return ResponseEntity.ok(formFieldDTO);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<FormFieldDTO> getFormFieldById(@PathVariable Long id) throws FormFieldNotFoundException {
        FormField formField = formFieldService.getFormFieldById(id);
        if (formField == null) {
            throw new FormFieldNotFoundException(id);
        }
        FormFieldDTO formFieldDTO = new FormFieldDTO(formField.getLabel(),
                formField.getDataType(),
                formField.getIsRequired(),
                formField.getMinValue(),
                formField.getMaxValue(),
                formField.getDecimalPlaces(),
                formField.getForm());
        return ResponseEntity.ok(formFieldDTO);

    }

    @GetMapping("label/{label}")
    public ResponseEntity<FormFieldDTO> getFormFieldByLabel(@PathVariable String label) throws FormFieldNotFoundException {
        FormField formField = formFieldService.getFormFieldByLabel(label);
        if (formField == null) {
            throw new FormFieldNotFoundException(label);
        }
        FormFieldDTO formFieldDTO = new FormFieldDTO(formField.getLabel(),
                formField.getDataType(),
                formField.getIsRequired(),
                formField.getMinValue(),
                formField.getMaxValue(),
                formField.getDecimalPlaces(),
                formField.getForm());
        return ResponseEntity.ok(formFieldDTO);
    }
}
