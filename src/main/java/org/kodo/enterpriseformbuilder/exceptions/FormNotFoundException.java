package org.kodo.enterpriseformbuilder.exceptions;

public class FormNotFoundException extends RuntimeException {
    public FormNotFoundException(Long formId) {
        super("Form not found with id: " + formId);
    }

    public FormNotFoundException(String title) {
        super("Form not found with id: " + title);
    }
}
