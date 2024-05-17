<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Payment Form</title>
</head>
<body>
    <center>
        <h1>Payment Management</h1>
        <h2>
            <a href="/paymentForm.jsp">Add New Payment</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/paymentListing.jsp">List All Payments</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${payment != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${payment == null}">
            <form action="" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Search Payment        
                </h2>
            </caption>
                <c:if test="${payment != null}">
                    <input type="hidden" name="paymentID" value="<c:out value='${payment.paymentID}' />" />
                </c:if> 
            <tr>
                <th>Payment ID: </th>
                <td>
                    <input type="int" name="CardNum" size="45" placeholder="Card Number"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Search" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>