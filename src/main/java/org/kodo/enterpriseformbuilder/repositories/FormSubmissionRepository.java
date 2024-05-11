package org.kodo.enterpriseformbuilder.repositories;

import org.kodo.enterpriseformbuilder.entities.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {
    public FormSubmission findByFormId(Long formId);
}
