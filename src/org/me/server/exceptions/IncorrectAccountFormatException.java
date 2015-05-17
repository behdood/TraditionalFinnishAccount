package org.me.server.exceptions;


public class IncorrectAccountFormatException extends BaseBankAccountNumberException {
    public IncorrectAccountFormatException() {
        super("Account number is not in correct text format.");
    }
}
