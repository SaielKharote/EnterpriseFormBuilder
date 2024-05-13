package org.kodo.enterpriseformbuilder.controllers;

import org.kodo.enterpriseformbuilder.entities.DataType;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.exceptions.FormException;
import org.kodo.enterpriseformbuilder.exceptions.FormNotFoundException;
import org.kodo.enterpriseformbuilder.services.FormFieldService;
import org.kodo.enterpriseformbuilder.services.FormService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<FormField> createFormField(@RequestParam Long formId, @RequestBody FormField formField) throws Exception {

        Form form = formService.getFormById(formId);
        if (form == null) {
            throw new FormNotFoundException("Form not found");
        }
        else {
            formField.setForm(form);
            if (formField.getDataType() == DataType.DECIMAL && formField.getDecimalPlaces() == null) {
                throw new IllegalArgumentException("Decimal places must be provided for decimal data type");
            }
            else {
                return ResponseEntity.ok(formFieldService.saveFormField(formField));
            }
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<FormField> deleteFormField(FormField formField) {
        formFieldService.deleteFormField(formField);
        return ResponseEntity.ok(formField);
    }

    @GetMapping("{id}/id")
    public ResponseEntity<FormField> getFormFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(formFieldService.getFormFieldById(id));
    }

    @GetMapping("{label}/label")
    public ResponseEntity<FormField> getFormFieldByLabel(@PathVariable String label) {
        return ResponseEntity.ok(formFieldService.getFormFieldByLabel(label));
    }
}
