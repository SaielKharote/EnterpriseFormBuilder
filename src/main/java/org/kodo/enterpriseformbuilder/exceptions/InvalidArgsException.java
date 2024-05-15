package org.kodo.enterpriseformbuilder.exceptions;

import org.kodo.enterpriseformbuilder.entities.DataType;

public class InvalidArgsException extends RuntimeException {
    public InvalidArgsException() {
        super("Invalid fields to create a form");
    }

    public InvalidArgsException(DataType dataType) {
        super("Please provide valid number of decimal places (decimalPlaces) for data type " + dataType);
    }
}
