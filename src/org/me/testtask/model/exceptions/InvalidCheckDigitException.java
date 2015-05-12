package org.me.testtask.model.exceptions;


public class InvalidCheckDigitException extends Exception {
    public InvalidCheckDigitException() {
        super("Check digit is not correct!");
    }
}
