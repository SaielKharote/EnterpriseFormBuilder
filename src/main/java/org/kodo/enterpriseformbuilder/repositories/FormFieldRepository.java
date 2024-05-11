package org.kodo.enterpriseformbuilder.repositories;

import org.kodo.enterpriseformbuilder.entities.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFieldRepository extends JpaRepository<FormField, Long> {
    public FormField findByLabel(String label);
}
