<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ page import="org.me.server.controller.*" %>

<html>
<body>
	account number is <b>${requestScope.validity_status} </b> <br/>
	${requestScope.bank_account_short}  <br/>
	${requestScope.bank_account_long}
</body>
</html>