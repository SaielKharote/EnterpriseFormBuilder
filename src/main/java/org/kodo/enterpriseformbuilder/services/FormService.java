package org.kodo.enterpriseformbuilder.services;

import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.entities.FormField;
import org.kodo.enterpriseformbuilder.repositories.FormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form getFormByTitle(String title) throws Exception {
        if (formRepository.findByTitle(title) != null) {
            return formRepository.findByTitle(title);
        } else {
            throw new Exception("Form not found");
        }
    }

    public void deleteForm(Form form) {
        formRepository.delete(form);
    }

    public long getFormCount() {
        return formRepository.count();
    }

    public Form createForm(String title, List<FormField> fields, String getSubmitLabel) {
        Form form = new Form(title, getSubmitLabel, fields);
        return formRepository.save(form);
    }

    public Form getFormById(Long id) throws Exception {
        return formRepository.findById(id).orElseThrow(() -> new Exception("Form not found"));
    }

    public void saveForm(Form form) {
        formRepository.save(form);
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }
}
