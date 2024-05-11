package org.kodo.enterpriseformbuilder.services;

import org.kodo.enterpriseformbuilder.entities.FormSubmission;
import org.kodo.enterpriseformbuilder.repositories.FormSubmissionRepository;
import org.springframework.stereotype.Service;

@Service
public class FormSubmissionService {
    private FormSubmissionRepository formSubmissionRepository;
    public FormSubmissionService(FormSubmissionRepository formSubmissionRepository) {
        this.formSubmissionRepository = formSubmissionRepository;
    }

    public FormSubmission getFormSubmissionByFormId(Long formId) {
        return formSubmissionRepository.findByFormId(formId);
    }

    public FormSubmission saveFormSubmission(FormSubmission formSubmission) {
        return formSubmissionRepository.save(formSubmission);
    }
}
