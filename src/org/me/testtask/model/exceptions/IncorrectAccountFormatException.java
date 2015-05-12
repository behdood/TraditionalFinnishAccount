package org.me.testtask.model.exceptions;


public class IncorrectAccountFormatException extends Exception {
    public IncorrectAccountFormatException() {
        super("Account number is not in correct text format");
    }
}
