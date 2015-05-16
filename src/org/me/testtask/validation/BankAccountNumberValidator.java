package org.me.testtask.validation;


import org.me.testtask.exceptions.IncorrectAccountFormatException;
import org.me.testtask.exceptions.InvalidBankCodeException;
import org.me.testtask.exceptions.InvalidCheckDigitException;

public interface BankAccountNumberValidator {

    String validateAndConvertToLongFormat(String accountNumber) throws IncorrectAccountFormatException, InvalidBankCodeException, InvalidCheckDigitException;

}
