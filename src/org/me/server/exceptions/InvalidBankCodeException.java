package org.me.server.exceptions;


public class InvalidBankCodeException extends BaseBankAccountNumberException {
    public InvalidBankCodeException(String bankCode) {
        super("Unknown bank code : " + bankCode);
    }
}
