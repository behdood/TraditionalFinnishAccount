package org.me.server.validation;


import org.me.server.exceptions.IncorrectAccountFormatException;
import org.me.server.exceptions.InvalidBankCodeException;
import org.me.server.exceptions.InvalidCheckDigitException;

public interface BankAccountNumberValidator {

    String validateAndConvertToLongFormat(String accountNumber) throws IncorrectAccountFormatException, InvalidBankCodeException, InvalidCheckDigitException;

}
