package org.me.testtask.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.me.testtask.exceptions.IncorrectAccountFormatException;
import org.me.testtask.exceptions.InvalidBankCodeException;
import org.me.testtask.exceptions.InvalidCheckDigitException;
import org.me.testtask.model.BankAccountNumberFactory;
import org.me.testtask.model.FinnishBankAccountNumber;
import org.me.testtask.model.NationalBankAccountNumber;

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
