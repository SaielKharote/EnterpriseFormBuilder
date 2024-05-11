package org.kodo.enterpriseformbuilder.repositories;

import org.kodo.enterpriseformbuilder.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    public Form findByTitle(String title);
}
