package org.me.server.exceptions;


public class InvalidCheckDigitException extends BaseBankAccountNumberException {
    public InvalidCheckDigitException() {
        super("Check digit is not correct.");
    }
}
