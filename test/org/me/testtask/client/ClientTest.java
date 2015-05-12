package org.me.testtask.client;


import org.junit.Test;
import org.me.testtask.model.to.FinnishBankAccountNumber;

public class ClientTest {



    @Test
    public void testAccountCreation_validAccount(){
        FinnishBankAccountNumber account = new FinnishBankAccountNumber("110335-1537");
    }


    @Test
    public void testAccountCreation_invalidAccountNumber(){
        FinnishBankAccountNumber account1 = new FinnishBankAccountNumber("123456-785");
        FinnishBankAccountNumber account2 = new FinnishBankAccountNumber("423456-781");



    }

    @Test
    public void testAccountCreation_invalidAccountFormat(){
        FinnishBankAccountNumber account = new FinnishBankAccountNumber("110335-1537");


    }
}
