package org.me.server.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.server.model.BankAccountNumber;
import org.me.server.model.BankAccountNumberFactory;

public class MainController extends HttpServlet {
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        
		String account_number = req.getParameter("account_number");
		BankAccountNumber ban = getBankAccountNumber(account_number);

		if (ban.getShortFormat().equals("")) {
			req.setAttribute("validity_status", "Invalid");
			
		} else {
			req.setAttribute("validity_status", "Valid");
			req.removeAttribute(account_number);
			req.setAttribute("bank_account_short", "in short format : " + ban.getShortFormat());
			req.setAttribute("bank_account_long", "in long format : " + ban.getLongFormat());
		}

		req.getRequestDispatcher("response.jsp").forward(req, resp);;
	}

	
	private BankAccountNumber getBankAccountNumber(String input) {
		return BankAccountNumberFactory.getNationalBankAccount(input, BankAccountNumberFactory.FINNISH_ACCOUNT);
	}
	
}
