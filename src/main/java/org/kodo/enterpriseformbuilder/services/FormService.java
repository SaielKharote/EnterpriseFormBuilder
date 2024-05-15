package org.kodo.enterpriseformbuilder.services;

import org.kodo.enterpriseformbuilder.dtos.CreateFormRequestDTO;
import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.exceptions.FormNotFoundException;
import org.kodo.enterpriseformbuilder.exceptions.InvalidArgsException;
import org.kodo.enterpriseformbuilder.repositories.FormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form getFormByTitle(String title) {
        if (formRepository.findByTitle(title) != null) {
            return formRepository.findByTitle(title);
        } else {
            throw new FormNotFoundException(title);
        }
    }

    public long getFormCount() {
        return formRepository.count();
    }

    public Form createForm(CreateFormRequestDTO createFormRequestDTO) {
        try {
            Form form = new Form(createFormRequestDTO.getTitle(),
                    createFormRequestDTO.getSubmitButtonLabel(),
                    createFormRequestDTO.getFields());
            formRepository.save(form);
            return form;
        } catch (Exception e) {
            throw new InvalidArgsException();
        }
    }

    public Form getFormById(Long formId) {
        return formRepository.findById(formId).orElseThrow(() -> new FormNotFoundException(formId));
    }

    public void saveForm(Form form) {
        formRepository.save(form);
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }
}
