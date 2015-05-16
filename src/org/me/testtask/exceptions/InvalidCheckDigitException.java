package org.me.testtask.exceptions;


public class InvalidCheckDigitException extends BaseBankAccountNumberException {
    public InvalidCheckDigitException() {
        super("Check digit is not correct.");
    }
}
