<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
    <center>
        <h1>Payment Management</h1>
        <h2>
            <a href="paymentnew">Add New Payment</a>
            &nbsp;&nbsp;&nbsp;
            <a href="paymentlist">List All Payments</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${payment != null}">
            <form action="paymentupdate" method="post">
        </c:if>
        <c:if test="${payment == null}">
            <form action="paymentinsert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${payment != null}">
                        Edit Book
                    </c:if>
                    <c:if test="${payment == null}">
                        Add New Book
                    </c:if>                
                </h2>
            </caption>
                <c:if test="${payment != null}">
                    <input type="hidden" name="paymentID" value="<c:out value='${payment.paymentID}' />" />
                </c:if> 
            <tr>
                <th>Payment Method: </th>
                <td>
                    <label>
                        <input type="radio" name="method" value="creditDebit" id="creditDebitRadio"/>
                        Credit/Debit Card
                    </label>
                    <label>
                        <input type="radio" name="method" value="giftCard" id="giftCardRadio"/>
                        Gift Card
                    </label>
                </td>
            </tr>
            <tr id="creditDebitDetails" style="display: none;">
                <th>Credit/Debit Card Details: </th>
                <td>
                    <input type="text" name="CardNum" size="45" placeholder="Card Number"/>
                    <input type="text" name="ExpMonth" size="3" maxlength="2" placeholder="MM"/>
                    <input type="text" name="ExpYear" size="5" maxlength="4" placeholder="YYYY"/>
                    <input type="text" name="CVN" size="5" maxlength="3" placeholder="CVN"/>
                </td>
            </tr>
            <tr id="giftCardDetails" style="display: none;">
                <th>Gift Card Details: </th>
                <td>
                    <input type="text" name="GCNum" size="45" placeholder="Gift Card Number"/>
                    <input type="text" name="PIN" size="5" placeholder="PIN"/>
                </td>
            </tr>
            <script>
                const creditDebitRadio = document.getElementById('creditDebitRadio');
                const giftCardRadio = document.getElementById('giftCardRadio');
            
                const creditDebitDetails = document.getElementById('creditDebitDetails');
                const giftCardDetails = document.getElementById('giftCardDetails');
            
                creditDebitRadio.addEventListener('change', function() {
                    creditDebitDetails.style.display = this.checked ? 'table-row' : 'none';
                    giftCardDetails.style.display = this.checked ? 'none' : 'table-row';
                });
            
                giftCardRadio.addEventListener('change', function() {
                    giftCardDetails.style.display = this.checked ? 'table-row' : 'none';
                    creditDebitDetails.style.display = this.checked ? 'none' : 'table-row';
                });
            </script>
            
            <tr>
                <th>Amount: </th>
                <td>
                    <input type="text" name="amount" placeholder="$" oninput="formatCurrency(this)">
                </td>
            </tr>
            <script>
                function formatCurrency(input) {
                    input.value = input.value.replace(/[^0-9.]/g, '');
                    let parts = input.value.split('.');
                    if (parts.length > 1) {
                        parts[1] = parts[1].slice(0, 2);
                    }
                    input.value = parts.join('.');
                }
            </script>
            <tr>
                <th>Date: </th>
                <td>
                    <input type="date" name="date"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>