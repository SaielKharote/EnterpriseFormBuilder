package org.kodo.enterpriseformbuilder.exceptions;

public class FormFieldNotFoundException extends Exception {
    public FormFieldNotFoundException(Long formFieldId) {
        super("Form field not found with id: " + formFieldId);
    }

    public FormFieldNotFoundException(String label) {
        super("Form field not found with label: " + label);
    }
}
