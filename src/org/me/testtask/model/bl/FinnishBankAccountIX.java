package org.me.testtask.model.bl;

import org.me.testtask.model.to.FinnishBankAccountNumber;


public interface FinnishBankAccountIX {

    FinnishBankAccountNumber createAccount(String accountNumber);
}
