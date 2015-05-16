package org.me.testtask.exceptions;


public class InvalidBankCodeException extends BaseBankAccountNumberException {
    public InvalidBankCodeException(String bankCode) {
        super("Unknown bank code : " + bankCode);
    }
}
