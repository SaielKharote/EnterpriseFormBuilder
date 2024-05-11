package org.kodo.enterpriseformbuilder.controllers;

import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.services.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/count")
    public Long getFormCount() {
        return formService.getFormCount();
    }

    @PostMapping("/create")
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        return ResponseEntity.ok(formService.saveForm(form));
    }
}
