package org.me.testtask.model.bl;


import org.me.testtask.model.exceptions.IncorrectAccountFormatException;
import org.me.testtask.model.exceptions.InvalidBankCodeException;
import org.me.testtask.model.exceptions.InvalidCheckDigitException;
import org.me.testtask.model.to.FinnishBankAccountNumber;
import org.me.testtask.model.validation.FinnishAccountValidator;
import org.me.testtask.model.validation.Validator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class FinnishBankAccountBL implements FinnishBankAccountIX {

    String accountFirstPart; // bank&branch code
    String accountSecondPart; // account number


    @Override
    public FinnishBankAccountNumber createAccount(String accountNumber) {
//        performSyntacticCheck(accountNumber);
//        long accountNo14 = convertTo14Digits(accountFirstPart, accountSecondPart);
//        performSemanticCheck(accountNo14);
//        return new FinnishBankAccountNumber(String.valueOf(accountNo14));
        return null;
    }


    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Account number : ??");

        Validator finnishAccountValidator = new FinnishAccountValidator();
        System.out.println();

        while (true) {
            if (userInput.hasNextLine()) {
                String input = userInput.nextLine();
                if (input.equals(""))
                    break;

                try {
                    System.out.println(finnishAccountValidator.validateAndConvertToElectronicFormat(input));
                } catch (IncorrectAccountFormatException e) {
                    e.printStackTrace();
                } catch (InvalidCheckDigitException e) {
                    e.printStackTrace();
                } catch (InvalidBankCodeException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
