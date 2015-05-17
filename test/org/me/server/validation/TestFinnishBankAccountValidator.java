package org.me.server.validation;

import org.junit.Before;
import org.junit.Test;
import org.me.server.exceptions.IncorrectAccountFormatException;
import org.me.server.exceptions.InvalidBankCodeException;
import org.me.server.exceptions.InvalidCheckDigitException;
import org.me.server.model.FinnishBankAccountNumber;
import org.me.server.validation.BankAccountNumberValidator;

import static org.junit.Assert.assertEquals;

public class TestFinnishBankAccountValidator {

    BankAccountNumberValidator validator;

    @Before
    public void setUp() {
       validator = new FinnishBankAccountNumber.FinnishBankAccountNumberValidator();
    }


    @Test
    public void testFinnishBankAccountNumber_returnValidObject()
            throws InvalidBankCodeException, InvalidCheckDigitException, IncorrectAccountFormatException {
        String account1_num = "123456-785"; // Nordea (bank group 1)
        String account2_num = "423456-781"; // Sp (bank group 2)

        String account1_long_format = validator.validateAndConvertToLongFormat(account1_num);
        String account2_long_format = validator.validateAndConvertToLongFormat(account2_num);

        assertEquals("12345600000785", account1_long_format);
        assertEquals("42345670000081", account2_long_format);
    }

    @Test(expected = IncorrectAccountFormatException.class)
    public void testFinnishBankAccountNumber_incorrectFormatThrowsException()
            throws InvalidBankCodeException, InvalidCheckDigitException, IncorrectAccountFormatException {
        String account_num = "12345-789";

        validator.validateAndConvertToLongFormat(account_num);
    }

    @Test(expected = InvalidBankCodeException.class)
    public void testFinnishBankAccountNumber_unknownBankCodeThrowsException()
            throws InvalidBankCodeException, InvalidCheckDigitException, IncorrectAccountFormatException {
        String account_num = "301111-111";      // "30" is not the code for any bank

        validator.validateAndConvertToLongFormat(account_num);
    }

    @Test(expected = InvalidCheckDigitException.class)
    public void testFinnishBankAccountNumber_incorrectCheckDigitThrowsException()
            throws InvalidBankCodeException, InvalidCheckDigitException, IncorrectAccountFormatException {
        String account_num = "123456-789";

        validator.validateAndConvertToLongFormat(account_num);
    }

}
