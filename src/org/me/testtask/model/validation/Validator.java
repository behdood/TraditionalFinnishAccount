package org.me.testtask.model.validation;

import org.me.testtask.model.exceptions.IncorrectAccountFormatException;
import org.me.testtask.model.exceptions.InvalidBankCodeException;
import org.me.testtask.model.exceptions.InvalidCheckDigitException;

public interface Validator {

    String validateAndConvertToElectronicFormat(String accountNumber)
            throws IncorrectAccountFormatException, InvalidCheckDigitException, InvalidBankCodeException;
}
