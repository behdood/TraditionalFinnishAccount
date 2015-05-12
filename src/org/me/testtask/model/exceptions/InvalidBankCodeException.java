package org.me.testtask.model.exceptions;


public class InvalidBankCodeException extends Exception {
    public InvalidBankCodeException(String bankCode) {
        super("Unknown bank code : " + bankCode);
    }
}
