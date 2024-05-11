package org.kodo.enterpriseformbuilder.services;

import org.kodo.enterpriseformbuilder.entities.Form;
import org.kodo.enterpriseformbuilder.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form getFormByTitle(String title) {
        return formRepository.findByTitle(title);
    }

    public Form saveForm(Form form) {
        return formRepository.save(form);
    }

    public void deleteForm(Form form) {
        formRepository.delete(form);
    }

    public long getFormCount() {
        return formRepository.count();
    }
}
