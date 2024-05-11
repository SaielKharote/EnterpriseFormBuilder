package org.kodo.enterpriseformbuilder.services;

import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.repositories.FormFieldRepository;
import org.springframework.stereotype.Service;

@Service
public class FormFieldService {
    private FormFieldRepository formFieldRepository;
    public FormFieldService(FormFieldRepository formFieldRepository) {
        this.formFieldRepository = formFieldRepository;
    }

    public FormField getFormFieldByLabel(String label) {
        return formFieldRepository.findByLabel(label);
    }

    public FormField saveFormField(FormField formField) {
        return formFieldRepository.save(formField);
    }

    public void deleteFormField(FormField formField) {
        formFieldRepository.delete(formField);
    }

    public FormField getFormFieldById(Long id) {
        return formFieldRepository.findById(id).orElse(null);
    }
}
